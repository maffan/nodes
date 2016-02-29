package services;

import entities.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DAO;

@Stateless
public class CollectionService extends DAO<Collection, Integer>{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public CollectionService(){
        super(Collection.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
