package com.example.banquegestionservice;

import com.example.banquegestionservice.Configuration.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeys.class)
public class BanqueGestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanqueGestionServiceApplication.class, args);
    }

}
