package gameLogic;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameLogic implements GameLogicInterface{
    private static GameLogic gameLogic;
    private List<Climber> climbers;
    private List<Item> itemsLeft;
    private Weather weather;
    private short days;

    private final short ACCLIMATION_START_VALUE = 20;
    private final WeatherType WEATHER_TYPE_START_VALUE = WeatherType.SUN;
    private final short WEATHER_TREND_DAYS_START_VALUE = 2;
    private final short TEMPERATURE_START_VALUE = 5;
    private final boolean POSITIVE_TREND_START_VALUE = false;
    private static final short MAX_HEIGHT_LEVEL = 5;

    private GameLogic(List<JLabel> currentPositions, List<SiteParameters> siteParameters) {
        initGame(currentPositions, siteParameters);
    }

    public static GameLogic getInstance(List<JLabel> currentPositions, List<SiteParameters> siteParameters){
        gameLogic = new GameLogic(currentPositions, siteParameters);
        return gameLogic;
    }

    private void initGame(List<JLabel> currentPositions, List<SiteParameters> siteParameters){
        days = 1;

        itemsLeft = new ArrayList<>();
        itemsLeft.add(new BlankItem());

        climbers = new ArrayList<>();
        for(int i = 0; i < currentPositions.size(); i++){
            Item item;
            if(i == 0){
                item = new Backpack();
            } else{
                item = new Tent();
            }
            item.setActualSite(currentPositions.get(i));

            itemsLeft.add(new BlankItem());

            climbers.add(Climber.builder()
                    .currentPosition(currentPositions.get(i))
                    .siteParameters(siteParameters.get(i))
                    .acclimation(ACCLIMATION_START_VALUE)
                    .isAlive(true)
                    .madeToTop(false)
                    .item(item)
                    .movesInOneDay((short) 0)
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
    public List<Short> getClimbersAcclimation(){
        List<Short> accList = new ArrayList<>();
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
    public boolean moveClimberAndShowResults(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition, short impactFromMove){
        Climber climberToMove = getClimberWithSpecificLocation(currentPosition);
        if(climberToMove == null) return false;

        SiteParameters siteParametersToMove = sitesMap.get(siteToGo);
        SiteParameters currSiteParameters = climberToMove.getSiteParameters();
        if(siteParametersToMove.isLegalToMove(currSiteParameters) && climberToMove.isAlive()){
            climberToMove.setCurrentPosition(siteToGo);
            climberToMove.setSiteParameters(siteParametersToMove);
            if(siteParametersToMove.level == MAX_HEIGHT_LEVEL){
                climberToMove.setMadeToTop(true);
            }

            Item climberItem = climberToMove.getItem();
            climberItem.setActualSite(siteToGo);

            makeImpactOnClimber(climberToMove, impactFromMove);
            if(impactFromMove != 0){
                short movesInDay = climberToMove.getMovesInOneDay();
                climberToMove.setMovesInOneDay(++movesInDay);
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public short getImpactFromMoving(Map<JLabel, SiteParameters> sitesMap, JLabel siteToGo, JLabel currentPosition) {
        Climber climberToMove = getClimberWithSpecificLocation(currentPosition);
        if(climberToMove == null) return 127;
        else if(!climberToMove.isAlive()) return 127;

        SiteParameters siteParametersToMove = sitesMap.get(siteToGo);
        SiteParameters currSiteParameters = climberToMove.getSiteParameters();

        if(siteParametersToMove.equals(currSiteParameters)) return 0;

        if(siteParametersToMove.isLegalToMove(currSiteParameters)){
            short impactFromMove = currSiteParameters.impactFromMove(siteParametersToMove);
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
    public void makeImpactOnClimber(Climber climber, short impact){
        if(climber.isAlive()){
            short acc = (short) (climber.getAcclimation() + impact);

            if(acc >= 100){
                climber.setAcclimation((short)100);
            } else if(acc <= 0){
                climber.setAlive(false);
                climber.setAcclimation((short)0);
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
    public short getTemperature() {
        return weather.getTemperature();
    }

    @Override
    public WeatherType getWeatherType() {
        return weather.getWeatherType();
    }

    @Override
    public List<Short> getImpactForNextDay() {
        short nightWeatherImpact = 0;
        nightWeatherImpact += weather.impactFromWeather(false);
        List<Short> nightImpactList = new ArrayList<>();
        for(Climber climber : climbers){
            short buf = (short)(climber.getSiteParameters().getImpactFromSiteNextDay() + nightWeatherImpact);
            buf += climber.getItem().getNightImpactMod();
            nightImpactList.add(buf);
        }
        return nightImpactList;
    }

    @Override
    public void makeNextDay() {
        days++;

        List<Short> impactBuf = getImpactForNextDay();
        for(int i = 0; i < climbers.size(); i++){
            JLabel actualSite = climbers.get(i).getCurrentPosition();
            climbers.get(i).getItem().setActualSite(actualSite);
            makeImpactOnClimber(climbers.get(i),impactBuf.get(i));
            climbers.get(i).setMovesInOneDay((short) 0);
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
        Item itemToLeave = climbers.get(which).getItem();
        if(!itemToLeave.equals(item)){
            itemsLeft.set(which, itemToLeave);
            climbers.get(which).setItem(item);
        }

    }

    @Override
    public List<Item> getItemsLeft() {
        return itemsLeft;
    }
}
