package co.uk.gcsample.cakes.service;

import co.uk.gcsample.cakes.model.Cake;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalCakeSourceService {

    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(ExternalCakeSourceService.class);

    @Value("${external.cake.source}")
    private String externalSource;

    public ExternalCakeSourceService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Cake> getCakeListFromExternalSource() {
        List<Cake> cakes = new ArrayList<>();
        try (InputStream inputStream = new URL(externalSource).openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                line = reader.readLine();
            }

            return objectMapper.readValue(buffer.toString(), new TypeReference<List<Cake>>(){});

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return cakes;
    }

}
