package gameLogic;

import gameLogic.SiteParameters;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Interface for game logic
 * @author Michal Glodek
 */
public interface GameLogicInterface {
    public List<Short> getClimbersAcclimation();
    public List<Boolean> checkClimbersAreAlive();
    public boolean moveClimberAndShowResults(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition, short impactFromMove);
    public short getImpactFromMoving(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition);
    public void makeImpactOnClimber(Climber climber, short impact);
    public List<JLabel> getCurrentClimbersPosition();
    public short getTemperature();
    public WeatherType getWeatherType();
    public List<Short> getImpactForNextDay();
    public void makeNextDay();
    public short getDay();
    public List<Item> getItemsList();
    public void setItemToClimber(Item item, int which);
    public List<Item> getItemsLeft();
    public List<JLabel> getCurrentPositionsItemsLeft();
    public boolean doesClimberCanStandOnItem(JLabel climberPosition, JLabel siteToGo);
    public boolean isGameReadyToFinish();
    public int getWinScore();
}
