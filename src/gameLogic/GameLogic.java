package gameLogic;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameLogic implements GameLogicInterface{
    private static GameLogic gameLogic;
    private List<Climber> climbers;
    private List<Item> items;
    private Weather weather;
    private short days;

    private final byte ACCLIMATION_START_VALUE = 20;
    private final WeatherType WEATHER_TYPE_START_VALUE = WeatherType.SUN;
    private final byte WEATHER_TREND_DAYS_START_VALUE = 2;
    private final byte TEMPERATURE_START_VALUE = 5;
    private final boolean POSITIVE_TREND_START_VALUE = false;

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
            Item item;
            if(i == 0){
                item = new Backpack();
            } else{
                item = new Tent();
            }
            climbers.add(Climber.builder()
                    .currentPosition(currentPositions.get(i))
                    .siteParameters(siteParameters.get(i))
                    .acclimation(ACCLIMATION_START_VALUE)
                    .isAlive(true)
                    .madeToTop(false)
                    .item(item)
                    .movesInOneDay((byte) 0)
                    .build());
        }

        days = 1;
        weather = Weather.builder()
                .temperature(TEMPERATURE_START_VALUE)
                .weatherType(WEATHER_TYPE_START_VALUE)
                .weatherTrendDays(WEATHER_TREND_DAYS_START_VALUE)
                .positiveTrendWeather(POSITIVE_TREND_START_VALUE)
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
    public boolean moveClimberAndShowResults(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition, byte impactFromMove){
        Climber climberToMove = getClimberWithSpecificLocation(currentPosition);
        if(climberToMove == null) return false;

        SiteParameters siteParametersToMove = sitesMap.get(siteToGo);
        SiteParameters currSiteParameters = climberToMove.getSiteParameters();
        if(siteParametersToMove.isLegalToMove(currSiteParameters) && climberToMove.isAlive()){
            climberToMove.setCurrentPosition(siteToGo);
            climberToMove.setSiteParameters(siteParametersToMove);

            makeImpactOnClimber(climberToMove, impactFromMove);
            if(impactFromMove != 0){
                byte movesInDay = climberToMove.getMovesInOneDay();
                climberToMove.setMovesInOneDay(++movesInDay);
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public byte getImpactFromMoving(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition) {
        Climber climberToMove = getClimberWithSpecificLocation(currentPosition);
        if(climberToMove == null) return 127;
        else if(!climberToMove.isAlive()) return 127;

        SiteParameters siteParametersToMove = sitesMap.get(siteToGo);
        SiteParameters currSiteParameters = climberToMove.getSiteParameters();

        if(siteParametersToMove.equals(currSiteParameters)) return 0;

        if(siteParametersToMove.isLegalToMove(currSiteParameters)){
            byte impactFromMove = currSiteParameters.impactFromMove(siteParametersToMove);
            impactFromMove += weather.impactFromWeather(true);
            impactFromMove += climberToMove.impactFromMovesInOneDay();
            impactFromMove += climberToMove.getItem().getMoveImpactMod();
            return impactFromMove;
        } else return 127;
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

            if(acc >= 100 || acc <= -50){
                climber.setAcclimation((byte)100);
            } else if(acc <= 0){
                climber.setAlive(false);
                climber.setAcclimation((byte)0);
            }else {
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
            buf += climber.getItem().getNightImpactMod();
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

    @Override
    public List<Item> getItemsList() {
        List<Item> itemsList = new ArrayList<>();
        for(Climber climber : climbers){
            itemsList.add(climber.getItem());
            itemsList.add(new BlankItem());
        }
        return itemsList;
    }

    @Override
    public void setItemToClimber(Item item, int which) {
        climbers.get(which).setItem(item);
    }
}
