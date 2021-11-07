package co.uk.gcsample.cakes.api;

import co.uk.gcsample.cakes.entity.Cake;
import co.uk.gcsample.cakes.service.CakeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/cakes")
public class CakeController {

    private final CakeService cakeService;

    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping("/list")
    @ApiOperation(value= "List all cakes being managed")
    public List<String> allCakes() {
        return cakeService.getCakeTitles();
    }

    @GetMapping("/findCake")
    @ApiOperation(value= "Get details of specific cake")
    public Cake findCake(@RequestParam ("title") String title) {
        return cakeService.getCake(title).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find cake"));
    }

    @GetMapping("/")
    @ApiOperation(value= "Get full details of all cakes being managed")
    public List<Cake> allCakeDetails() {
        return cakeService.getCakes();
    }

    @PostMapping("/")
    @ApiOperation(value= "Add a new cake")
    public void addCake(@RequestBody Cake cake) {
        cakeService.addCake(cake);
    }
}
