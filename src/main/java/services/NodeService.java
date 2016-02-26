/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Node;
import entities.NodePK;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DAO;

/**
 *
 * @author Marcus
 */
@Stateless
public class NodeService extends DAO<Node,NodePK>{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public NodeService(){
        super(Node.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
