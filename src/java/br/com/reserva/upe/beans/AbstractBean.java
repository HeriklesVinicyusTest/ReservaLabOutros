/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import br.com.reserva.upe.dao.DAO;

/**
 *
 * @author Hérikles
 */
public abstract class AbstractBean<T> {

    private DAO<T> dao;
    private T current;

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    private DAO<T> getDAO() {
        if (dao == null) {
            dao = createDao();
        }
        return dao;
    }

    public void salvar() {
        try {
            getDAO().cadastrar(current);
        } catch (Exception ex) {
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        novo();
    }
    
    public void apagar(){
        try{
        getDAO().apagar(current);
        }catch(Exception ex){
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    abstract void novo();
    
    

    abstract DAO<T> createDao();

    @PostConstruct
    public void init() {
        novo();
    }
    
    

}
