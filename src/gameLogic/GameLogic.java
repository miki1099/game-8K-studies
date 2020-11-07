package gameLogic;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameLogic {
    private static GameLogic gameLogic;
    private List<Climber> climbers;
    private Weather weather;
    private short days;

    private GameLogic(List<JLabel> currentPositions, List<SiteParameters> siteParameters) {
        initGame(currentPositions, siteParameters);
    }

    public static GameLogic getInstance(List<JLabel> currentPositions, List<SiteParameters> siteParameters){
        gameLogic = new GameLogic(currentPositions, siteParameters);
        return gameLogic;
    }

    private void initGame(List<JLabel> currentPositions, List<SiteParameters> siteParameters){
        climbers = new ArrayList<>();
        for(int i = 0; i < currentPositions.size(); i++){
            climbers.add(Climber.builder().currentPosition(currentPositions.get(i)).siteParameters(siteParameters.get(i)).build());
        }

    }

    private Climber getClimberWithSpecificLocation(JLabel currentPosition){
        for(Climber climber : climbers){
            if(climber.getCurrentPosition().equals(currentPosition)){
                return climber;
            }
        }
        return null;
    }

    public boolean moveClimberAndShowResults(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition){
        Climber climberToMove = getClimberWithSpecificLocation(currentPosition);
        if(climberToMove == null) return false;

        SiteParameters siteParametersToMove = sitesMap.get(siteToGo);

        if(siteParametersToMove.isLegalToMove(climberToMove.getSiteParameters())){
            climberToMove.setCurrentPosition(siteToGo);
            climberToMove.setSiteParameters(siteParametersToMove);
            return true;
        } else {
            return false;
        }

    }


    public List<JLabel> getCurrentClimbersPosition(){
        List<JLabel> currPos= new ArrayList<>();
        for(Climber climber : climbers){
            currPos.add(climber.getCurrentPosition());
        }
        return currPos;
    }
}
