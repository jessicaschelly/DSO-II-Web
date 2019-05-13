/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ejb.Item;
import ejb.ItemManager;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcoslaydner
 */
@Named(value = "ServicoMBean")
@RequestScoped
public class ItemBean {

    private String nome;
    private long preco;
    
    @EJB
    private ItemManager servico;
    
    public List<Item> getListaServicos() {
        return servico.getAllFuncionarios();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPreco() {
        return preco;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }

    public void cadastrarServico() throws IOException {          // Chama o método do bean de sessão
        Item hst = new Item();
      
        servico.persist(hst);
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        eContext.redirect("menuFuncionario.xhtml");
    }
    
    /**
     * Creates a new instance of FuncionariosMBean
     */
    public ItemBean() {
    }
    
}
