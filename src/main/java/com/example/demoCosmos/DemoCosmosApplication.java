package com.example.demoCosmos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoCosmosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCosmosApplication.class, args);
	}

}
