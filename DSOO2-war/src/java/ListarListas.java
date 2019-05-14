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
@Named(value = "ListarListas")
@RequestScoped
public class ListarListas {

    @EJB
    private UsuarioManager usuarioManager;
    
     @EJB
    private ListaDeComprasManager listaDeComprasManager;
     
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
    ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();

    public ListarListas() {
    }
    
    public List<ListaDeCompras> getListasDeCompras(){
        Usuario logado = (Usuario)session.getAttribute("usuarioLogado");
        return listaDeComprasManager.getAllListasByUser(logado.getId());
    }
    
    public void criarLista(){
        ListaDeCompras listaDeCompras = new ListaDeCompras();
        listaDeCompras.setUsuarios(new ArrayList<>());
        listaDeCompras.getUsuarios().add((Usuario)session.getAttribute("usuarioLogado"));
        listaDeComprasManager.create(listaDeCompras);
    }

}
