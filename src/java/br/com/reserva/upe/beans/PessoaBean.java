package br.com.reserva.upe.beans;

import br.com.reserva.upe.dao.DAO_Pessoa;
import br.com.reserva.upe.dao.DAO_Reserva;
import br.com.reserva.upe.modelo.Pessoa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.reserva.upe.dao.IDAO_Pessoa;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.EnviarEmail;
import br.com.reserva.upe.util.FacesUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mathe
 */
@ManagedBean(name = "CrudPessoa")
@SessionScoped
public class PessoaBean extends AbstractBeanPessoa<Pessoa>{
    
    private Pessoa novaPessoa;
    private Pessoa pessoaAtual;
    private Integer id_certo;
    
    public PessoaBean(){}
    
    @Override
    public void novo() {
        setCurrent(new Pessoa());
    }

    @Override
    public IDAO_Pessoa<Pessoa> createDao() {
        return new DAO_Pessoa();
    }
    
    
    public void salvarPessoa(Pessoa pessoa) {

        DAO_Pessoa dao = new DAO_Pessoa();
        
        try {
            dao.Cadastrar(pessoa);
            novaPessoa = pessoa;
            
            //limpa os dados da reserva para não aparecer no formulário
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CrudPessoa");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pessoa");
            
            //permite que seja mostrada a mensagem após o redirecionamento dá página
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesUtil.MensagemIformativa(""
                    + "------------------------------------------"
                    + "Novo usuário cadastrado com sucesso!");
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("usuarios.xhtml");
            
        } catch (SQLException ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível cadastrar o novo usuários! :/");

        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void excluirUsuario(Pessoa pessoa) {
        DAO_Pessoa dao = new DAO_Pessoa();

        try {
            dao.Apagar(pessoa);

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("usuarios.xhtml");
                FacesUtil.MensagemIformativa("Usuário excluido com sucesso!");
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível excluir o usuário! :/");
        }
    }
    
    public void editarPessoa(Pessoa pessoa) {
        //this.novaReserva = reserva;
        this.pessoaAtual = pessoa;
        this.id_certo = pessoa.getId();
        //this.novaReserva.setId(id);

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.setAttribute("pessoa", this.pessoaAtual);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alterarUsuario.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarPessoa(Pessoa pessoa, Integer id) {
        DAO_Pessoa dao = new DAO_Pessoa();
        try {
            dao.Atualizar(pessoa, id);
            
            //limpa os dados da reserva para não aparecer no formulário
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pessoa");
            
            //permite que seja mostrada a mensagem após o redirecionamento dá página
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesUtil.MensagemIformativa(""
                    + "------------------------------------------"
                    + "O cadastro do usuário foi atualizada com sucesso!");
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("usuarios.xhtml");       
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
