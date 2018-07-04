package br.com.reserva.upe.beans;

import br.com.reserva.upe.modelo.Pessoa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.reserva.upe.util.FacesUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import br.com.reserva.upe.dao.PessoaDAO;
import br.com.reserva.upe.dao.hibenate.PessoaHibernate;

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
    public PessoaDAO createDao() {
        return new PessoaHibernate();
    }
    
    
    public void salvarPessoa(Pessoa pessoa) {

        PessoaDAO dao = new PessoaHibernate();
        
        try {
            dao.cadastrar(pessoa);
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
            
        } catch (Exception ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível cadastrar o novo usuários! :/");

        }

    }
    
    public void excluirUsuario(Pessoa pessoa) {
        PessoaDAO dao = new PessoaHibernate();

        try {
            dao.apagar(pessoa);

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("usuarios.xhtml");
                FacesUtil.MensagemIformativa("Usuário excluido com sucesso!");
            } catch (IOException ex) {
                Logger.getLogger(ValidaLoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            System.out.println("Erro de SQL: " + ex);
            FacesUtil.MensagemErro("Não foi possível excluir o usuário! :/");
        }
    }
    
    public void editarPessoa(Pessoa pessoa) {
        this.pessoaAtual = pessoa;
        this.id_certo = pessoa.getId();

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
        PessoaDAO dao = new PessoaHibernate();
        try {
            dao.atualizar(pessoa);
            
            //limpa os dados da reserva para não aparecer no formulário
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pessoa");
            
            //permite que seja mostrada a mensagem após o redirecionamento dá página
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesUtil.MensagemIformativa(""
                    + "------------------------------------------"
                    + "O cadastro do usuário foi atualizada com sucesso!");
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("usuarios.xhtml");       
            
        } catch (IOException ex) {
            Logger.getLogger(ReservaNormalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
