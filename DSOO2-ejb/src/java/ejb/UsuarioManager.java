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
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author marcoslaydner
 */
@Stateless
@LocalBean
public class UsuarioManager {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public void create(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Usuario> getAllClients() {
        Query query = em.createNamedQuery("HotelClientes.findAll");
        return query.getResultList();
    }

    public Usuario getByEmail(String email) {
        Query query = em.createNamedQuery("Usuario.findByEmail").setParameter("email", email);
        
        List list = query.getResultList();
        if (list.isEmpty()){
            return null;
        }
        return (Usuario) list.get(0);
    }

    public Usuario update(Usuario cliente) {
        Usuario u = em.merge(cliente);
        em.flush();
        return u;
    }

    public Usuario getById(long id) {
        return em.find(Usuario.class, id);
    }

}
