/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.integrado;

import br.com.reserva.upe.beans.ListarReservasBean;
import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.modelo.Reserva;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@hotmail.com>
 */
public class TestListaReservasBean {

    public DAO_Reserva dr;
    public Reserva r;
    public ListarReservasBean lrb;
    

    public TestListaReservasBean() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        lrb = new ListarReservasBean();
        dr = new DAO_Reserva();
        r = new Reserva();
        r.setId(1);
        r.setIdPessoa(1);
        r.setData("06/01/2019");
        dr.Cadastrar(r);
       
    }

    @After
    public void tearDown() throws SQLException {
        dr.Apagar(r);
    }
    
    @Test
    public void getList(){
        Reserva r1 = (lrb.getLista()).get(0);
        assertEquals(r1, r);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
