package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Pessoa;
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

    private List<Pessoa> lista = new ArrayList<Pessoa>();
    
    public ListarPessoasBean(){
    
    }

    public List<Pessoa> getLista() {
        DAO_Pessoa dao = new DAO_Pessoa();
        this.lista = dao.listar();
        for (Pessoa p : this.lista) {
            // this.lista.add(e);
            
        }
        return lista;
    }

   
}
