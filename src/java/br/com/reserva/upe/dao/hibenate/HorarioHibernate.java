/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.dao.hibenate;

import br.com.reserva.upe.dao.HorarioDAO;
import br.com.reserva.upe.modelo.Horario;
import br.com.reserva.upe.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hérikles Vinicyus <heriklesvinicyus@hotmail.com>
 */
public class HorarioHibernate implements HorarioDAO {

    @Override
    public void cadastrar(Horario t) {
        Session session =  HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Horario. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void apagar(Horario t) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Horario. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(Horario t) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }

    }

    @Override
    public List<Horario> horariosSemReserva(String data, String laboratorio) {
        Session session = HibernateUtil.getSession();

        List<Horario> horarios = (session.createQuery("from horario h where h.hora not in (SELECT horario from reserva where dataDaReserva = :data and laboratorio = :laboratorio )order by h.hora")
                .setParameter("data", data).setParameter("laboratorio", laboratorio).list());
        if (horarios != null && !horarios.isEmpty()) {
            return horarios;
        }

        return null;
    }

    @Override
    public Horario recuperar(int id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Horario) session.get(Horario.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Reserva. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }
}
