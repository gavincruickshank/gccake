package co.uk.gcsample.cakes.service;

import co.uk.gcsample.cakes.entity.Cake;
import co.uk.gcsample.cakes.repository.CakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        logger.info("Listing cake titles...");
        return cakeRepository.findAll().stream().map(cake -> cake.getTitle()).collect(Collectors.toList());
    }

    public List<Cake> getCakes() {
        logger.info("Retrieving list of all cakes...");
        List<Cake> cakes = cakeRepository.findAll();
        logger.info("Found " + cakes.size());
        return cakes;
    }

    public Optional<Cake> getCake(String title) {
        logger.info("Attempting to find cake with title: " + title);
        return Optional.ofNullable(cakeRepository.findByTitle(title));
    }

    public void addCake(Cake cake) {
        logger.info("Saving cake: " + cake);
        cakeRepository.save(cake);
    }

    public void initialiseCakesFromExternalSource() {
        logger.info("Fetching cakes from external source..");
        List<Cake> cakes = externalCakeSourceService.getCakeListFromExternalSource();
        logger.info("Found " + cakes.size());
        cakes.forEach(cake -> logger.info("Adding external cake: " + cake));
        cakeRepository.saveAll(cakes);
    }
}
