package com.kbe.priceservice.config;

import com.kbe.priceservice.domain.PriceServiceUtils;
import com.kbe.priceservice.domain.PriceServiceUtilsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PriceServiceUtils priceServiceUtils(){return new PriceServiceUtilsImpl();}
}
