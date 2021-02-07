package com.example.ttunes.services;

import com.example.ttunes.data_access.TrackRepo;
import com.example.ttunes.models.Track;

import java.util.List;
import java.util.Objects;

//Middle-man class for track repo and both home and search controllers
public class TrackServices {

    private final TrackRepo trackRepo = new TrackRepo();

    //check if list is empty, if not, return 5 random tracks.
    public List<Track> getRandTracks() {
        List<Track> tracks = trackRepo.getRandTracks();
        if (tracks.size() > 0)
            return tracks;
        return null;
    }

    //return list of searched tracks
    public List<Track> getSearchedTracks(String name) {
        List<Track> tracks = null;
        if (Objects.requireNonNull(name).length() != 0) {
            tracks = trackRepo.getSearchedTrack(name);
        }
        assert tracks != null;
        if (tracks.size() > 0)
            return tracks;
        return null;
    }
}
