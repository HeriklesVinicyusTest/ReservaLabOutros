package br.com.reserva.upe.dao.hibenate;

import br.com.reserva.upe.dao.PessoaDAO;
import br.com.reserva.upe.modelo.Pessoa;
import br.com.reserva.upe.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hérikles Vinicyus <heriklesvinicyus@hotmail.com>
 */
public class PessoaHibernate implements PessoaDAO {

    @Override
    public void cadastrar(Pessoa t) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Pessoa. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void apagar(Pessoa t) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Pessoa. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(Pessoa t) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Pessoa. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Pessoa> listar() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Pessoa> lista = (session.createQuery("from " + Pessoa.class.getName())).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Endereços. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Pessoa recuperar(int id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Pessoa) session.get(Pessoa.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Reserva. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

}
