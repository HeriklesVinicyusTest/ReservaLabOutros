/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao;

import br.com.reserva.upe.modelo.Horario;
import java.util.List;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@hotmail.com>
 */
public interface HorarioDAO extends DAO<Horario>{
    
    List<Horario> horariosSemReserva(String data, String laboratorio);
    
}
