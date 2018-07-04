package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Pessoa;
import br.com.reserva.upe.dao.PessoaDAO;
import br.com.reserva.upe.dao.hibenate.PessoaHibernate;
import br.com.reserva.upe.modelo.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author matheus
 */
@ManagedBean(name = "listaPessoas")
@RequestScoped
public class ListarPessoasBean {

    public ListarPessoasBean() {
    }
    private List<Pessoa> lista = new ArrayList<Pessoa>();

    public List<Pessoa> getLista() {
        PessoaDAO dao = new PessoaHibernate();
        this.lista = dao.listar();
        return lista;
    }

}
