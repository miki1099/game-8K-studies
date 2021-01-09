package dao;

import model.Score;
import org.hibernate.Session;
import util.HibernateUtil;
import java.util.List;

/**
 * Score dao gives methods to use data in database
 * @author Michal Glodek
 */
public class ScoreDao {

    /**
     * method return all scores with users from database
     * @return Score list
     */
    public List<Score> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List foundList = session.createQuery("select s " +
                "from Score s " +
                "left join fetch s.user").getResultList();
        session.close();
        return foundList;
    }

    /**
     * Inserts score to database
     */
    public void insert(Score score){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(score);
        session.getTransaction().commit();
        session.close();
    }
}
