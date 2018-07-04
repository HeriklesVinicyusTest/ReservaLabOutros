/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reserva.upe.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Hérikles Vinícyus <heriklesvinicyus@hotmail.com>
 */
public class HibernateUtil {
//private static HibernateUtil instance;

    private static SessionFactory sessionFactory;

    private HibernateUtil() {

    }

    public static Session getSession() {
        /*if (instance == null) {
            instance = new HibernateUtil();
        }*/
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        return sessionFactory.openSession();
    }
}
