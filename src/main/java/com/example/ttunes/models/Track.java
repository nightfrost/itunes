package com.example.ttunes.models;

public class Track {
    private int trackId;
    private String name;
    private double price;
    private String artist;
    private String album;
    private String genre;

    public Track() {
    }

    public Track(String name, String artist, String album, String genre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public Track(int trackId, String name, double price) {
        this.trackId = trackId;
        this.name = name;
        this.price = price;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
