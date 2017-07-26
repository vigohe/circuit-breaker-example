package cl.vigohe.example.service;

import cl.vigohe.example.domain.Estimation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vigohe on 07-05-17.
 */
@Service
public class ViajesService {
    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ViajesService.class);

    public ViajesService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    //commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="6000")}
    @HystrixCommand(fallbackMethod = "fallback")
    public List getEstimationsWithCircuitBreaker() {
        logger.info("Own thread ...");

        URI uri = URI.create("http://localhost:9000/poor/estimations");
        return this.restTemplate.getForObject(uri, List.class);
    }

    public List fallback() {
        List polizas = new ArrayList<Estimation>();
        polizas.add(new Estimation("Mocked Data Company 1", 5000));
        polizas.add(new Estimation("Mocked Data Company 2", 3400));
        polizas.add(new Estimation("Mocked Data Company 3", 4000));
        polizas.add(new Estimation("Mocked Data Company 4", 2000));

        return polizas;
    }

    public List getEstimationsWithoutCircuitBreaker(){
        logger.info("Same thread as server...");
        URI uri = URI.create("http://localhost:9000/poor/estimations");
        return this.restTemplate.getForObject(uri, List.class);
    }

}
