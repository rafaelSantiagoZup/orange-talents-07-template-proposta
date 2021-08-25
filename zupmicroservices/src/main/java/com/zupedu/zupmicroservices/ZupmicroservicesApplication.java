package com.zupedu.zupmicroservices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableAutoConfiguration
public class ZupmicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZupmicroservicesApplication.class, args);
	}
}
