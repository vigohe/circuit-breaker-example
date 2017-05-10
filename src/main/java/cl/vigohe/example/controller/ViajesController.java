package cl.vigohe.example.controller;

import cl.vigohe.example.service.ViajesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vigohe on 08-05-17.
 */
@RestController
public class ViajesController {

    private static final Logger logger = LoggerFactory.getLogger(ViajesController.class);

    private final ViajesService viajesService;

    public ViajesController(ViajesService viajesService) {
        this.viajesService = viajesService;
    }

    @GetMapping("/api/estimations")
    public List getEstimations(){
        logger.info("Calling from the tomcat thread...");

        return this.viajesService.getEstimations();
    }

}
