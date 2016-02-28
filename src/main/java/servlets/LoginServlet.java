/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.User;
import filters.HomeRouter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

/**
 *
 * @author Marcus
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(HomeRouter.TEMPLATE + "?partial=login").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = login(username, password);
        if(user != null){
            HttpSession session = request.getSession();
            String uri = session.getAttribute("destination") == null ? 
                    "home" : (String) session.getAttribute("destination");           
            session.setAttribute("user", user);
            response.sendRedirect(uri);
        }
        else{
            response.sendRedirect("login?failed=true");
        }
    }

    private User login(String username, String password) {
        User user = userService.find(username);
        if(user != null && user.getPassword().equals(password))
            return user;
        else
            return null;
    }

}
