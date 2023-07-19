package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerAppMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerAppMain.class,args);
        System.out.println("Hello ConsumerAppMain");
    }
}