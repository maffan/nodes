/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DAO;

/**
 *
 * @author Marcus
 */
@Stateless
public class UserService extends DAO<User,String>{

    @PersistenceContext
    EntityManager entityManager;
    
    public UserService(){
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
