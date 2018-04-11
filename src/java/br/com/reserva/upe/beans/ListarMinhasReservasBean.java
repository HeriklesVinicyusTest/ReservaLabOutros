package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.modelo.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "listaMinhasReservas")
@SessionScoped

public class ListarMinhasReservasBean {
    
    private List<Reserva> lista = new ArrayList<Reserva>();
    
    public ListarMinhasReservasBean(){
    
    }
    
    public List<Reserva> getLista(Integer idPessoa) {
        DAO_Reserva dao = new DAO_Reserva();
        this.lista = dao.listarMinhasReservas(idPessoa);
       for(Reserva r : this.lista){
           // this.lista.add(e);
             System.out.println(r.getTurno());
        }
        return lista;
    }
    public void setLista(List<Reserva> lista) {
        this.lista = lista;
    }
}