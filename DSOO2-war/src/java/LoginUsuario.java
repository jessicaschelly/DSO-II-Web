/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UsuarioManager;
import ejb.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.SessionBean;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcoslaydner
 */
@Named(value = "loginUsuario")
@SessionScoped
public class LoginUsuario implements Serializable {

    @EJB
    private UsuarioManager manager; 
    
    ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
    private Usuario usuarioLogado;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    public long getIdUsuarioLogado(){
        return usuarioLogado.getId();
    }

    
    public String login() throws IOException {

        Usuario x = manager.getByEmail(usuario.getEmail());

        if (x != null) {
            this.setUsuarioLogado(x);
//            eContext.redirect("listarListas.xhtml");
            return "listarListas";
        } else {
            erros.add("Usuário não encontrado");
            return null;
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
        usuario.setEmail("gui");
        usuario.setSenha("123");
    }

}
