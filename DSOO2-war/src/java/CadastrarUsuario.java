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
@Named(value = "CadastrarUsuario")
@RequestScoped
public class CadastrarUsuario {

    @EJB
    private UsuarioManager cliente;

    public void cadastrarUsuario() throws IOException {
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        cliente.create(usuario);
        eContext.redirect("loginUsuario.xhtml");
    }

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CadastrarUsuario() {
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

    private String getSenhaResponse() throws Exception {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.setAttribute("hc", null);

        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        eContext.redirect("menuCliente.xhtml");
        return "existe";

    }

}
