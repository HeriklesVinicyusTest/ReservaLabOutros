/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao;

import br.com.reserva.upe.modelo.Reserva;
import java.util.List;

/**
 *
 * @author mathe
 */
public interface ReservaDAO extends DAO<Reserva> {

    List<Reserva> listar();

}
