package util;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

/**
 * Hibernate connection manager
 * @author Michal Glodek
 */
public class HibernateUtil {
    /** Session factory */
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Builds session factory and returns session factory
     * @return session factory
     */
    private static SessionFactory buildSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }

    /**
     * Session factory getter
     * @return session factory
     */
    public static SessionFactory getSessionFactory() {return  sessionFactory;}
}