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


@Named(value = "loginUsuario")
@SessionScoped
public class LoginUsuario implements Serializable {

    @EJB
    private UsuarioManager manager;

    private Usuario usuarioLogado;
    private Usuario usuario = new Usuario();
    private List<String> erros = new ArrayList<>();
    private long idListaAtual;

    public LoginUsuario() {
    }

    public String login() throws IOException {
        erros.clear();

        Usuario x = manager.getByEmail(usuario.getEmail());
        
        if (x != null) {
            if (!x.getSenha().equals(usuario.getSenha())){
                erros.add("Senha incorreta");    
                return null;
            }
            this.setUsuarioLogado(x);
            return "listarListas";
        } else {
            erros.add("Usuário não encontrado");
            return null;
        }

    }

    public String logout() {
        usuarioLogado = null;
        return "index";
    }

    public String getErros() {
        return String.join("\n", erros);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public long getIdUsuarioLogado() {
        return usuarioLogado.getId();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getIdListaAtual() {
        return idListaAtual;
    }

    public void setIdListaAtual(long idListaAtual) {
        this.idListaAtual = idListaAtual;
    }

}
