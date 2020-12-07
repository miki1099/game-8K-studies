package gameLogic;


import gui.QuickTimeEventDialog;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameLogic implements GameLogicInterface{
    private static GameLogic gameLogic;
    private List<Climber> climbers;
    private List<Item> itemsLeft;
    private Weather weather;
    private short days;
    @Setter
    private short quickEventAccMod;

    private final short ACCLIMATION_START_VALUE = 20;
    private final WeatherType WEATHER_TYPE_START_VALUE = WeatherType.SUN;
    private final short WEATHER_TREND_DAYS_START_VALUE = 2;
    private final short TEMPERATURE_START_VALUE = 5;
    private final boolean POSITIVE_TREND_START_VALUE = false;
    private static final short MAX_HEIGHT_LEVEL = 5;
    private final int CHANCES_TO_GET_QUICK_TIME_EVENT = 25; // from 0 to 100

    private GameLogic(List<JLabel> currentPositions, List<SiteParameters> siteParameters) {
        initGame(currentPositions, siteParameters);
    }

    public static GameLogic getInstance(List<JLabel> currentPositions, List<SiteParameters> siteParameters){
        gameLogic = new GameLogic(currentPositions, siteParameters);
        return gameLogic;
    }
    public static GameLogic getInstance(){
        return gameLogic;
    }

    private void initGame(List<JLabel> currentPositions, List<SiteParameters> siteParameters){
        days = 1;

        itemsLeft = new ArrayList<>();

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
                    .readyToEndGame(false)
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
            } else if(siteParametersToMove.level == 0 && climberToMove.isMadeToTop()){
                climberToMove.setReadyToEndGame(true);
            }

            Item climberItem = climberToMove.getItem();
            for(int i = 0; i < itemsLeft.size(); i++){
                Item item = itemsLeft.get(i);
                if(climberItem instanceof BlankItem && item.getActualSite().equals(siteToGo) && (item instanceof Backpack || item instanceof Tent)){
                    climberToMove.setItem(item);
                    itemsLeft.set(i, new BlankItem());
                    break;
                }
            }
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
        Random random = new Random();
        if(CHANCES_TO_GET_QUICK_TIME_EVENT >= random.nextInt(100) + 1){
            QuickTimeEventDialog quicktimeDialog = new QuickTimeEventDialog(new Frame(),true);
            quicktimeDialog.setVisible(true);
        } else{
            quickEventAccMod = 0;
        }

        for(int i = 0; i < impactBuf.size(); i++){
            impactBuf.set(i, (short) (impactBuf.get(i) + quickEventAccMod));
        }
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

    @Override
    public List<JLabel> getCurrentPositionsItemsLeft() {
        List<JLabel> currItemPos= new ArrayList<>();
        for(Item item : itemsLeft){
            currItemPos.add(item.getActualSite());
        }
        return currItemPos;
    }

    @Override
    public boolean doesClimberCanStandOnItem(JLabel climberPosition, JLabel siteToGo) {
        Item climberItem = getClimberWithSpecificLocation(climberPosition).getItem();
        return !(climberItem instanceof Backpack) && !(climberItem instanceof Tent);

    }

    @Override
    public boolean isGameReadyToFinish() {
        boolean readyToEndGame = false;
        for(Climber climber : climbers){
            if(!climber.isReadyToEndGame() && climber.isAlive()){
                return false;
            } else if(climber.isReadyToEndGame() || !climber.isAlive()){
                readyToEndGame = true;
            }
        }
        return  readyToEndGame;

    }
}
