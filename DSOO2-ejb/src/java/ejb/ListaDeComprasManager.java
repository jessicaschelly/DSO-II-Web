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
public class ListaDeComprasManager {


    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public void create(Object object) {
        em.persist(object);
        em.flush();
    }

  
    public void update(Produto quarto) {
        em.merge(quarto);
        em.flush();
    }
    
    
    public List<ListaDeCompras> getAllListasByUser(long idUsuario) {
        Query query = em.createNamedQuery("ListaDeCompras.findAll");
        List<ListaDeCompras> result = query.getResultList();
        return result;
    } 
    
    public int getMaxNumero() {  // pega o maior ID de cliente na tabela
        Query query = em.createNativeQuery("SELECT MAX(numero) FROM QUARTOS");
        return (Integer) query.getSingleResult();
    }
 
    public void cadastrarQuarto(Produto hQuartos) {
        em.persist(hQuartos);
    }

    public void refresh(Usuario usuarioLogado) {
        em.refresh(usuarioLogado);
    }
  
}
