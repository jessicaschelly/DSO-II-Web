/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.ListaDeCompras;
import ejb.Produto;
import ejb.ProdutoManager;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcoslaydner
 */
@Named(value = "QuartoMBean")
@RequestScoped
public class ListaBean {

    @EJB
    private ProdutoManager quarto;
    private int numero;
    private String tipo;
    private long diaria;
    private short qtdcamas;
    private String status;
    
    private ListaDeCompras hQuartos = sethQuartos();

    public String getStatus() {
        return status;
    }
    public String getFoi(){
        if(numero == 0) {
            return "";
        }else {
            return "Quarto ou CPF não existe.";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProdutoManager getQuarto() {
        return quarto;
    }

    public void setQuarto(ProdutoManager quarto) {
        this.quarto = quarto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getDiaria() {
        return diaria;
    }

    public void setDiaria(long diaria) {
        this.diaria = diaria;
    }

    public short getQtdcamas() {
        return qtdcamas;
    }

    public void setQtdcamas(short qtdcamas) {
        this.qtdcamas = qtdcamas;
    }
    public void cadastrarQuarto() throws IOException {          // Chama o método do bean de sessão
        ListaDeCompras hqt = new ListaDeCompras();
    
        quarto.persist(hqt);
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        eContext.redirect("menuFuncionario.xhtml");
        
    }
   

    public ListaDeCompras sethQuartos() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object att = session.getAttribute("hq");
        if (att != null)
            return (ListaDeCompras) att;
        else 
            return null;    
    }
    
    
    public List<Produto> getListaQuartos() {
        return quarto.getAllQuartos();
    }
    
    /**
     * Creates a new instance of QuartoMBean
     */
    public ListaBean() {
    }
    
}
