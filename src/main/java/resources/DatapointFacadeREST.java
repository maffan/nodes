/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Datapoint;
import entities.DatapointPK;
import entities.Node;
import entities.NodePK;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import persistence.DAO;
import services.DatapointService;
import services.NodeService;
import websocket.WebsocketSessionHandler;

/**
 *
 * @author flycktm
 */
@Stateless
@Path("datapoint")
public class DatapointFacadeREST extends DAO<Datapoint,DatapointPK> {

    @PersistenceContext(unitName = "se.nomorebagels_nodes_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private NodeService nodeService;
    
    @Inject
    private DatapointService datapointService;
    
    @Inject
    private WebsocketSessionHandler sessionHandler;

    public DatapointFacadeREST() {
        super(Datapoint.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Datapoint entity) {
        entity.getDatapointPK().setTime(new Date());
        NodePK nodePK = new NodePK(entity.getDatapointPK().getNode(), entity.getDatapointPK().getOwner());
        super.create(entity);
        sessionHandler.messageAll(nodePK);
    }
    
    @GET
    @Path("{owner}/{node}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Datapoint> findAllForNode(@PathParam("owner") String owner, @PathParam("node") String node) {

        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if(fetchedNode != null)
            return fetchedNode.getDatapointList();
        else
            return new ArrayList();
    }
    
    @GET
    @Path("{owner}/{node}/{hours}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Datapoint> findLastDaysForNode(@PathParam("owner") String owner, @PathParam("node") String node, @PathParam("hours") int hours){
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if(fetchedNode != null){
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.HOUR, -hours);
            Date past = calendar.getTime();
            return datapointService.getForNodeAfterDate(fetchedNode, past, now);
        }
        else
            return new ArrayList();       
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    
}
