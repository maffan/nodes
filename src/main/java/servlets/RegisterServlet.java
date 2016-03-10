/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.User;
import filters.HomeRouter;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;

/**
 *
 * Servlet for registering new users. Serves up login page and processes 
 * results.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(HomeRouter.TEMPLATE + "?partial=register").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        
        if(username.equals("") || password.equals("") || name.equals("") || phone.equals("")){
            response.sendRedirect("register?failed=true");
        } 
        else if (!checkIfTaken(username, phone)){
            User user = register(username, password, name, phone);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home");
        }
        else{
            response.sendRedirect("register?taken=true");
        }
    }

    private User register(String username, String password, String name, String phone) {
        User user = new User();
        user.setMail(username);
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        
        userService.create(user);
        return user;
    }

    private boolean checkIfTaken(String username, String phone) {
        User user = userService.find(username);
        User userPhone = userService.findByPhone(phone);
        return ((user != null) && (userPhone != null));
    }

}
