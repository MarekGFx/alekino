package pl.gajdek.alekino.domain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gajdek.alekino.domain.showing.Showing;

import java.util.List;

@RestController
public class ShowingController {

    @GetMapping("/showing")
    public List<Showing> getShowing(){
        throw new IllegalArgumentException("Tu będzie lista seansów!!!");
    }
}
