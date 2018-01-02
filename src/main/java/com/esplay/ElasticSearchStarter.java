package com.esplay;
/*
 * Created by nvishwarupe on 28/11/2017
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ElasticSearchStarter {
    public static void main(String[] args){
        SpringApplication.run(ElasticSearchStarter.class, args);
    }
}
