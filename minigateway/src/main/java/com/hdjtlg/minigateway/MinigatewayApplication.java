package com.hdjtlg.minigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MinigatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinigatewayApplication.class, args);
    }

}
