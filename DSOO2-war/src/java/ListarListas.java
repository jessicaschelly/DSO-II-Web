/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.ListaDeCompras;
import ejb.ListaDeComprasManager;
import ejb.UsuarioManager;
import ejb.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@Named(value = "ListarListas")
@RequestScoped
public class ListarListas {

    @EJB
    private UsuarioManager usuarioManager;
    
    @EJB
    private ListaDeComprasManager listaDeComprasManager;
       @Inject
    private LoginUsuario loginUsuario;
    public ListarListas() {
    }
    
    public List<ListaDeCompras> getListasDeCompras(){
        return listaDeComprasManager.getAllListasByUser(loginUsuario.getUsuarioLogado().getId());
    }
    
  

    public void criarLista(){
        Usuario managed = usuarioManager.getById(loginUsuario.getIdUsuarioLogado());

        ListaDeCompras listaDeCompras = new ListaDeCompras();         
        managed.getListasDeCompras().add(listaDeCompras);
        
        loginUsuario.setUsuarioLogado(usuarioManager.update(managed));
    }
    
    public String editarLista(long idLista){
        loginUsuario.setIdListaAtual(idLista);
        return "lista";
    }

}
