package com.example.web.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public Gson gson() {
        return new GsonBuilder()
//              .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

}
