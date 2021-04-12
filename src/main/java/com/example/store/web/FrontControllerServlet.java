package com.example.store.web;

import com.example.store.model.*;
import com.example.store.services.*;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/do/*"})
public class FrontControllerServlet extends HttpServlet {

    MovieService movieService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
        movieService = (MovieService) config.getServletContext().getAttribute("movieService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
//        try {
            switch (pathInfo) {
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/movie":
                    movie(request, response);
                    break;
                case "/like":
                    like(request, response);
                    break;
                case "/unlike":
                    unlike(request, response);
                    break;
                case "/comment":
                    comment(request, response);
                    break;
                case "/":
                case "/search":
                default:
                    movies(request, response);
                    break;
            }
//        } catch (RuntimeException ex) {
//            error(request, response, "Oops, " + ex.getMessage());
//        }

    }

    protected void movies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("text");

        String sort = request.getParameter("sort");
        MovieSortCriteria sortCriteria;
        if (sort == null || sort.equals("")) {
            sortCriteria = MovieSortCriteria.OLD_FIRST;
        } else {
            sortCriteria = MovieSortCriteria.valueOf(sort);
        }

        System.out.println((movieService == null) + " null check");
        Collection<Movie> movies = movieService.search(searchText, sortCriteria);
        request.setAttribute("movies", movies);
        request.setAttribute("text", searchText);
        request.getRequestDispatcher("/WEB-INF/jsp/movies.jsp").forward(request, response);
    }

    protected void movie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        Movie movie = movieService.getMovieById(movieId);
        request.setAttribute("movie", movie);
        request.getRequestDispatcher("/WEB-INF/jsp/movie.jsp").forward(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();

        String login = request.getParameter("login");
        User user = userService.getByLogin(login);
        if (user == null) {
            error(request, response, "Sorry, user with login '" + login + "' not exists");
            return;
        }
        String password = request.getParameter("password");

        if (!userService.checkPassword(user, password)) {
            error(request, response, "Sorry, wrong password");
            return;
        }

        request.getSession().setAttribute("user", user);
        response.sendRedirect(".");
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("movies", movieService.getAllMovies());
        request.getSession().invalidate();
        response.sendRedirect(".");
    }

    protected void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        int movieId = Integer.parseInt(request.getParameter("movieId"));
        Movie movie = movieService.getMovieById(movieId);

        movieService.likeMovie(movie, user);
        response.sendRedirect("./movie?movieId=" + movieId);
    }

    protected void unlike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        int movieId = Integer.parseInt(request.getParameter("movieId"));
        Movie movie = movieService.getMovieById(movieId);

        movieService.unlikeMovie(movie, user);
        response.sendRedirect("./movie?movieId=" + movieId);
    }

    protected void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        int movieId = Integer.parseInt(request.getParameter("movieId"));
        Movie movie = movieService.getMovieById(movieId);

        String text = request.getParameter("text");

        movieService.addComment(movie, user, text);
        response.sendRedirect("./movie?movieId=" + movieId);
    }

    protected void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
