/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao;

import java.sql.SQLException;

/**
 *
 * @author mathe
 * @param <T>
 */
public interface IDAO_Pessoa<T> {

    void Cadastrar(T t)  throws SQLException;

    void Apagar(T t) throws SQLException ;

    void Atualizar(T t) throws SQLException ;

}
