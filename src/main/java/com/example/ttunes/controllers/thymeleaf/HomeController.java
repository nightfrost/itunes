package com.example.ttunes.controllers.thymeleaf;

import com.example.ttunes.services.ArtistServices;
import com.example.ttunes.services.GenreServices;
import com.example.ttunes.services.TrackServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    //Get method with map model returning three different objects, each containing a list.
    //Renders Index
    @GetMapping
    public String getRandoms(ModelMap model) {
        model.put("artists", new ArtistServices().getRandArtists());
        model.put("genres", new GenreServices().getRandGenres());
        model.put("tracks", new TrackServices().getRandTracks());
        return "index";
    }
}
