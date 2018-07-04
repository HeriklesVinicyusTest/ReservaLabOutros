/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.beans;

import javax.annotation.PostConstruct;
import br.com.reserva.upe.dao.PessoaDAO;
import br.com.reserva.upe.modelo.Pessoa;

public abstract class AbstractBeanPessoa<T> {

    private PessoaDAO dao;
    private Pessoa current;

    public Pessoa getCurrent() {
        return current;
    }

    public void setCurrent(Pessoa current) {
        this.current = current;
    }

    private PessoaDAO getDAO() {
        if (dao == null) {
            dao = createDao();
        }
        return dao;
    }

    public void salvar() {
        getDAO().cadastrar(current);
        novo();
    }

    abstract void novo();

    abstract PessoaDAO createDao();

    @PostConstruct
    public void init() {
        novo();
    }

}
