package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProducerAppMain {

    public static void main(String[] args) {
        SpringApplication.run(ProducerAppMain.class,args);
    }

}