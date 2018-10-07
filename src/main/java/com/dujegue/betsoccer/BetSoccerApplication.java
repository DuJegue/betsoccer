package com.dujegue.betsoccer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dujegue.betsoccer.controller"})
public class BetSoccerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetSoccerApplication.class, args);
	}
}
