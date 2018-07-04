/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao;

/**
 *
 * @author Hérikles
 * @param <T>
 */
public interface DAO<T> {

    void cadastrar(T t);

    void apagar(T t);

    void atualizar(T t);
    
    T recuperar(int id);
}
