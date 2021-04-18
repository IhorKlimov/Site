package com.example.store.web;

import com.example.store.dao.DaoFactory;
import com.example.store.dao.impl.inmemory.InMemoryDatabase;
import com.example.store.dao.impl.inmemory.InMemoryTestData;
import com.example.store.services.CategoryService;
import com.example.store.services.CategoryServiceImpl;
import com.example.store.services.UserService;
import com.example.store.services.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.function.UnaryOperator;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Change to real database in real project
        InMemoryDatabase database = new InMemoryDatabase();

        // Don't use in real project
        InMemoryTestData.generateTo(database);

        DaoFactory daoFactory = database.getDaoFactory();

        UserService userService = new UserServiceImpl(daoFactory, UnaryOperator.identity());
        sce.getServletContext().setAttribute("userService", userService);

        CategoryService categoryService = new CategoryServiceImpl(daoFactory);
        sce.getServletContext().setAttribute("categoryService", categoryService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
