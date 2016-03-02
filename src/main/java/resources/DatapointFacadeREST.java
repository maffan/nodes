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
import services.NodeService;

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

    public DatapointFacadeREST() {
        super(Datapoint.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Datapoint entity) {
        entity.getDatapointPK().setTime(new Date());
        super.create(entity);
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
    

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    
}
