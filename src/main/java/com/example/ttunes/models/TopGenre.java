package com.example.ttunes.models;

public class TopGenre {
    private int genreId;
    private String name;
    private int numOfOccurrences;


    public TopGenre() {
    }

    public TopGenre(String name) {
        this.name = name;
    }

    public TopGenre(int genreId, String name, int numOfOccurrences) {
        this.genreId = genreId;
        this.name = name;
        this.numOfOccurrences = numOfOccurrences;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfOccurrences() {
        return numOfOccurrences;
    }

    public void setNumOfOccurrences(int numOfOccurrences) {
        this.numOfOccurrences = numOfOccurrences;
    }
}
