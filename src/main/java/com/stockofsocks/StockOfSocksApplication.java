
package com.stockofsocks;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@OpenAPIDefinition
@SpringBootApplication
public class StockOfSocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockOfSocksApplication.class, args);
    }

}