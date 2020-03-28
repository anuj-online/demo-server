package com.example.demo.controllers;

import com.example.demo.repositories.DataEntity;
import com.example.demo.services.DemoService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DemoController {

    private final DemoService demoService;


    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseClass> getAll(){
        CompletableFuture<Stream<DataEntity>> all = demoService.getAll(LocalDate.now());

        Stream<ResponseClass> collect = all.join().map(d ->
                new ResponseClass()
                        .setDesignationNow(d.getDesignation())
                        .setId(d.getId())
                        .setNameNow(d.getName()));


        return Flux.fromStream(collect).doOnComplete(() -> log.info("DONE!")).delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping(value = "/all-o", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Single<Stream<ResponseClass>>> getAllObservable(){
        Single<Stream<ResponseClass>> map = demoService.streamObservable(LocalDate.now())
                .subscribeOn(Schedulers.io()).map(dl ->
                        dl.stream().map(d -> new ResponseClass()
                                .setDesignationNow(d.getDesignation())
                                .setId(d.getId())
                                .setNameNow(d.getName())));

        return Flux.just(map).delayElements(Duration.ofSeconds(1L));

//        return null;

    }
}

