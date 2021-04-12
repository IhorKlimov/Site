package com.example.store.web;

import com.example.store.dao.DaoFactory;
import com.example.store.dao.impl.inmemory.*;
import com.example.store.services.*;
import java.util.function.UnaryOperator;
import javax.servlet.*;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Change to real database in real project
        InMemoryDatabase database = new InMemoryDatabase();
        
        // Don't use in real project
        InMemoryTestData.generateTo(database);

        DaoFactory daoFactory = database.getDaoFactory();

        MovieService movieService = new MovieServiceImpl(daoFactory);
        sce.getServletContext().setAttribute("movieService", movieService);

        UserService userService = new UserServiceImpl(daoFactory, UnaryOperator.identity());
        sce.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
