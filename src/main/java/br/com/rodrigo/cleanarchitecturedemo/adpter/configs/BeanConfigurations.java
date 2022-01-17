package br.com.rodrigo.cleanarchitecturedemo.adpter.configs;

import br.com.rodrigo.cleanarchitecturedemo.CleanArchitectureDemoApplication;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.Ports.ProductPort;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.ProductUseCase;
import br.com.rodrigo.cleanarchitecturedemo.domain.userCases.interfaces.IProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { CleanArchitectureDemoApplication.class })
public class BeanConfigurations {

    @Bean
    IProductUseCase productUseCase(ProductPort productPort) {
        return new ProductUseCase(productPort);
    }

}
