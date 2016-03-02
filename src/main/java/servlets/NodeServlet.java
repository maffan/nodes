/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Collection;
import entities.Node;
import entities.NodePK;
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username;
        String node;
        String collection;
        String msg;
        NodePK nodeKey;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        try{
            switch(action){
            case "createnode":
                username = request.getParameter("username");
                node = request.getParameter("newnodename");
                Node newNode = new Node(node, username);
                nodeService.create(newNode);
                
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            case "createcollection":
                username = request.getParameter("username");
                collection = request.getParameter("newcollectioname");
                Collection newCol = new Collection();
                newCol.setOwner(userService.find(username));
                newCol.setName(collection);
                collectionService.create(newCol);
                            
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            case "deletenode":
                username = request.getParameter("username");
                node = request.getParameter("value");
                nodeKey = new NodePK(node, username);
                nodeService.delete(nodeKey);
                
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            case "deletecollection":
                username = request.getParameter("username");
                collection = request.getParameter("value");
                collectionService.delete(Integer.valueOf(collection));
                
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            case "addtocollection":
                username = request.getParameter("username");
                node = request.getParameter("value");
                collection = request.getParameter("value2");
                
                nodeKey = new NodePK(node, username);
                Node n = nodeService.find(nodeKey);
                
                removeFromCollections(nodeKey);
                                
                //Add node to new collection
                Collection col = collectionService.find(Integer.valueOf(collection));
                col.getNodeList().add(n);
                collectionService.update(col);
                
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            case "removefromcollection":
                username = request.getParameter("username");
                node = request.getParameter("value");
                
                removeFromCollections(node, username);
                
                session.setAttribute("user", userService.find(username));
                response.sendRedirect("nodes");
                break;
            default:
                break;
            }
            
        }catch(Exception e){
            response.sendRedirect("nodes");
        }

    }
    
    private void removeFromCollections(NodePK key){
        removeFromCollections(key.getName(), key.getOwner());
    }
    
    private void removeFromCollections(String node, String username){
        //Find current node and collections it belongs to
        NodePK nodeKey = new NodePK(node, username);
        Node n = nodeService.find(nodeKey);
        List collectionList = n.getCollectionList();

        //Remove node from all previous nodelists
        if(!collectionList.isEmpty()){
            for(Collection c : n.getCollectionList()){
                c.getNodeList().remove(n);
                collectionService.update(c);
            }
        }
    }
    
    

}
