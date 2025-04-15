package com.example.foodly;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@OpenAPIDefinition(info = @Info(title = "API do Money Flow", description = "Site de receitas culinárias!!!", version = "v1"))
@SpringBootApplication
public class FoodlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodlyApplication.class, args);
	}

}
