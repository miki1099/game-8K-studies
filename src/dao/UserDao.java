package dao;

import model.User;
import org.hibernate.Session;
import util.HibernateUtil;

public class UserDao {
    public void insert(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }
}
