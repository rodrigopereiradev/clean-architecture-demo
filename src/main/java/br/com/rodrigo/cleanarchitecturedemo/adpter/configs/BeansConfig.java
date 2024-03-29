package br.com.rodrigo.cleanarchitecturedemo.adpter.configs;

import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IBrandUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.IProductUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations.BrandUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.implementations.ProductUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.BrandPort;
import br.com.rodrigo.cleanarchitecturedemo.domain.usercases.ports.ProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    IProductUseCase productUseCase(ProductPort productPort, IBrandUseCase brandUseCase) {
        return new ProductUseCase(productPort, brandUseCase);
    }

    @Bean
    IBrandUseCase brandUseCase(BrandPort brandPort) {
        return new BrandUseCase(brandPort);
    }

}
