package com.example.store.dao.impl.inmemory;

import com.example.store.model.Comment;
import com.example.store.model.Movie;
import com.example.store.model.User;
import java.util.Arrays;
import java.util.List;

public class InMemoryTestData {

    public static void generateTo(InMemoryDatabase database) {
        database.users.clear();
        database.movies.clear();
        database.comments.clear();

        User alice = new User(1, "Alice", "alice@example.com", "passwordhash");
        User bob = new User(2, "Bob", "bob@example.com", "passwordhash");
        User charlie = new User(3, "Charlie", "charlie@example.com", "passwordhash");
        User diana = new User(4, "Diana", "diana@example.com", "passwordhash");
        User evil = new User(5, "Evil Emperror", "evil@example.com", "passwordhash");
        List<User> users = Arrays.asList(alice, bob, charlie, diana, evil);
        users.forEach(user -> database.users.put(user.getUserId(), user));

        Movie pirates = new Movie(1, "Pirates of the Caribbean: The Curse of the Black Pearl (2003)", "Blacksmith Will Turner teams up with eccentric pirate \"Captain\" Jack Sparrow to save his love, the governor's daughter, from Jack's former pirate allies, who are now undead.");
        Movie ironMan = new Movie(2, "Iron Man (2008)", "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.");
        Movie guardians = new Movie(3, "Guardians of the Galaxy (2014)", "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.");
        Movie deadPool = new Movie(4, "Deadpool (2016)", "A wisecracking mercenary gets experimented on and becomes immortal but ugly, and sets out to track down the man who ruined his looks.");
        Movie wonderWoman = new Movie(5, "Wonder Woman (2017)", "When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny.");
        List<Movie> movies = Arrays.asList(pirates, ironMan, guardians, deadPool, wonderWoman);
        movies.forEach(movie -> database.movies.put(movie.getMovieId(), movie));

        pirates.getLikers().addAll(Arrays.asList(alice, bob, evil));
        ironMan.getLikers().addAll(Arrays.asList(alice, bob, charlie, evil));
        guardians.getLikers().addAll(Arrays.asList(alice, bob, charlie, diana, evil));
        deadPool.getLikers().addAll(Arrays.asList(bob, charlie, evil));
        wonderWoman.getLikers().addAll(Arrays.asList(diana));

        pirates.getComments().add(new Comment(1, pirates, alice, "Orlando Blum so hot <3"));
        pirates.getComments().add(new Comment(2, pirates, alice, "Actually, Jack Sparrow too :)"));
        pirates.getComments().add(new Comment(3, pirates, bob, "Actually, CAPTAIN Jack Sparrow"));
        pirates.getComments().add(new Comment(4, pirates, evil, "I like Barbossa! He is so cool!"));
        ironMan.getComments().add(new Comment(5, ironMan, bob, "The Best. Movie. EVER."));
        ironMan.getComments().add(new Comment(6, ironMan, charlie, "true"));
        guardians.getComments().add(new Comment(7, guardians, bob, "The Best. Movie. EVER... (forever)"));
        guardians.getComments().add(new Comment(8, guardians, charlie, "forever true"));
        guardians.getComments().add(new Comment(9, guardians, evil, "I like Thanos! He is so cool!"));
        deadPool.getComments().add(new Comment(10, deadPool, bob, "Lol"));
        deadPool.getComments().add(new Comment(11, deadPool, evil, "I like Dead Pool? OMG, I like protagonist for the first time ever!"));
        wonderWoman.getComments().add(new Comment(12, wonderWoman, diana, "I like Diana!"));
        movies.stream()
                .flatMap(movie -> movie.getComments().stream())
                .forEach(comment -> database.comments.put(comment.getCommentId(), comment));
    }
}
