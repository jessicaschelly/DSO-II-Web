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
public class UsuarioManager {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Usuario> getAllClients() {
        Query query = em.createNamedQuery("HotelClientes.findAll");
        return query.getResultList();
    }
    
    public Usuario getByQuarto(int quarto) { 
        Query query = em.createNamedQuery("HotelClientes.findByQuarto").setParameter("quarto", quarto);
        
        List<Usuario> list = query.getResultList();
        
        if (list.isEmpty()) 
            return null;
        else 
            return list.get(0);
    }
    
    public Usuario getByCpf(long cpf) {
        Query query = em.createNamedQuery("HotelClientes.findByCpf").setParameter("cpf", cpf);
        
        List<Usuario> list = query.getResultList();
        
        if (list.isEmpty()) 
            return null;
        else 
            return list.get(0);

    }

    public void salvarCliente(Usuario cliente) {
        em.merge(cliente);
        em.flush();
    }
    
    
    
    
}