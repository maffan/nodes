/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import filters.HomeRouter;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.CollectionService;
import services.NodeService;
import services.UserService;

/**
 *
 * @author Marcus
 */
@WebServlet(name = "NodeServlet", urlPatterns = {"/nodes"})
public class NodeServlet extends HttpServlet {
@Inject
UserService userService;
@Inject
NodeService nodeService;
@Inject
CollectionService collectionService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher(HomeRouter.TEMPLATE + "?partial=nodes").forward(request, response);
    }

}
