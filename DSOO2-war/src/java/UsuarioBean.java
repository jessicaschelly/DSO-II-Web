/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UsuarioManager;
import ejb.ProdutoManager;
import ejb.Usuario;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcoslaydner
 */
@Named(value = "ClienteMBean")
@RequestScoped
public class UsuarioBean {

    @EJB
    private UsuarioManager cliente;

    public void checkOut() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object att = session.getAttribute("hq");
        Usuario hq = (Usuario) att;
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        eContext.redirect("menuFuncionario.xhtml");

    }

    public void cadastrarUsuario() throws IOException {
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();

        cliente.persist(usuario);
        eContext.redirect("checkIn.xhtml");

    }

    public String cpfJahExiste() {
        if (cliente.getByCpf(cpf) != null) {
            return "";
        } else {
            return "CPF Já Cadastrado!";
        }
    }

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaClientes() {
        return cliente.getAllClients();
    }

    public String getSenhaResponse() throws Exception {

        if (senhaCorreta(hCliente)) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.setAttribute("hc", this.hCliente);

            ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
            eContext.redirect("menuCliente.xhtml");
            return "existe";
        } else {
            return "nao existe";
        }
    }

    public boolean senhaCorreta(Usuario hc) {
        if (hc == null || senha == null) {
            return false;
        } else {
            return senha.equals(hc.getSenha());
        }
    }

    public void salvarCliente() {
        if (hCliente != null) {
            cliente.salvarCliente(this.hCliente);
        }

    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public UsuarioBean() {
    }

    public void cadastrarCI() throws IOException {
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        eContext.redirect("cadastroCliente.xhtml");

    }

    private Usuario setHCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object att = session.getAttribute("hc");
        if (att != null) {
            return (Usuario) att;
        } else {
            return null;
        }
    }

}
