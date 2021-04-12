package com.example.store.dao;

import com.example.store.model.*;
import java.util.Collection;

public interface CommentDao extends AbstractDao<Comment> {

    Collection<Comment> findByMovieId(Integer moveId);

    void addComment(Movie movie, User user, String text);
}
