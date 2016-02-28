/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marcus
 * @param <T> Type
 * @param <K> Primary Key
 */
public abstract class DAO<T, K> {

    private final Class<T> typeClass;

    protected abstract EntityManager getEntityManager();

    protected DAO(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public void create(T t) {
       this.getEntityManager().persist(t);
    }

    public void delete(K id) {
        T t = getEntityManager().getReference(typeClass, id);
        getEntityManager().remove(t);
    }

    public void update(T t) {
        getEntityManager().merge(t);
    }

    public T find(K id) {
       return getEntityManager().find(typeClass, id);
    }

    public List<T> findAll() {
       return get(true, -1, -1);
    }

    public List<T> findRange(int first, int n) {
        return get(false, first, n);
    }

    private List<T> get(boolean all, int first, int n) {
        EntityManager em = getEntityManager();
        List<T> found = new ArrayList<>();
        TypedQuery<T> q = em.createQuery("select t from " + typeClass.getSimpleName() + " t", typeClass);
        if (!all) {
            q.setFirstResult(first);
            q.setMaxResults(n);
        }
        found.addAll(q.getResultList());
        return found;
    }

    public int count() {
        EntityManager em = getEntityManager();
        Long n = em.createQuery("select count(t) from " + typeClass.getSimpleName() + " t", Long.class)
                .getSingleResult();
        return n.intValue();
    }
}

