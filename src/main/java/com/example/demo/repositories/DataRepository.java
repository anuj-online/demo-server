package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface DataRepository extends CrudRepository<DataEntity, Long> {

    List<DataEntity> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate start, LocalDate end);


}
