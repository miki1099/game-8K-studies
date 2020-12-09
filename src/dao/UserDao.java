package dao;

import model.User;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDao {
    public void insert(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }

    public User findByName(String name){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            User user = session
                    .createQuery("select u from User u where u.name=:name", User.class)
                    .setParameter("name", name).getSingleResult();
            session.close();
            return user;
        } catch (NoResultException e){
            return null;
        }
    }
}
