package com.example.store.model;

import java.time.Instant;
import java.util.*;

public class Movie {

    private Integer movieId;
    private String title;
    private String description;
    private List<Comment> comments;
    private Set<User> likers;
    private Instant instant;

    public Movie(Integer movieId, String title, String description) {
        this(movieId, title, description, Instant.now());
    }

    public Movie(Integer movieId, String title, String description, Instant instant) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.instant = instant;
        comments = new ArrayList<>();
        likers = new HashSet<>();
    }

    public Integer getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Set<User> getLikers() {
        return likers;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikers(Set<User> likers) {
        this.likers = likers;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public int getNumberOfLikes() {
        return likers.size();
    }

    public int getNumberOfComments() {
        return comments.size();
    }

}
