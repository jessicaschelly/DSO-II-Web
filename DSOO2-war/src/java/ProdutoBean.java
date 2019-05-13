/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.Produto;
import ejb.ProdutoManager;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author LucasVarella
 */
@Named(value = "FuncionarioMBean")
@RequestScoped
public class ProdutoBean {

    @EJB
    private ProdutoManager funcionario;
    
    private String nome;
    private String id;
    private String senha;

    public ProdutoManager getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(ProdutoManager funcionario) {
        this.funcionario = funcionario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    private Produto hFuncionario = setHFuncionario();
    
    
    public List<Produto> getListaQuartos() {
        return funcionario.getAllQuartos();
    }
    
    public void validar() throws Exception {
        getResponse();
        getSenhaResponse();
    }
    
    public String getResponse() {
        
        if(id != null && (idExiste(id))) {
            return "existe";
        }else{
            return "ID Incorreta!";
        }
            
    }
    public boolean idExiste(String id) {
        return hFuncionario != null;
    }
    public boolean senhaCorreta(Produto hf) {
        if(hf == null) {
            return false;
        } else {
            return senha.equals("");
        }
    }
    public String getSenhaResponse() throws Exception {
    
        if(senhaCorreta(hFuncionario)) {
            ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
            eContext.redirect("menuFuncionario.xhtml");
                return "existe";
        } else {
            return "Senha Incorreta!";
        }
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
     
    /**
     * Creates a new instance of FuncionarioMBean
     */
    public ProdutoBean() {
    }

    private Produto setHFuncionario() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object att = session.getAttribute("hf");
        if (att != null)
            return (Produto) att;
        else 
            return null;    }
    
}
