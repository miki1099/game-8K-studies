package ScoreLogic;


import dao.ScoreDao;
import dao.UserDao;
import model.Score;
import model.User;

import java.util.Comparator;
import java.util.List;

/**
 * Class to work with ScoreBoardPanel
 * @author Michal Glodek
 */
public class ScoreBoardLogic {
    /** Gives methods to get or insert information to database */
    ScoreDao scoreDao;
    /** Gives methods to get or insert information to database */
    UserDao userDao;

    /**
     * Creates new ScoreBoardPanel and daos
     */
    public ScoreBoardLogic() {
        this.scoreDao = new ScoreDao();
        this.userDao = new UserDao();
    }

    /**
     * Get scores list and sort it by score value
     */
    public List<Score> getSortedScoreList(){
        List<Score> scores = scoreDao.getAll();
        scores.sort(Comparator.comparing(Score::getScore).reversed());
        return scores;
    }

    /**
     * Inserts user and his score to database
     */
    public void insertScoreAndUser(Score score){
        scoreDao.insert(score);

    }

    /**
     * Finds and return user id from database with given name
     * @param userName user name
     * @return user id or -1 if he doesn't exist
     */
    public int getIdSpecificUser(String userName){
        User user = userDao.findByName(userName);
        if(user != null){
            return user.getId();
        } else{
            return -1;
        }
    }

    /**
     * Finds and return user from database with given name
     * @param userName user name
     * @return User
     */
    public User getSpecificUser(String userName){
        return userDao.findByName(userName);
    }
}
