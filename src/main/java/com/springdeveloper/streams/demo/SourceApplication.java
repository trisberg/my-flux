package com.springdeveloper.streams.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SourceApplication.class, args).close();
	}
}