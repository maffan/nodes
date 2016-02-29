/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.Datapoint;
import entities.DatapointPK;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import persistence.DAO;

/**
 *
 * @author flycktm
 */
@Stateless
@Path("datapoint")
public class DatapointFacadeREST extends DAO<Datapoint,DatapointPK> {

    @PersistenceContext(unitName = "se.nomorebagels_nodes_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;


    public DatapointFacadeREST() {
        super(Datapoint.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Datapoint entity) {
        entity.getDatapointPK().setTime(new Date());
        super.create(entity);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    
}
