package com.example.demo.services;


import com.example.demo.repositories.DataEntity;
import com.example.demo.repositories.DataRepository;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {
    private final DataRepository dataRepository;

    public CompletableFuture<Stream<DataEntity>> getAll(LocalDate date) {

        List<DataEntity> all = dataRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);
        return CompletableFuture.supplyAsync(all::parallelStream);

    }

    public Single<List<DataEntity>> streamObservable(LocalDate date) {
        return getData(date);

    }

    private Single<List<DataEntity>> getData(LocalDate date) {
        return Single.create(emitter -> {
                    List<DataEntity> all = dataRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);
                    emitter.onSuccess(all);

                }
        );
    }

}
