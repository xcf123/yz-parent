package com.yuanzong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YzTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(YzTestApplication.class, args);
	}
}
