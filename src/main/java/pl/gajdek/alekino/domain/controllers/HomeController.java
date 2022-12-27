//package pl.gajdek.alekino.domain.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import pl.gajdek.alekino.domain.movie.MovieService;
//import pl.gajdek.alekino.domain.movie.dto.MovieDto;
//
//import java.util.List;
//
//
//@Controller
//public class HomeController {
//
//    private final MovieService movieService;
//
//
//    public HomeController(MovieService movieService) {
//        this.movieService = movieService;
//    }
//
//    @GetMapping("/")
//    public String home(Model model){
//        return "movie";
//    }
//}
