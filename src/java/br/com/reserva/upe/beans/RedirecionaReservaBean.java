package br.com.reserva.upe.beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "redirecionadorReserva")
@SessionScoped
public class RedirecionaReservaBean {

    public void VerificaReserva(TipoReservaBean tipoReserva) {
        if (tipoReserva.getTipo().equals("1")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("reservaNormal.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(RedirecionaReservaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tipoReserva.getTipo().equals("2")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("reservaEstendida.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(RedirecionaReservaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
