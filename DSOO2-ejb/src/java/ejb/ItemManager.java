/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcoslaydner
 */
@Stateless
@LocalBean
public class ItemManager {

     @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    public List<Item> getAllFuncionarios() {
        Query query = em.createNamedQuery("HotelFuncionarios.findAll");
        return query.getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Item getById(String id) {
        Query query = em.createNamedQuery("HotelFuncionarios.findById").setParameter("id", id);
        
        List<Item> list = query.getResultList();
        
        if(list.isEmpty()) {
            return null;
        }else{
            return list.get(0);
        }
    }

}
