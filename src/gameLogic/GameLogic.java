package gameLogic;

import com.google.common.collect.BiMap;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private static GameLogic gameLogic;
    private List<Climber> climbers;
    private Weather weather;
    private short days;
    private byte climber1Height;
    private byte climber2Height;

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

    public boolean moveClimberAndShowResults(BiMap<SiteParameters, JLabel> sitesMap, JLabel siteToGo, JLabel currentPosition){
        Climber climberToMove = getClimberWithSpecificLocation(currentPosition);
        if(climberToMove == null) return false;

        SiteParameters siteParamitersToMove = sitesMap.inverse().get(siteToGo);

        if(siteParamitersToMove.isLegalToMove(climberToMove.getSiteParameters())){
            climberToMove.setCurrentPosition(siteToGo);
            climberToMove.setSiteParameters(siteParamitersToMove);
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
