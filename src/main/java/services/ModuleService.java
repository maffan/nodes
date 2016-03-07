/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Module;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DAO;

/**
 *
 * @author maffan
 */
@Stateless
public class ModuleService extends DAO<Module, String>{
    @PersistenceContext
    EntityManager entityManager;

    public ModuleService(){
        super(Module.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
    
    public 
    
}
