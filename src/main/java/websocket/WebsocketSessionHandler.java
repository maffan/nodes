/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.DatapointPK;
import entities.NodePK;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class WebsocketSessionHandler {
    private final Map<NodePK,List<Session>> sessions;
    
    public WebsocketSessionHandler(){
        //this.sessions = new HashMap<>();
        this.sessions = new HashMap<>();
    }
    
    public void addSession(Session session, String owner, String node){
        NodePK nodeKey = new NodePK(node, owner);
       if(sessions.containsKey(nodeKey)){
            sessions.get(nodeKey).add(session);
        }
        else{
            ArrayList sessionList = new ArrayList<>();
            sessionList.add(session);
            sessions.put(nodeKey, sessionList);
        }
    }
    
    public void removeSession(Session session,String owner, String node){
       NodePK nodeKey = new NodePK(node, owner);
        if(sessions.containsKey(nodeKey)){
            sessions.get(nodeKey).remove(session);
        }
    }

    public void messageAll(NodePK nodePK) {
        for(Session session : sessions.get(nodePK)){
            if(session != null)
                session.getAsyncRemote().sendText("New data available");
        }
    }
}
