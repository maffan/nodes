/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Collection;
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
import services.CollectionService;
import services.DatapointService;
import services.NodeService;
import websocket.WebsocketSessionHandler;

/**
 *
 * @author flycktm
 */
@Stateless
@Path("datapoint")
public class DatapointFacadeREST extends DAO<Datapoint, DatapointPK> {

    @PersistenceContext(unitName = "se.nomorebagels_nodes_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;

    @Inject
    private NodeService nodeService;

    @Inject
    private DatapointService datapointService;

    @Inject
    private CollectionService collectionService;

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
        sessionHandler.messageAll(nodeService.find(nodePK));
    }

    @GET
    @Path("{owner}/{node}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Datapoint> findAllForNode(@PathParam("owner") String owner, @PathParam("node") String node) {

        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if (fetchedNode != null) {
            return datapointService.getLatestForNode(fetchedNode, 100, true);
        } else {
            return new ArrayList();
        }
    }
    
    @GET
    @Path("{owner}/{node}/latest")
    @Produces({MediaType.APPLICATION_JSON})
    public String findLatestForNode(@PathParam("owner") String owner, @PathParam("node") String node) {

        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if (fetchedNode != null) {
            return String.format("%d", datapointService.getLatestForNode(fetchedNode));
        } else {
            return "0";
        }
    }

    @GET
    @Path("{owner}/{node}/{hours}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Datapoint> findLastDaysForNode(@PathParam("owner") String owner, @PathParam("node") String node, @PathParam("hours") int hours) {
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if (fetchedNode != null) {
            Date now = new Date();
            Date past = getCorrectedDate(now, hours);
            return datapointService.getForNodeBetweenDates(fetchedNode, past, now);
        } else {
            return new ArrayList();
        }
    }

    private Date getCorrectedDate(Date now, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR, -hours);
        Date past = calendar.getTime();
        return past;
    }

    @GET
    @Path("collection/{collectionId}/average")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAverageValueForCollection(@PathParam("collectionId") int collectionId) {
        Collection collection = collectionService.find(collectionId);
        if (collection == null) {
            return "false";
        }
        return String.format("%.2f", datapointService.getAverageForCollection(collection, 100));
    }
    
    @GET
    @Path("collection/{collectionId}/average/{hours}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAverageValueForCollection(@PathParam("collectionId") int collectionId, @PathParam("hours") int hours) {
        Collection collection = collectionService.find(collectionId);
        Date now = new Date();
        Date past = getCorrectedDate(now, hours);
        if (collection == null) {
            return "false";
        }
        return String.format("%.2f", datapointService.getAverageForCollection(collection, past, now));
    }

    @GET
    @Path("collection/{collectionId}/max")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMaxValueForCollection(@PathParam("collectionId") int collectionId) {
        Collection collection = collectionService.find(collectionId);
        if (collection == null) {
            return "false";
        }
        return String.format("%d", datapointService.getMaxForCollection(collection, 100));
    }

    @GET
    @Path("collection/{collectionId}/min")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMinValueForCollection(@PathParam("collectionId") int collectionId) {
        Collection collection = collectionService.find(collectionId);
        if (collection == null) {
            return "false";
        }
        return String.format("%d", datapointService.getMinForCollection(collection, 100));
    }

    @GET
    @Path("{owner}/{node}/average")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAverageForNode(@PathParam("owner") String owner, @PathParam("node") String node) {
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if (fetchedNode != null) {
            return String.format("%.2f", datapointService.getAverageForNode(fetchedNode, 100));
        } else {
            return "false";
        }
    }
    
    @GET
    @Path("{owner}/{node}/average/{hours}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAverageForNode(@PathParam("owner") String owner, @PathParam("node") String node, @PathParam("hours") int hours){
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if (fetchedNode != null) {
            Date now = new Date();
            Date past = getCorrectedDate(now, hours);
            return String.format("%.2f", datapointService.getAverageForNode(fetchedNode, past, now));
        } else {
            return "false";
        }
    }

    @GET
    @Path("{owner}/{node}/max")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMaxForNode(@PathParam("owner") String owner, @PathParam("node") String node) {
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if (fetchedNode != null) {
            return String.format("%d", datapointService.getMaxForNode(fetchedNode, 100));
        } else {
            return "false";
        }
    }
    
    @GET
    @Path("{owner}/{node}/max/{hours}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMaxForNode(@PathParam("owner") String owner, @PathParam("node") String node, @PathParam("hours") int hours) {
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        Date now = new Date();
        Date past = getCorrectedDate(now, hours);
        if (fetchedNode != null) {
            return String.format("%d", datapointService.getMaxForNode(fetchedNode, past, now));
        } else {
            return "false";
        }
    }

    @GET
    @Path("{owner}/{node}/min")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMinForNode(@PathParam("owner") String owner, @PathParam("node") String node) {
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        if (fetchedNode != null) {
            return String.format("%d", datapointService.getMinForNode(fetchedNode, 100));
        } else {
            return "false";
        }
    }
    
    @GET
    @Path("{owner}/{node}/min/{hours}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMinForNode(@PathParam("owner") String owner, @PathParam("node") String node, @PathParam("hours") int hours) {
        Node fetchedNode = nodeService.find(new NodePK(node, owner));
        Date now = new Date();
        Date past = getCorrectedDate(now, hours);
        if (fetchedNode != null) {
            return String.format("%d", datapointService.getMinForNode(fetchedNode, past, now));
        } else {
            return "false";
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

}
