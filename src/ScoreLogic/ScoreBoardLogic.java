package ScoreLogic;


import dao.ScoreDao;
import dao.UserDao;
import model.Score;
import model.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreBoardLogic {
    ScoreDao scoreDao;
    UserDao userDao;

    public ScoreBoardLogic() {
        this.scoreDao = new ScoreDao();
        this.userDao = new UserDao();
    }

    public List<Score> getSortedScoreList(){
        List<Score> scores = scoreDao.getAll();
        scores.sort(Comparator.comparing(Score::getScore).reversed());
        return scores;
    }

    public void insertScoreAndUser(Score score){
        scoreDao.insert(score);

    }

    public int getIdSpecificUser(String userName){
        User user = userDao.findByName(userName);
        if(user != null){
            return user.getId();
        } else{
            return -1;
        }
    }

    public User getSpecificUser(String userName){
        return userDao.findByName(userName);
    }
}
