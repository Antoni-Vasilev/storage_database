package com.storage;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Storage")
                        .version("1.0.0")
                        .description("API for an app that will store all your files in one place")
                        .termsOfService("")
                        .contact(new Contact().email("antonivasilev15@gmail.com").name("Antoni Vasilev"))
                        .license(new License().name("").url(""))
                );
    }

}
