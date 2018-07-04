package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.dao.ReservaDAO;
import br.com.reserva.upe.dao.hibenate.ReservaHibernate;
import br.com.reserva.upe.modelo.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "listaReservas")
@SessionScoped

public class ListarReservasBean {

    private List<Reserva> lista = new ArrayList<Reserva>();

    public ListarReservasBean() {
    }

    public List<Reserva> getLista() {
        //Ainda mudar
        ReservaDAO dao = new ReservaHibernate();
        this.lista = dao.listar();
        for (Reserva r : this.lista) {
            System.out.println(r.getTurno());
        }
        return lista;
    }

    public void setLista(List<Reserva> lista) {
        this.lista = lista;
    }
}
