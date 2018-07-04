/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao.hibenate;

import br.com.reserva.upe.dao.PessoaDAO;
import br.com.reserva.upe.dao.ReservaDAO;
import br.com.reserva.upe.modelo.Pessoa;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hérikles Vinicyus <heriklesvinicyus@hotmail.com>
 */
public class ReservaHibernate implements ReservaDAO {

    @Override
    public void cadastrar(Reserva t) {
        Session session = HibernateUtil.getSession();
        ReservaDAO r = new ReservaHibernate();
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Reserva. Erro: " + e.toString());
        } finally {
            session.close();
        }

    }

    @Override
    public void apagar(Reserva t) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Reserva. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(Reserva t) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Reserva. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Reserva> listar() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Reserva> lista = (session.createQuery("from " + Pessoa.class.getName())).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos as Reservas. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Reserva recuperar(int id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Reserva) session.get(Reserva.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Reserva. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

}
