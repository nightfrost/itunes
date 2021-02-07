package com.example.ttunes.services;

import com.example.ttunes.data_access.GenreRepo;
import com.example.ttunes.models.TopGenre;
import java.util.List;

//Middle-man class for genre repo and home controller
public class GenreServices {

    private final GenreRepo genreRepo = new GenreRepo();

    //Check if list is empty, if not, return 5 random genres
    public List<TopGenre> getRandGenres() {
        List<TopGenre> genres = genreRepo.getRandGenres();
        if (genres.size() > 0)
            return genres;
        return null;
    }
}
