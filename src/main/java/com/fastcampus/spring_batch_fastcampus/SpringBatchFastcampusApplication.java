package com.fastcampus.spring_batch_fastcampus;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchFastcampusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchFastcampusApplication.class, args);
    }

}
