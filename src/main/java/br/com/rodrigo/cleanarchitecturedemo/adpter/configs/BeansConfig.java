package br.com.rodrigo.cleanarchitecturedemo.adpter.configs;

import br.com.rodrigo.cleanarchitecturedemo.CleanArchitectureDemoApplication;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.ProductPort;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations.ProductUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { CleanArchitectureDemoApplication.class })
public class BeansConfig {

    @Bean
    IProductUseCase productUseCase(ProductPort productPort) {
        return new ProductUseCase(productPort);
    }

}
