package gameLogic;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameLogic implements GameLogicInterface{
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
        days = 1;

        climbers = new ArrayList<>();
        for(int i = 0; i < currentPositions.size(); i++){
            climbers.add(Climber.builder()
                    .currentPosition(currentPositions.get(i))
                    .siteParameters(siteParameters.get(i))
                    .acclimation((byte) 20)
                    .isAlive(true)
                    .movesInOneDay((byte) 0)
                    .build());
        }

        days = 1;
        weather = Weather.builder()
                .temperature((byte) 20)
                .weatherType(WeatherType.SUN)
                .weatherTrendDays((byte) 2)
                .positiveTrendWeather(false)
                .build();
    }

    @Override
    public List<Byte> getClimbersAcclimation(){
        List<Byte> accList = new ArrayList<>();
        for (Climber climber : climbers){
            accList.add(climber.getAcclimation());
        }
        return accList;
    }

    @Override
    public List<Boolean> checkClimbersAreAlive(){
        List<Boolean> aliveList = new ArrayList<>();
        for (Climber climber : climbers){
            aliveList.add(climber.isAlive());
        }
        return aliveList;
    }

    @Override
    public boolean moveClimberAndShowResults(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition){
        Climber climberToMove = getClimberWithSpecificLocation(currentPosition);
        if(climberToMove == null) return false;

        SiteParameters siteParametersToMove = sitesMap.get(siteToGo);
        SiteParameters currSiteParameters = climberToMove.getSiteParameters();
        if(siteParametersToMove.isLegalToMove(currSiteParameters) && climberToMove.isAlive()){
            climberToMove.setCurrentPosition(siteToGo);
            climberToMove.setSiteParameters(siteParametersToMove);

            byte impactFromMove = currSiteParameters.impactFromMove(siteParametersToMove);
            impactFromMove += weather.impactFromWeather(true);
            impactFromMove += climberToMove.impactFromMovesInOneDay();
            makeImpactOnClimber(climberToMove, impactFromMove);

            byte movesInDay = climberToMove.getMovesInOneDay();
            climberToMove.setMovesInOneDay(++movesInDay);
            return true;
        } else {
            return false;
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

    @Override
    public void makeImpactOnClimber(Climber climber, byte impact){
        if(climber.isAlive()){
            byte acc = (byte) (climber.getAcclimation() + impact);

            if(acc <= 0){
                climber.setAlive(false);
                climber.setAcclimation((byte)0);
            } else if(acc >= 100){
                climber.setAcclimation((byte)100);
            } else {
                climber.setAcclimation(acc);
            }
        }
    }

    @Override
    public List<JLabel> getCurrentClimbersPosition(){
        List<JLabel> currPos= new ArrayList<>();
        for(Climber climber : climbers){
            currPos.add(climber.getCurrentPosition());
        }
        return currPos;
    }

    @Override
    public byte getTemperature() {
        return weather.getTemperature();
    }

    @Override
    public WeatherType getWeatherType() {
        return weather.getWeatherType();
    }

    @Override
    public List<Byte> getImpactForNextDay() {
        byte nightWeatherImpact = 0;
        nightWeatherImpact += weather.impactFromWeather(false);
        List<Byte> nightImpactList = new ArrayList<>();
        for(Climber climber : climbers){
            byte buf = (byte)(climber.getSiteParameters().getImpactFromSiteNextDay() + nightWeatherImpact);
            nightImpactList.add(buf);
        }
        return nightImpactList;
    }

    @Override
    public void makeNextDay() {
        days++;
        List<Byte> impactBuf = getImpactForNextDay();
        for(int i = 0; i < climbers.size(); i++){
            makeImpactOnClimber(climbers.get(i),impactBuf.get(i));
            climbers.get(i).setMovesInOneDay((byte) 0);
        }
        weather.generateNewWeather();
    }

    @Override
    public short getDay() {
        return days;
    }
}
