package pl.gajdek.alekino.domain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gajdek.alekino.domain.repertoire.Repertoire;

import java.util.List;

@RestController
public class RepertoireController {

    @GetMapping("/repertuar")
    List<Repertoire> getCurrentDayRepertoire(){
        throw new IllegalArgumentException("Tu będzie repertuar na aktualny dzień!!!");
    }
}
