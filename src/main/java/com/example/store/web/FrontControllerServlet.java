package com.example.store.web;

import com.example.store.model.Category;
import com.example.store.model.Item;
import com.example.store.model.User;
import com.example.store.services.CategoryService;
import com.example.store.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;


@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/do/*"})
    public class FrontControllerServlet extends HttpServlet {

    CategoryService categoryService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
        categoryService = (CategoryService) config.getServletContext().getAttribute("categoryService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        try {
            switch (pathInfo) {
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/createCategory":
                    createCategory(request, response);
                    break;
                case "/editCategory":
                    editCategory(request, response);
                    break;
                case "/deleteCategory":
                    deleteCategory(request, response);
                    break;
                case "/deleteItem":
                    deleteItem(request, response);
                    break;
                case "/createItem":
                    createItem(request, response);
                    break;
                case "/editItem":
                    editItem(request, response);
                    break;
                case "/":
                case "/search":
                default:
                    category(request, response);
                    break;
            }
        } catch (RuntimeException ex) {
            error(request, response, "Oops, " + ex.getMessage());
        }

    }

    protected void category(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("text");

        Collection<Category> categories = null;

        if (request.getParameter("categoryId") != null) {
            int parentCategoryId = Integer.parseInt(request.getParameter("categoryId"));
            Category parentCategory = categoryService.getCategoryById(parentCategoryId);
            request.setAttribute("parentCategory", parentCategory);
            System.out.println("checkwefwe " + (parentCategory == null) + " " + parentCategoryId);
            categories = parentCategory.getSubCategories();
        } else {
            categories = categoryService.searchRootCategories(searchText);
        }

        request.setAttribute("categories", categories);
        request.setAttribute("text", searchText);
        request.getRequestDispatcher("/WEB-INF/jsp/category.jsp").forward(request, response);
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
        request.getSession().invalidate();
        response.sendRedirect(".");
    }

    protected void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        System.out.println("here");
        Category parentCategory = null;
        if (!isEmpty(request.getParameter("categoryId"))) {
            System.out.println("here 2");
            Integer parentCategoryId = Integer.parseInt(request.getParameter("categoryId"));
            System.out.println("parent " + parentCategoryId);
            parentCategory = categoryService.getCategoryById(parentCategoryId);
        }

        String text = request.getParameter("text");

        System.out.println("text is " + text);
        categoryService.addCategory(parentCategory, text);
        if (parentCategory != null) {
            response.sendRedirect("./categoryId?categoryId=" + parentCategory.getCategoryId());
        } else {
            response.sendRedirect("./");
        }
    }

    protected void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        Category category = null;
        if (!isEmpty(request.getParameter("categoryId"))) {
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            category = categoryService.getCategoryById(categoryId);
            System.out.println("edit " + categoryId + " " + (category == null));
        }

        String text = request.getParameter("text");
        categoryService.editCategory(category, text);

        if (!isEmpty(request.getParameter("parentCategoryId"))) {
            response.sendRedirect("./categoryId?categoryId=" + request.getParameter("parentCategoryId"));
        } else {
            response.sendRedirect("./");
        }
    }

    protected void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        if (!isEmpty(request.getParameter("categoryId"))) {
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Category category = categoryService.getCategoryById(categoryId);
            categoryService.deleteCategory(category);
            if (category.getParentCategoryId() != null) {
                response.sendRedirect("./categoryId?categoryId=" + category.getParentCategoryId());
            } else {
                response.sendRedirect("./");
            }
        }
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        if (!isEmpty(request.getParameter("itemId"))) {
            Integer itemId = Integer.parseInt(request.getParameter("itemId"));
            Item item = categoryService.getItemById(itemId);
            List<Category> categoriesContainingItem = categoryService.getCategoriesContainingItem(itemId);
            categoryService.deleteItem(categoriesContainingItem, item);
            response.sendRedirect("./categoryId?categoryId=" + categoriesContainingItem.get(0).getCategoryId());
        }
    }

    protected void createItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        Category parentCategory = null;
        if (!isEmpty(request.getParameter("categoryId"))) {
            Integer parentCategoryId = Integer.parseInt(request.getParameter("categoryId"));
            parentCategory = categoryService.getCategoryById(parentCategoryId);
        }

        String maker = request.getParameter("maker");
        String model = request.getParameter("model");
        String description = request.getParameter("description");

        categoryService.addItem(parentCategory, maker, model, description);
        response.sendRedirect("./categoryId?categoryId=" + parentCategory.getCategoryId());
    }

    protected void editItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        Category parentCategory = null;
        if (!isEmpty(request.getParameter("categoryId"))) {
            Integer parentCategoryId = Integer.parseInt(request.getParameter("categoryId"));
            parentCategory = categoryService.getCategoryById(parentCategoryId);
        }

        Integer itemId = Integer.parseInt(request.getParameter("itemId"));
        Item item = categoryService.getItemById(itemId);
        System.out.println("item id " + item.getItemId());

        String maker = request.getParameter("maker");
        String model = request.getParameter("model");
        String description = request.getParameter("description");

        categoryService.editItem(item, maker, model, description);
        response.sendRedirect("./categoryId?categoryId=" + parentCategory.getCategoryId());
    }

    protected void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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

    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

}
