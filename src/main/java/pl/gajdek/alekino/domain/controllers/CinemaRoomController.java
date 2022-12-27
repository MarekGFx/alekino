package pl.gajdek.alekino.domain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;

import java.util.List;

@RestController
public class CinemaRoomController {

    @GetMapping("/cinemaroom")
    public List<CinemaRoom> getCinemaRoom(){
        throw new IllegalArgumentException("Tu będzie lista sal!!!");
    }
}
