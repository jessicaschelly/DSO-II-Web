/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcoslaydner
 */
@Stateless
@LocalBean
public class ProdutoManager {

    
    private int numero;
    private String tipo;
    private int diaria;
    private int qtdcamas;
    private int conta;
    private String status;
    private ListaDeCompras hQuarto;

    
    public int getQtdcamas() {
        return qtdcamas;
    }

    public void setQtdcamas(int qtdcamas) {
        this.qtdcamas = qtdcamas;
    }
    public void statusChange(int numero) {
        Query query = em.createNamedQuery("HotelQuartos.findByNumero").setParameter("numero", numero);
        
        List<ListaDeCompras> list = query.getResultList();
        ListaDeCompras hqq = list.get(0);
      
    } 
    
    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
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

    public int getDiaria() {
        return diaria;
    }

    public void setDiaria(int diaria) {
        this.diaria = diaria;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public void salvarQuarto(Produto quarto) {
        em.merge(quarto);
        em.flush();
    }
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public List<Produto> getAllQuartos() {
        Query query = em.createNamedQuery("HotelQuartos.findAll");
        return query.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    public int getMaxNumero() {  // pega o maior ID de cliente na tabela
        Query query = em.createNativeQuery("SELECT MAX(numero) FROM QUARTOS");
        return (Integer) query.getSingleResult();
    }
 
    public void cadastrarQuarto(Produto hQuartos) {
        em.persist(hQuarto);
    }

    public Produto getByNumero(int numero) {
        Query query = em.createNamedQuery("HotelQuartos.findByNumero").setParameter("numero", numero);
        
        List<Produto> list = query.getResultList();
        
        if (list.isEmpty()) 
            return null;
        else 
            return list.get(0);
    }    
}
