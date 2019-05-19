/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.ListaDeCompras;
import ejb.ListaDeComprasManager;
import ejb.UsuarioManager;
import ejb.ProdutoManager;
import ejb.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcoslaydner
 */
@Named(value = "ListarListas")
@RequestScoped
public class ListarListas {

    @EJB
    private UsuarioManager usuarioManager;
    
    @EJB
    private ListaDeComprasManager listaDeComprasManager;
     
    public ListarListas() {
    }
    
    public List<ListaDeCompras> getListasDeCompras(){
        return listaDeComprasManager.getAllListasByUser(loginUsuario.getUsuarioLogado().getId());
    }
    
    @Inject
    private LoginUsuario loginUsuario;

    public void criarLista(){
        Usuario managed = usuarioManager.getById(loginUsuario.getIdUsuarioLogado());

        ListaDeCompras listaDeCompras = new ListaDeCompras();         
        managed.getListasDeCompras().add(listaDeCompras);
        
        loginUsuario.setUsuarioLogado(usuarioManager.update(managed));
//        listaDeCompras.getUsuarios().add(loginUsuario.getUsuarioLogado());;
//        listaDeComprasManager.create(listaDeCompras);
    }

}
