package co.uk.gcsample.cakes.service;

import co.uk.gcsample.cakes.model.Cake;
import co.uk.gcsample.cakes.repository.CakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeService {

    private final CakeRepository cakeRepository;
    private final ExternalCakeSourceService externalCakeSourceService;

    private static final Logger logger = LoggerFactory.getLogger(CakeService.class);

    public CakeService(CakeRepository cakeRepository, ExternalCakeSourceService externalCakeSourceService) {
        this.cakeRepository = cakeRepository;
        this.externalCakeSourceService = externalCakeSourceService;
    }

    public List<String> getCakeTitles() {
        return cakeRepository.findAll().stream().map(cake -> cake.getTitle()).collect(Collectors.toList());
    }

    public List<Cake> getCakes() {
        return cakeRepository.findAll();
    }

    public void addCake(Cake cake) {
        cakeRepository.save(cake);
    }

    public void initialiseCakesFromExternalSource() {
        List<Cake> cakes = externalCakeSourceService.getCakeListFromExternalSource();
        cakes.forEach(cake -> logger.info("Adding external cake: " + cake));
        cakeRepository.saveAll(cakes);
    }
}
