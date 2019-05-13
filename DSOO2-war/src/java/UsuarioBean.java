/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UsuarioManager;
import ejb.ProdutoManager;
import ejb.Usuario;
import java.io.IOException;
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
@Named(value = "ClienteMBean")
@RequestScoped
public class UsuarioBean {

    @EJB
    private UsuarioManager cliente;

    long value;
    private String senha;
    private int quarto;
    private long cpf;
    private long telefone;
    private String nome;

    public long getCpf() {
        return cpf;
    }
    
    public void checkOut() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object att = session.getAttribute("hq");
        Usuario hq = (Usuario)att;
        setQuarto(0);
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        eContext.redirect("menuFuncionario.xhtml");
        
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    
    public void cadastrarCliente() throws IOException {
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        if(cliente.getByCpf(cpf) == null) {
            Usuario hct = new Usuario();
            hct.setNome(nome);
            hct.setSenha(senha);
            hct.setTelefone("a");
   
            cliente.persist(hct);
            eContext.redirect("checkIn.xhtml");
            
        }
    }
    public String cpfJahExiste() {
        if(cliente.getByCpf(cpf) != null){
            return "";
        }else{
            return "CPF Já Cadastrado!";
        }
    }

    private Usuario hCliente = setHCliente();
    Usuario hs;
    
    public List<Usuario> getListaClientes() {
        return cliente.getAllClients();
    }
    
   public String getFoi() throws Exception {
       
       if (quarto == 0)
           return "";
       else if (getResponse().equals("nao existe"))
           return "Este quarto não existe";
       else
           getSenhaResponse();
           return "Senha Incorreta";
       
   }
        
    public String getResponse() {

        if ((quarto != 0) && (quartoExiste(quarto))) {
            //invalidate user session
            return "existe";
        } else {
            return "nao existe";
        }
    }
    
    public String getSenhaResponse() throws Exception {

        if (senhaCorreta(hCliente)) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.setAttribute("hc", this.hCliente);
            
            ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
            eContext.redirect("menuCliente.xhtml");
            return "existe";
        } else {
            return "nao existe";
        }
    }
    
    public boolean quartoExiste(int quarto) {
        this.hCliente = cliente.getByQuarto(quarto);        
        return hCliente != null;
    }
    
    public boolean senhaCorreta(Usuario hc) {
        if (hc == null || senha == null)
            return false;
        
        else return senha.equals(hc.getSenha());
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getQuarto() {
        return quarto;
    }

    public void setQuarto(int quarto) {
        this.quarto = quarto;
    }

    public void setValue(String value) {
        long lValue = Long.valueOf(value);
        this.value = lValue;
    }
    
    public String getSaldo() {
        
        if (hCliente != null)
           return "Sua Fatura é de R$ " ;
        else
           return "";
    }
    
    public void somarSaldo (long value) {
        
        salvarCliente();
}

    public void setHs(Usuario hs) {
        this.hs = hs;
    }
    
    public Usuario getHs() {
        return hs;
    }
    
//    public void somarSaldo () {
//        
//        if (hCliente != null)
//            hCliente.setConta(hCliente.getConta() + value);
//        
//        salvarCliente();
//    }
    
    public String getNome() {
        if (hCliente != null){
            return hCliente.getNome();
        } else
           return "";
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void salvarCliente() {
        if (hCliente != null)
            cliente.salvarCliente(this.hCliente);
        
    }
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public UsuarioBean() {
    }
    public void cadastrarCI() throws IOException {
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
        eContext.redirect("cadastroCliente.xhtml");
        
    }

    private Usuario setHCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Object att = session.getAttribute("hc");
        if (att != null)
            return (Usuario) att;
        else 
            return null;
    }
    

}
