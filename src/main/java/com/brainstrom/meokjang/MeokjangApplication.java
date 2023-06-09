package com.brainstrom.meokjang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MeokjangApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeokjangApplication.class, args);
	}

}