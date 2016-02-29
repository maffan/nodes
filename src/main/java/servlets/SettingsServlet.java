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
@WebServlet(name = "SettingsServlet", urlPatterns = {"/settings"})
public class SettingsServlet extends HttpServlet {
    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(HomeRouter.TEMPLATE + "?partial=settings").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String hash = request.getParameter("hash");
        
        if(username.equals("") || password.equals("") || name.equals("") || phone.equals("")){
            response.sendRedirect("settings?failed=true");
        } 
        else if (!checkIfPhoneTaken(username, phone)){
            update(username, password, name, phone, hash);
            HttpSession session = request.getSession();
            session.setAttribute("user", userService.find(username));
            response.sendRedirect("settings?updated=true");
        }
        else{
            response.sendRedirect("settings?taken=true");
        }
    }

    private void update(String username, String password, String name, String phone, String hash) {
        User user = userService.find(username);
        user.setMail(username);
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setApi(hash);
        
        userService.update(user);
    }

    private boolean checkIfPhoneTaken(String username, String phone) {
        User currentUser = userService.find(username);
        User userPhone = userService.findByPhone(phone);
               
        return userPhone != null && !userPhone.getMail().equals(currentUser.getMail());
    }

}
