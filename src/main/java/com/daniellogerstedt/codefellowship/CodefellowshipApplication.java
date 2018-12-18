package com.daniellogerstedt.codefellowship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// "scanBasePackages" found here -> https://stackoverflow.com/questions/30425016/spring-boot-find-autowired-on-another-package
@SpringBootApplication
public class CodefellowshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodefellowshipApplication.class, args);
	}

}

