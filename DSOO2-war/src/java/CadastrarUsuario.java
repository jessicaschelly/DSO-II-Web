/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UsuarioManager;
import ejb.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



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

}
