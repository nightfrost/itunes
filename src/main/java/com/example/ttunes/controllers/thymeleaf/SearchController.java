package com.example.ttunes.controllers.thymeleaf;

import com.example.ttunes.services.TrackServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/search")
public class SearchController {

    //Get method that takes search word as a paramater and a model. Returns a list of tracks in a model object.
    //Renders Search
    @GetMapping
    public String getSearch(@RequestParam(defaultValue = "") String track, ModelMap model) {
        model.put("searchResult", "Search result for: " + track);
        model.put("tracks", new TrackServices().getSearchedTracks(track));
        return "search";
    }

}
