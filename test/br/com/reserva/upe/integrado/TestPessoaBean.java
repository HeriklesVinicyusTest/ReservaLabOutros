/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.integrado;

import br.com.reserva.upe.beans.ListarPessoasBean;
import br.com.reserva.upe.beans.PessoaBean;
import br.com.reserva.upe.modelo.Pessoa;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@hotmail.com>
 */
public class TestPessoaBean {

    PessoaBean pb;
    Pessoa p;

    public TestPessoaBean() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        pb.excluirUsuario(p);
    }

    @Test
    public void salvarPessoa() {
        boolean aux = false;
        pb = new PessoaBean();
        p = new Pessoa("lala@lili1", "1231");
        pb.salvarPessoa(p);
        List<Pessoa> lp = new ListarPessoasBean().getLista();
        for (Pessoa pessoa : lp) {
            if (pessoa.equals(p)) {
                aux = true;
            }
        }
        assertTrue(aux);

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
