/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;

/**
 *
 * Connection endpoint for websockets needing information about data for a 
 * single node.
 */
@ServerEndpoint("/websocket/{owner}/{node}")
@ApplicationScoped
public class NodeDataSocketEndpoint {
    
    @Inject
    WebsocketSessionHandler sessionHandler;

    @OnOpen
    public void open(Session session, @PathParam("owner") String owner, @PathParam("node") String node){
        sessionHandler.addSession(session, owner, node);       
    }
    
    @OnClose
    public void close(Session session, @PathParam("owner") String owner, @PathParam("node") String node){
        sessionHandler.removeSession(session, owner, node);
    }
    
    @OnMessage
    public String onMessage(String message) {
        return null;
    }
    
}
