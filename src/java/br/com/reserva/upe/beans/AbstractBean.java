/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.IDAO;
import br.com.reserva.upe.modelo.Pessoa;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author israel
 */
public abstract class AbstractBean<T> {

    private IDAO<T> dao;
    private T current;

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    private IDAO<T> getDAO() {
        if (dao == null) {
            dao = createDao();
        }
        return dao;
    }

    public void salvar() {
        try {
            getDAO().Cadastrar(current);
        } catch (SQLException ex) {
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        novo();
    }
    
    public void apagar(){
        try{
        getDAO().Apagar(current);
        }catch(SQLException ex){
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    abstract void novo();
    
    

    abstract IDAO<T> createDao();

    @PostConstruct
    public void init() {
        novo();
    }
    
    

}
