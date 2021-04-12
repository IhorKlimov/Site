package com.example.store.model;

import java.time.Instant;
import java.util.Date;

public class Comment {

    private Integer commentId;
    private String text;
    private Movie movie;
    private User user;
    private Instant instant;

    public Comment(Integer commentId, Movie movie, User user, String text) {
        this(commentId, movie, user, text, Instant.now());
    }

    public Comment(Integer commentId, Movie movie, User user, String text, Instant instant) {
        this.commentId = commentId;
        this.text = text;
        this.movie = movie;
        this.user = user;
        this.instant = instant;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getText() {
        return text;
    }

    public Movie getMovie() {
        return movie;
    }

    public User getUser() {
        return user;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
    
    public Date getDate() {
       return Date.from(instant); 
    }
    
}
