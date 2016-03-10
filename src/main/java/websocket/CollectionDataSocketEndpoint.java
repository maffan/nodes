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
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/*
* Connection endpoint for websockets needing information about data in 
* collections.
*/
@ServerEndpoint("/websocket/collection/{collectionId}")
@ApplicationScoped
public class CollectionDataSocketEndpoint {

    @Inject
    WebsocketSessionHandler sessionHandler;

    @OnOpen
    public void open(Session session, @PathParam("collectionId") int collectionId){
        sessionHandler.addSession(session, collectionId);       
    }
    
    @OnClose
    public void close(Session session, @PathParam("collectionId") int collectionId){
        sessionHandler.removeSession(session, collectionId);
    }
    
    @OnMessage
    public String onMessage(String message) {
        return null;
    }
    
}
