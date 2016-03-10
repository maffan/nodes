package services;

import entities.Collection;
import entities.Datapoint;
import entities.DatapointPK;
import entities.Node;
import entities.NodePK;
import entities.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import persistence.DAO;

@Stateless
public class DatapointService extends DAO<Datapoint, DatapointPK> {

    @PersistenceContext
    private EntityManager entityManager;

    public DatapointService() {
        super(Datapoint.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    /*
    Removes all datapoints related to the given user
    */
    public void deleteForUser(User user) {
        entityManager.createQuery("delete from Datapoint t where t.datapointPK.owner = " + user.getMail() + "").executeUpdate();
    }

    /*
    Removes all datapoints related to the given node
    */
    public void deleteForNode(Node node) {
        for (Datapoint dp : node.getDatapointList()) {
            this.delete(dp.getDatapointPK());
        }
    }
    /*
    Returns all datapoints reported by a node between two given dates
    */
    public List<Datapoint> getForNodeBetweenDates(Node node, Date first, Date second) {
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

    /*
    Returns a given amount of the latest reported datapoints for a given node.
    Returned list may be reversed to make it easier to present in front-end.
    */
    public List<Datapoint> getLatestForNode(Node node, int amount, boolean reversed) {
        TypedQuery<Datapoint> query = entityManager.createQuery("SELECT dp FROM Datapoint dp WHERE dp.datapointPK.node = :node AND dp.datapointPK.owner = :owner ORDER BY dp.datapointPK.time DESC", Datapoint.class);
        query.setParameter("node", node.getNodePK().getName());
        query.setParameter("owner", node.getNodePK().getOwner());
        query.setMaxResults(amount);
        List<Datapoint> result = query.getResultList();
        if(reversed)
            Collections.reverse(result);
        return result;
    }
    /*
    Returns the average value for a given List of datapoints
    */
    public double getAverageForDatapoints(List<Datapoint> datapoints){
        List<Integer> listOfAverages = new ArrayList<>();
        for(Datapoint datapoint : datapoints){
            listOfAverages.add(datapoint.getData());
        }
        int sum = 0;
        for(Integer value : listOfAverages){
            sum += value;
        }
        if(!listOfAverages.isEmpty())
            return (double)sum / (double)listOfAverages.size();
        else
            return 0;
    }
    
    /*
    Returns the average value for a given amount of latest reported values by a node.
    */
    public double getAverageForNode(Node node, int amount){
        return getAverageForDatapoints(getLatestForNode(node, amount, false));
    }
    
    /*
    Returns the average value for a node reported between two given dates.
    */
    public double getAverageForNode(Node node, Date first, Date second){
        return getAverageForDatapoints(getForNodeBetweenDates(node, first, second));
    }

    /*
    Returns the aggregated average value for a given amount of latest reported
    values by all nodes in a collection
    */
    public double getAverageForCollection(Collection collection, int amount) {
        List<Double> listOfAverages = new ArrayList<>();
        for(Node node : collection.getNodeList()){
            listOfAverages.add(getAverageForNode(node, amount));
        }
        double sum = 0;
        for(Double value : listOfAverages){
            sum += value;
        }
        if(!listOfAverages.isEmpty())
            return sum / (double)listOfAverages.size();
        else
            return 0;
    }
    
    /*
    Returns the aggregated average value reported by all nodes in a collection 
    between two dates.
    */
    public double getAverageForCollection(Collection collection, Date past, Date now) {
        List<Double> listOfAverages = new ArrayList<>();
        for(Node node : collection.getNodeList()){
            listOfAverages.add(getAverageForNode(node, past, now));
        }
        double sum = 0;
        for(Double value : listOfAverages){
            sum += value;
        }
        if(!listOfAverages.isEmpty())
            return sum / (double)listOfAverages.size();
        else
            return 0;
    }

    /*
    Returns the aggregated maximum value for a given amount of latest reported
    values by all nodes in a collection
    */
    public int getMaxForCollection(Collection collection, int amount) {
        List<Integer> listOfMaxes = new ArrayList<>();
        for(Node node : collection.getNodeList()){
            listOfMaxes.add(getMaxForNode(node, amount));
        }
        int max = Integer.MIN_VALUE;
        for(Integer value : listOfMaxes){
            max = value > max ? value : max;
        }
        return max;
    }

    /*
    Returns the aggregated minimum value for a given amount of latest reported
    values by all nodes in a collection
    */
    public int getMinForCollection(Collection collection, int amount) {
        List<Integer> listOfMaxes = new ArrayList<>();
        for(Node node : collection.getNodeList()){
            listOfMaxes.add(getMinForNode(node, amount));
        }
        int min = Integer.MAX_VALUE;
        for(Integer value : listOfMaxes){
            min = value < min ? value : min;
        }
        return min;
    }

    /*
    Returns the maximum value for a given amount of latest reported values
    by a node.
    */
    public Integer getMaxForNode(Node node, int amount) {
        int max = Integer.MIN_VALUE;
        for(Datapoint datapoint : getLatestForNode(node, amount, false)){
            max = datapoint.getData() > max ? datapoint.getData() : max;
        }
        return max;
    }
    
    /*
    Return the maximum value reported by a node between two given dates
    */
    public int getMaxForNode(Node fetchedNode, Date past, Date now) {
        int max = Integer.MIN_VALUE;
        for(Datapoint datapoint : getForNodeBetweenDates(fetchedNode, past, now)){
            max = datapoint.getData() > max ? datapoint.getData() : max;
        }
        return max;
    }

    /*
    Returns the minimum value for a given amount of latest reported values
    by a node.
    */
    public Integer getMinForNode(Node node, int amount) {
        int min = Integer.MAX_VALUE;
        for(Datapoint datapoint : getLatestForNode(node, amount, false)){
            min = datapoint.getData() < min ? datapoint.getData() : min;
        }
        return min;
    }

    /*
    Return the minimum value reported by a node between two given dates
    */
    public int getMinForNode(Node fetchedNode, Date past, Date now) {
        int min = Integer.MAX_VALUE;
        for(Datapoint datapoint : getForNodeBetweenDates(fetchedNode, past, now)){
            min = datapoint.getData() < min ? datapoint.getData() : min;
        }
        return min;
    }
    
    /*
    Returns the latest reported value reported by a node. If no data has been 
    reported, 0 is returned.
    */
    public int getLatestForNode(Node node) {
        List<Datapoint> latestDataPoint = getLatestForNode(node, 1, false);
        return latestDataPoint.size() > 0 ? latestDataPoint.get(0).getData() : 0;
    }

    

    
    
}
