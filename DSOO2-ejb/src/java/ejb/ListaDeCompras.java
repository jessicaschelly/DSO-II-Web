package ejb;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "ListaDeCompras.findAll", query = "SELECT l FROM ListaDeCompras l")
    ,
    @NamedQuery(name = "ListaDeCompras.findById", query = "SELECT l FROM ListaDeCompras l where l.id = :id")})
public class ListaDeCompras implements Serializable {

    @ManyToMany(mappedBy = "listasDeCompras", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Item> itens;

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public String getUsuariosNames() {
        if (this.usuarios != null && this.usuarios.size() > 0) {
            return this.usuarios.stream().map(o -> o.getNome()).collect(Collectors.joining(", "));
        }
        return "Nenhum";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaDeCompras)) {
            return false;
        }
        ListaDeCompras other = (ListaDeCompras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.trabalho.entidades.ListaDeCompras[ id=" + id + " ]";
    }

}
