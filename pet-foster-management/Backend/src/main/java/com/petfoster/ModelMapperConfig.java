package com.petfoster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
       // your JPA entity
import org.modelmapper.ModelMapper;     // ModelMapper class

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
