/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.dao.HorarioDAO;
import br.com.reserva.upe.dao.PessoaDAO;
import br.com.reserva.upe.dao.ReservaDAO;
import br.com.reserva.upe.dao.hibenate.HorarioHibernate;
import br.com.reserva.upe.dao.hibenate.PessoaHibernate;
import br.com.reserva.upe.dao.hibenate.ReservaHibernate;
import br.com.reserva.upe.modelo.Horario;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.EnviarEmail;
import br.com.reserva.upe.util.FacesUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mathe
 */
@ManagedBean(name = "reservaEstendida")
@SessionScoped
public class ReservaEstendidaBean {

    private Reserva novaReserva;
    private Reserva reservaAtual;
    private List<SelectItem> horariosSelect;

    public ReservaEstendidaBean() {
    }
    private Integer id_certo;

    public void salvarReserva(Reserva r, int id, String email) {

        ReservaDAO dao = new ReservaHibernate();
        PessoaDAO p = new PessoaHibernate();
        r.setPessoa(p.recuperar(id));

        try {
            dao.cadastrar(r);
            novaReserva = r;

            //permite que seja mostrada a mensagem após o redirecionamento dá página
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesUtil.MensagemIformativa(""
                    + "------------------------------------------"
                    + "A nova reserva foi efetuada com sucesso!");

            FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservas.xhtml");
            EnviarEmail.enviarEmail(email);

        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluirReserva(Reserva reserva) {
        ReservaDAO dao = new ReservaHibernate();

        dao.apagar(reserva);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservas.xhtml");
            FacesUtil.MensagemIformativa("A reserva foi excluida com sucesso!");
        } catch (IOException ex) {
            Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarReserva(Reserva reserva) {
        this.reservaAtual = reserva;
        this.id_certo = reserva.getId();

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.setAttribute("reservaN", this.reservaAtual);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alterarReservas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarReserva(Reserva reserva, Integer id) {
        DAO_Reserva dao = new DAO_Reserva();
        try {
            dao.Atualizar2(reserva, id);

            //limpa os dados da reserva para não aparecer no formulário
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("reservaNormal");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("reservaN");

            //permite que seja mostrada a mensagem após o redirecionamento dá página
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesUtil.MensagemIformativa(""
                    + "------------------------------------------"
                    + "A reserva foi atualizada com sucesso!");

            FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservas.xhtml");

        } catch (SQLException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reserva getNovaReserva() {
        return novaReserva;
    }

    public void setNovaReserva(Reserva novaReserva) {
        this.novaReserva = novaReserva;
    }

    public Reserva getReservaAtual() {
        return novaReserva;
    }

    public void setReservaAtual(Reserva novaReserva) {
        this.novaReserva = novaReserva;
    }

    public Integer getIdCerto() {
        return id_certo;
    }

    //metodo que tras uma lista de selectItem do banco de dados
    public SelectItem[] horariosSelect(String data, String laboratorio) {

        HorarioDAO dao = new HorarioHibernate();

        List<Horario> horarios = dao.horariosSemReserva(data, laboratorio);

        SelectItem[] items = new SelectItem[horarios.size()];

        int i = 0;

        for (Object x : horarios) {

            items[i++] = new SelectItem(x, x.toString());
        }

        return items;
    }

    @FacesConverter(forClass = Horario.class)
    public static class HorarioConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
            return new Horario(string);
        }

        @Override
        public String getAsString(FacesContext fc, UIComponent uic, Object o) {

            return o.toString();
        }

    }

    public void parte2DaReserva() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("reservaEstendidaPart2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
