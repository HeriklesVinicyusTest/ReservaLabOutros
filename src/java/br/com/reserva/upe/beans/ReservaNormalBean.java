package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Horario;
import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.modelo.Horario;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.EnviarEmail;
import br.com.reserva.upe.util.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
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
@ManagedBean(name = "reservaNormal")
@SessionScoped
public class ReservaNormalBean implements Serializable {

    private Reserva novaReserva;
    private Reserva reservaAtual;
    private List<SelectItem> horariosSelect;

    public ReservaNormalBean() {
    }

    private Integer id_certo;

    public void salvarReserva(Reserva r, int id, String email, String tipo) {

        DAO_Reserva dao = new DAO_Reserva();
        r.setIdPessoa(id);

        try {
            dao.Cadastrar(r);
            novaReserva = r;
            
            //limpa os dados da reserva para não aparecer no formulário
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("reservaN");
            
            //permite que seja mostrada a mensagem após o redirecionamento dá página
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesUtil.MensagemIformativa(""
                    + "------------------------------------------"
                    + "A nova reserva foi efetuada com sucesso!");
            
            if (tipo.equals("1")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservas.xhtml");
                EnviarEmail.enviarEmail(email);
            }else if(tipo.equals("2")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservasAdm.xhtml");
                EnviarEmail.enviarEmail(email);
            }
            

        } catch (SQLException ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível salvar a reserva! :/");

        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluirReserva(Reserva reserva, String tipo) {
        DAO_Reserva dao = new DAO_Reserva();

        try {
            dao.Apagar(reserva);

            try {
                if (tipo.equals("1")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservas.xhtml");
                }else if(tipo.equals("2")){
                    FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservasAdm.xhtml");
                }
                
                FacesUtil.MensagemIformativa("A reserva foi excluida com sucesso!");
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível excluir a sua reserva! :/");
        }
    }
    
    
    public void excluirReservaDaTelaAdmResevas(Reserva reserva) {
        DAO_Reserva dao = new DAO_Reserva();

        try {
            dao.Apagar(reserva);

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admReservas.xhtml");
                FacesUtil.MensagemIformativa("A reserva foi excluida com sucesso!");
                
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível excluir a sua reserva! :/");
        }
    }
    
    
//    public String editarReserva(Reserva reserva){
//        this.novaReserva = reserva;
//        
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
//        session.setAttribute("reservaN", this.novaReserva);
//        
//        return "alterarReservas.xhtml";
//        
//    }
    
    public void editarReservaParte1(Reserva reserva) {
        //this.novaReserva = reserva;
        this.reservaAtual = reserva;
        this.id_certo = reserva.getId();
        //this.novaReserva.setId(id);

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.setAttribute("reservaN", this.reservaAtual);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alterarReservas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void editarReservaParte2() {
//        //this.novaReserva = reserva;
//        this.reservaAtual = reserva;
//        this.id_certo = reserva.getId();
//        //this.novaReserva.setId(id);
//
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
//        session.setAttribute("reservaN", this.reservaAtual);
        
        //limpa o formulário para que quando o usário pressionar a tecla voltar o formuláiro não aparecer todo preenchido
       
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alterarReservaPart2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarReserva(Reserva reserva, Integer id, String tipo) {
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
            
            if(tipo.equals("1")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservas.xhtml");
            }else if(tipo.equals("2")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("minhasReservasAdm.xhtml");
            }
            
            
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

        DAO_Horario dao = new DAO_Horario();
        
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
    
    public void parte2DaReserva(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("reservaNormalPart2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarReservaPart2(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alterarReservaPart2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
