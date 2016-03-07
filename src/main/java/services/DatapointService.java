package services;

import entities.Collection;
import entities.Datapoint;
import entities.DatapointPK;
import entities.Node;
import entities.NodePK;
import entities.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    
    public List<Datapoint> getForNodeAfterDate(Node node, Date first, Date second){
        TypedQuery<Datapoint> query = entityManager.createQuery(
                "SELECT dp FROM Datapoint dp WHERE"
                        + " dp.datapointPK.node = :node AND"
                        + " dp.datapointPK.owner = :owner AND"
                        + " dp.datapointPK.time BETWEEN :first AND :second", Datapoint.class);
        query.setParameter("node", node.getNodePK().getName());
        query.setParameter("owner", node.getNodePK().getOwner());
        query.setParameter("first", first);
        query.setParameter("second", second);
        return query.getResultList();
    }
}
