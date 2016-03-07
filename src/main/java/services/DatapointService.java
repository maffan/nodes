package services;

import entities.Collection;
import entities.Datapoint;
import entities.DatapointPK;
import entities.Node;
import entities.NodePK;
import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.DAO;

@Stateless
public class DatapointService extends DAO<Datapoint, DatapointPK>{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public DatapointService(){
        super(Datapoint.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
    
    public void deleteForUser(User user){
        entityManager.createQuery("delete from Datapoint t where t.datapointPK.owner = " + user.getMail() + "").executeUpdate();
    }
    
    public void deleteForNode(Node node){
        for(Datapoint dp : node.getDatapointList()){
            this.delete(dp.getDatapointPK());
        }
    }
}
