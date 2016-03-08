/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Collection;
import entities.Module;
import entities.User;
import filters.HomeRouter;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CollectionService;
import services.ModuleService;
import services.UserService;

/**
 *
 * @author Marcus
 */
@WebServlet(name = "ModuleServlet", urlPatterns = {"/modules"})
public class ModuleServlet extends HttpServlet {
@Inject
UserService userService;
@Inject
CollectionService collectionService;
@Inject
ModuleService moduleService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
            request.setAttribute("modules", moduleService.findAll());
            request.getRequestDispatcher(HomeRouter.TEMPLATE + "?partial=modules").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username;
        String collection;
        String module;
        Collection col;
        List<Module> colModules;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        try{
            switch(action){
            case "addmodule":
                //Fetch variables from post
                username = request.getParameter("username");
                collection = request.getParameter("collection");
                module = request.getParameter("module");
                
                //Update modules in collection
                col = collectionService.find(Integer.valueOf(collection));
                colModules = col.getModuleList();
                colModules.add(moduleService.find(module));
                col.setModuleList(colModules);
                collectionService.update(col);
                
                //Update user
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            case "removemodule":
                //Fetch variables from post
                username = request.getParameter("username");
                collection = request.getParameter("collection");
                module = request.getParameter("module");
                
                //Update modules in collection
                col = collectionService.find(Integer.valueOf(collection));
                colModules = col.getModuleList();
                colModules.remove(moduleService.find(module));
                col.setModuleList(colModules);
                collectionService.update(col);
                            
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            default:
                break;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("nodes");
        }

    }  
    

}
