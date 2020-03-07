package com.example.demo.controllers;

import lombok.Data;
import lombok.experimental.Accessors;

//dataEntities -> dataEntities
//                                .stream()
//                        .map(dataEntity ->
//                                new ResponseClass().setDesignationNow(dataEntity.getDesignation())
//                                ).collect(Collectors.toList())
@Data
@Accessors(chain = true)
class ResponseClass{
    private Long id;
    private String nameNow;
    private String designationNow;
}
