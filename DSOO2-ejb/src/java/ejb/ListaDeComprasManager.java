package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ListaDeComprasManager {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public void create(Object object) {
        em.persist(object);
        em.flush();
    }
    
     public ListaDeCompras update(ListaDeCompras object) {
        ListaDeCompras x = em.merge(object);
        em.flush();
        return x;
    }

    public List<ListaDeCompras> getAllListasByUser(long idUsuario) {
        Query query = em.createNamedQuery("ListaDeCompras.findAll");
        List<ListaDeCompras> result = query.getResultList();
        List<ListaDeCompras> nova = new ArrayList<>();
        for (ListaDeCompras x : result) {
            em.refresh(x);

            for (Usuario u : x.getUsuarios()) {
                if (u.getId() == idUsuario) {
                    nova.add(x);

                    break;

                }
            }
        }
        return nova;
    }

    public ListaDeCompras getListaById(long id) {
        Query query = em.createNamedQuery("ListaDeCompras.findById");
        query.setParameter("id", id);

        List<ListaDeCompras> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        ListaDeCompras xa = list.get(0);

        em.refresh(xa);
        return xa;

    }

    public void refresh(Usuario usuarioLogado) {
        em.refresh(usuarioLogado);
    }

}
