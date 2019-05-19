package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class UsuarioManager {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public void create(Object object) {
        em.persist(object);
    }

    public List<Usuario> getAllClients() {
        Query query = em.createNamedQuery("Usuario.findAll");
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
