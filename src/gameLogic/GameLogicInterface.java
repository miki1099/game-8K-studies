package gameLogic;

import gameLogic.SiteParameters;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface GameLogicInterface {
    public List<Byte> getClimbersAcclimation();
    public List<Boolean> checkClimbersAreAlive();
    public boolean moveClimberAndShowResults(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition, byte impactFromMove);
    public byte getImpactFromMoving(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition);
    public void makeImpactOnClimber(Climber climber, byte impact);
    public List<JLabel> getCurrentClimbersPosition();
    public byte getTemperature();
    public WeatherType getWeatherType();
    public List<Byte> getImpactForNextDay();
    public void makeNextDay();
    public short getDay();
    public List<Item> getItemsList();
    public void setItemToClimber(Item item, int which);

}
