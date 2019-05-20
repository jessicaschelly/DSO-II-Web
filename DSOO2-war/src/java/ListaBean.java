/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.Item;
import ejb.ListaDeCompras;
import ejb.ListaDeComprasManager;
import ejb.Usuario;
import ejb.UsuarioManager;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "listaBean")
@RequestScoped
public class ListaBean {

    @EJB
    private UsuarioManager usuarioManager;

    @EJB
    private ListaDeComprasManager listaDeComprasManager;

    @Inject
    private LoginUsuario loginUsuario;
    private String nomeItem;

    private long idUsuarioSelecionado;

    public ListaBean() {
    }

    public List<Item> listaItens() {
        return listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual()).getItens();
    }

    public void somaItem(long itemId) {
        ListaDeCompras lista = listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual());

        for (Item item : lista.getItens()) {
            if (item.getId() == itemId) {
                item.setQuantidade(item.getQuantidade() + 1);
            }
        }
        listaDeComprasManager.update(lista);
    }

    public void subtraiItem(long itemId) {
        ListaDeCompras lista = listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual());

        for (Item item : lista.getItens()) {
            if (item.getId() == itemId) {
                if (item.getQuantidade() == 1) {
                    removeItem(itemId);
                    return;
                } else {
                    item.setQuantidade(item.getQuantidade() - 1);
                }
            }
            listaDeComprasManager.update(lista);
        }
    }

    public void removeItem(long itemId) {
        ListaDeCompras lista = listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual());

        Item toRemove = null;
        for (Item item : lista.getItens()) {
            if (item.getId() == itemId) {
                toRemove = item;
                break;
            }
        }

        lista.getItens().remove(toRemove);

        listaDeComprasManager.update(lista);
    }

    public void adicionarItem() {
        ListaDeCompras lista = listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual());

        Item item = new Item();
        item.setNome(nomeItem);
        item.setQuantidade(1);
        lista.getItens().add(item);

        listaDeComprasManager.update(lista);
    }

    public List<Usuario> getTodosUsuarios() {
        ListaDeCompras lista = listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual());
        List<Usuario> usuarios = usuarioManager.getAllClients();

        List<Usuario> paraRemover = new ArrayList<>();
        for (Usuario l : lista.getUsuarios()) {
            for (Usuario u : usuarios) {
                if (l.getId() == u.getId()) {
                    paraRemover.add(u);
                }
            }
        }
        for (Usuario u : paraRemover) {
            usuarios.remove(u);
        }

        return usuarios;
    }

    public void compartilhar(long id) {
        ListaDeCompras lista = listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual());
        Usuario usuario = usuarioManager.getById(id);
        usuario.getListasDeCompras().add(lista);

        usuarioManager.update(usuario);
    }

    public String remover() {
        ListaDeCompras lista = listaDeComprasManager.getListaById(loginUsuario.getIdListaAtual());
        for (Usuario u : lista.getUsuarios()) {
            List<ListaDeCompras> toRemove = new ArrayList<>();
            for (ListaDeCompras l : u.getListasDeCompras()) {
                if (l.getId().equals(loginUsuario.getIdListaAtual())) {
                    toRemove.add(l);
                }
            }
            for (ListaDeCompras l : toRemove) {
              u.getListasDeCompras().remove(l);
                
            }
             usuarioManager.update(u);
        }
        
        return "listarListas";
    }

    //GET SET
    public long getIdUsuarioSelecionado() {
        return idUsuarioSelecionado;
    }

    public void setIdUsuarioSelecionado(long idUsuarioSelecionado) {
        this.idUsuarioSelecionado = idUsuarioSelecionado;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

}
