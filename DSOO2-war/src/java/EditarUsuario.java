/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.ListaDeComprasManager;
import ejb.UsuarioManager;
import ejb.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@Named(value = "editarUsuario")
@RequestScoped
public class EditarUsuario {

    @EJB
    private UsuarioManager usuarioManager;
    @Inject
    private LoginUsuario loginUsuario;

    public String salvar() throws IOException {
        Usuario managed = usuarioManager.getById(loginUsuario.getIdUsuarioLogado());

        managed.setNome(loginUsuario.getUsuarioLogado().getNome());
        managed.setSenha(loginUsuario.getUsuarioLogado().getSenha());
        managed.setTelefone(loginUsuario.getUsuarioLogado().getTelefone());

        usuarioManager.update(managed);

        return "listarListas";
    }

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EditarUsuario() {

    }

}
