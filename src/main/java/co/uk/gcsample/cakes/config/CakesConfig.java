package co.uk.gcsample.cakes.config;

import co.uk.gcsample.cakes.service.CakeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CakesConfig {

    @Bean
    ApplicationRunner initialiseData(CakeService cakeService) {
        return args -> {
            cakeService.initialiseCakesFromExternalSource();
        };
    }

    @Bean
    ObjectMapper objectMapper () {
        return new ObjectMapper();
    }
}
