package com.samborskiy.hardwarestore.api.config;

import com.samborskiy.hardwarestore.store.model.Product;
import com.samborskiy.hardwarestore.store.model.Showcase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class AuditConfig {
    @Bean
    public Showcase auditorProvider(){
        return new Showcase();
    }
    @Bean
    public Product auditProvider(){
        return new Product();
    }
}
