/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.Collection;
import entities.Node;
import entities.NodePK;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

/*
An application scoped class for handling all messages being sent to connected
websockets.

Sessions can be related to a specifik node or collection
*/
@ApplicationScoped
public class WebsocketSessionHandler {
    private final Map<NodePK,List<Session>> nodeSessions;
    private final Map<Integer,List<Session>> collectionSessions;
    
    public WebsocketSessionHandler(){
        this.nodeSessions = new HashMap<>();
        this.collectionSessions = new HashMap<>();
    }
    /*
    Relates a session to a Node
    */
    public void addSession(Session session, String owner, String node){
        NodePK nodeKey = new NodePK(node, owner);
       if(nodeSessions.containsKey(nodeKey)){
            nodeSessions.get(nodeKey).add(session);
        }
        else{
            ArrayList sessionList = new ArrayList<>();
            sessionList.add(session);
            nodeSessions.put(nodeKey, sessionList);
        }
    }
    /*
    Relates a session to a collection
    */
    public void addSession(Session session, int collectionId){
       if(collectionSessions.containsKey(collectionId)){
            collectionSessions.get(collectionId).add(session);
        }
        else{
            ArrayList sessionList = new ArrayList<>();
            sessionList.add(session);
            collectionSessions.put(collectionId, sessionList);
        }
    }
    
    public void removeSession(Session session,String owner, String node){
       NodePK nodeKey = new NodePK(node, owner);
        if(nodeSessions.containsKey(nodeKey)){
            nodeSessions.get(nodeKey).remove(session);
        }
    }
    
    public void removeSession(Session session, int collectionId){
        if(collectionSessions.containsKey(collectionId)){
            collectionSessions.get(collectionId).remove(session);
        }
    }

    /*
    Sends a simple message to all sessions related to the node or a collection
    containing the node.
    */
    public void messageAll(Node node) {
        if(nodeSessions.containsKey(node.getNodePK())){
            for(Session session : nodeSessions.get(node.getNodePK())){
                if(session != null)
                    session.getAsyncRemote().sendText("New data available");
            }
        }
        List<Collection> collectionList = node.getCollectionList();
        for(Collection collection : collectionList){
            if(collectionSessions.containsKey(collection.getId())){
                for(Session session : collectionSessions.get(collection.getId())){
                    if(session != null)
                        session.getAsyncRemote().sendText("New data available");
                }
            }
        }
    }
}
