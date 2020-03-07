package com.example.demo;

import com.example.demo.repositories.DataEntity;
import com.example.demo.repositories.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {
	private final DataRepository dataRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * method to initialize some data in repository
	 */
	@PostConstruct
	public void print(){
		Random random = new Random(4);

		for(int i = 0; i < 50; i++){
			int days = random.nextInt();
			DataEntity dataEntity = new DataEntity()
					.setDesignation(
							UUID.randomUUID().toString().substring(0, 5))
					.setEndDate(LocalDate.now().minusDays(days))
					.setStartDate(LocalDate.now().plusDays(days))
					.setName(UUID.randomUUID().toString().substring(0, 3));
			dataRepository.save(dataEntity);
		}
	}

}
