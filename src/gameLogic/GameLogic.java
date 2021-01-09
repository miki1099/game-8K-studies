package gameLogic;


import gui.QuickTimeEventDialog;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * game logic - main core of game algorithms
 * @author Michal Glodek
 */
public class GameLogic implements GameLogicInterface{
    /** own instance singleton*/
    private static GameLogic gameLogic;
    /** list of climbers */
    private List<Climber> climbers;
    /** items left on the game board */
    private List<Item> itemsLeft;
    /** actual weather */
    private Weather weather;
    /** dayc counter */
    private short days;
    /** impact from quick events */
    @Setter
    private short quickEventAccMod;

    /** climbers starts with this amount of acclimation */
    private final short ACCLIMATION_START_VALUE = 20;
    /** type of weather for start */
    private final WeatherType WEATHER_TYPE_START_VALUE = WeatherType.SUN;
    /** trend weather days from start */
    private final short WEATHER_TREND_DAYS_START_VALUE = 2;
    /** initial temperature value */
    private final short TEMPERATURE_START_VALUE = 5;
    /** initial trend for temperature*/
    private final boolean POSITIVE_TREND_START_VALUE = false;
    /** max level of height */
    private static final short MAX_HEIGHT_LEVEL = 5;
    /** chances to get quick time event in % */
    private final int CHANCES_TO_GET_QUICK_TIME_EVENT = 20; // from 0 to 100

    /**
     * Creates new GameLogic
     * @param currentPositions list current climbers positions
     * @param siteParameters list os site parameters
     */
    private GameLogic(List<JLabel> currentPositions, List<SiteParameters> siteParameters) {
        initGame(currentPositions, siteParameters);
    }

    /**
     * method to get instance and it creates new instance
     * @return new game logic instance
     */
    public static GameLogic getInstance(List<JLabel> currentPositions, List<SiteParameters> siteParameters){
        gameLogic = new GameLogic(currentPositions, siteParameters);
        return gameLogic;
    }
    /**
     * return game logic instance
     * @return game logic instance
     */
    public static GameLogic getInstance(){
        return gameLogic;
    }

    /**
     * method initialize game
     * @param currentPositions list current climbers positions
     * @param siteParameters list os site parameters
     */
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

    /**
     * returns climbers acclimation
     * @return list of climbers actual acclimation
     */
    @Override
    public List<Short> getClimbersAcclimation(){
        List<Short> accList = new ArrayList<>();
        for (Climber climber : climbers){
            accList.add(climber.getAcclimation());
        }
        return accList;
    }

    /**
     * checks if climbers are alive
     * @return boolean list if they are alive
     */
    @Override
    public List<Boolean> checkClimbersAreAlive(){
        List<Boolean> aliveList = new ArrayList<>();
        for (Climber climber : climbers){
            aliveList.add(climber.isAlive());
        }
        return aliveList;
    }

    /**
     * checks it climber can go, moves climber and make impact on moving climber
     * @param sitesMap all sites map
     * @param siteToGo site with climber want to go
     * @param currentPosition climber current position
     * @param impactFromMove basic impact from move
     * @return boolean if move was legal to do
     */
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

    /**
     * method calculate impact on moving climber
     * @param sitesMap all sites map
     * @param siteToGo site with climber want to go
     * @param currentPosition climber current position
     * @return impact on climber
     */
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

    /**
     * gives user object with stand on given site
     * @param currentPosition current position co search
     * @return climber object
     */
    private Climber getClimberWithSpecificLocation(JLabel currentPosition){
        for(Climber climber : climbers){
            if(climber.getCurrentPosition().equals(currentPosition)){
                return climber;
            }
        }
        return null;
    }

    /**
     * makes impact on specific climber
     * @param climber climber to take impact
     * @param impact impact value
     */
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

    /**
     * returns climbers positions
     * @return list of climbers positions
     */
    @Override
    public List<JLabel> getCurrentClimbersPosition(){
        List<JLabel> currPos= new ArrayList<>();
        for(Climber climber : climbers){
            currPos.add(climber.getCurrentPosition());
        }
        return currPos;
    }

    /**
     * returns actual temperature
     * @return actual temperature
     */
    @Override
    public short getTemperature() {
        return weather.getTemperature();
    }

    /**
     * returns actual weather type
     * @return actual weather type
     */
    @Override
    public WeatherType getWeatherType() {
        return weather.getWeatherType();
    }

    /**
     * return calculated impact for the next day
     * @return impact list
     */
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

    /**
     * makes all changes on climber and weather for next day
     */
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

    /**
     * returns actual day
     * @return actual day
     */
    @Override
    public short getDay() {
        return days;
    }

    /**
     * returns actual climber list items
     * @return items list
     */
    @Override
    public List<Item> getItemsList() {
        List<Item> itemsList = new ArrayList<>();
        for(Climber climber : climbers){
            itemsList.add(climber.getItem());
            itemsList.add(new BlankItem());
        }
        return itemsList;
    }

    /**
     * set item to specific climber
     * @param which which climber
     * @param item which item
     */
    @Override
    public void setItemToClimber(Item item, int which) {
        Item itemToLeave = climbers.get(which).getItem();
        if(!itemToLeave.equals(item)){
            itemsLeft.set(which, itemToLeave);
            climbers.get(which).setItem(item);
        }

    }

    /**
     * getter list items left on game board
     * @return list items left on game board
     */
    @Override
    public List<Item> getItemsLeft() {
        return itemsLeft;
    }

    /**
     * getter list items position
     * @return list items position
     */
    @Override
    public List<JLabel> getCurrentPositionsItemsLeft() {
        List<JLabel> currItemPos= new ArrayList<>();
        for(Item item : itemsLeft){
            currItemPos.add(item.getActualSite());
        }
        return currItemPos;
    }

    /**
     * checks if climber can get other item
     * @return boolean if he can
     */
    @Override
    public boolean doesClimberCanStandOnItem(JLabel climberPosition, JLabel siteToGo) {
        Item climberItem = getClimberWithSpecificLocation(climberPosition).getItem();
        return !(climberItem instanceof Backpack) && !(climberItem instanceof Tent);

    }

    /**
     * checks if game is ready to end
     * @return boolean
     */
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

    /**
     * calculate game score
     * @return game score
     */
    @Override
    public int getWinScore() {
        List<Boolean> climbersAlive = checkClimbersAreAlive();
        int score = 500;
        for (boolean areAlive : climbersAlive){
            if(areAlive){
                score *= 2;
            }
        }
        score -= days;

        return score;
    }
}
