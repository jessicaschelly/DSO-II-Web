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
public class Default {

    @EJB
    private UsuarioManager cliente;
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
    ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();

    public Default() {
    }

}
