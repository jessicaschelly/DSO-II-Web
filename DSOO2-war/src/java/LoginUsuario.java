/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UsuarioManager;
import ejb.ProdutoManager;
import ejb.Usuario;
import java.io.IOException;
import java.util.ArrayList;
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
@Named(value = "LoginUsuario")
@RequestScoped
public class LoginUsuario {

    @EJB
    private UsuarioManager manager;

    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
    ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();

    public void login() throws IOException {

        Usuario x = manager.getByEmail(usuario.getEmail());

        if (x != null) {
            session.setAttribute("usuarioLogado", x);
            eContext.redirect("listarListas.xhtml");
        } else {
            erros.add("Usuário não encontrado");
        }

    }

    private List<String> erros = new ArrayList<>();

    public String getErros() {
        return String.join("\n", erros);
    }

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LoginUsuario() {
    }

}
