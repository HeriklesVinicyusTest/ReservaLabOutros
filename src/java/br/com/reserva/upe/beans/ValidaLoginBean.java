package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Pessoa;
import br.com.reserva.upe.modelo.Pessoa;
import br.com.reserva.upe.util.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "conferePessoa")
@SessionScoped
public class ValidaLoginBean implements Serializable{

    private Pessoa pessoaLogada = null;
    private boolean log = false;
    
    private String email;
    private String senha;

    public ValidaLoginBean() {
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void logar() {

        DAO_Pessoa dao = new DAO_Pessoa();
        //Pessoa logado = dao.autenticar(p);
        Pessoa logado = dao.autenticar(new Pessoa(email,senha));

        if (!logado.getNome().equals("")) {
            
            
            log = true;
            
            if (logado.getTipo().equals("1")) {
                pessoaLogada = logado;
                
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("home2.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (logado.getTipo().equals("2"))  {
                pessoaLogada = logado;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("homeAdm2.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }     
             
        }else{
            log = false;
            FacesUtil.MensagemIformativa("Usuário ou senha incorretos!");
             
        }
    }
    
    public void testeLog(){
        if(this.log == false){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    public void testeLogProf(){
        if(this.log == false){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            if(this.pessoaLogada.getTipo().equals("2")){
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        }
    }
    
    public void testeLogAdm(){
        if(this.log == false){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            if(this.pessoaLogada.getTipo().equals("1")){
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        }
    }

    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa p) {
        this.pessoaLogada = p;
    }
    
     public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }
    
}
