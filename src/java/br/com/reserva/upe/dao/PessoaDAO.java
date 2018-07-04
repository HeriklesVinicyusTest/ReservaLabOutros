/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao;

import br.com.reserva.upe.modelo.Pessoa;
import java.util.List;

public interface PessoaDAO extends DAO<Pessoa> {

    List<Pessoa> listar();

}
