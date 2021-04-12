package com.example.store.dao.impl.inmemory;

import com.example.store.dao.CommentDao;
import com.example.store.model.Comment;
import com.example.store.model.Movie;
import com.example.store.model.User;
import java.util.*;

class InMemoryCommentDao extends InMemoryAbstractDao<Comment> implements CommentDao {

    InMemoryCommentDao(InMemoryDatabase database) {
        super(database.comments, Comment::getCommentId, Comment::setCommentId, database);
    }

    @Override
    public Collection<Comment> findByMovieId(Integer moveId) {
        return database.movies.get(moveId).getComments();
    }

    @Override
    public void addComment(Movie movie, User user, String text) {
        Comment comment = new Comment(-1, movie, user, text);

        this.insert(comment, true);
        movie.getComments().add(comment);
    }

}
