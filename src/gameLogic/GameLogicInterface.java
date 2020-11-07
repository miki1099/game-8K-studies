package gameLogic;

import gameLogic.SiteParameters;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface GameLogicInterface {
    public List<Byte> getClimbersAcclimation();
    public List<Boolean> checkClimbersAreAlive();
    public boolean moveClimberAndShowResults(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition);
    public void makeImpactOnClimber(Climber climber, byte impact);
    public List<JLabel> getCurrentClimbersPosition();
    public byte getTemperature();
    public WeatherType getWeatherType();

}