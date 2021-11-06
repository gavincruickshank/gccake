package co.uk.gcsample.cakes.api;

import co.uk.gcsample.cakes.model.Cake;
import co.uk.gcsample.cakes.service.CakeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CakeController {

    private final CakeService cakeService;

    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping
    public List<String> allCakeTitles() {
        return cakeService.getCakeTitles();
    }

    @GetMapping
    @RequestMapping("cakes")
    public List<Cake> allCakes() {
        return cakeService.getCakes();
    }

    @PostMapping
    @RequestMapping("cakes/add")
    public void addCake(@RequestBody Cake cake) {
        cakeService.addCake(cake);
    }
}
