package com.example.demo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DemoControllerTest {
    @Autowired
    private DemoController demoController;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getAll() {
    }
}