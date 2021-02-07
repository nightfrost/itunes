package com.example.ttunes.services;

import com.example.ttunes.data_access.ArtistRepo;
import com.example.ttunes.models.Artist;
import java.util.List;

//Middle-man class for Artist repo and Home Controller
public class ArtistServices {

    private final ArtistRepo artistRepo = new ArtistRepo();

    //Return list of 5 random artists
    public List<Artist> getRandArtists() {
        List<Artist> artists = artistRepo.getRandArtists();
        if (artists.size() > 0)
            return artists;
        return null;
    }


}
