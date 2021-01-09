package gameLogic;

import lombok.Builder;
import lombok.Data;

import java.util.Random;

/**
 * Weather class used to generate weather and calculate impact from weather
 * @author Michal Glodek
 */
@Data
@Builder
class Weather {
    /** actual weather type */
    private WeatherType weatherType;
    /** actual temperature */
    private short temperature;
    /** weather trend days what temperature will grow or fall*/
    private short weatherTrendDays;
    /** temperature will grow or fall */
    private boolean positiveTrendWeather;

    /** min temperature in game */
    private final short MIN_TEMPERATURE = -60;
    /** max temperature in game */
    private final short MAX_TEMPERATURE = 10;
    /** max temperature change in one day in game */
    private final short MAX_TEMPERATURE_CHANGE = 15;
    /** max weather trend days */
    private final short MAX_TREND_DAYS = 3;
    /** sunny day impact on acclimation */
    private final short SUNNY_DAY_MOD = 0;
    /** coloud day impact on acclimation */
    private final short CLOUD_DAY_MOD = -3;
    /** snowy day impact on acclimation */
    private final short SNOW_DAY_MOD = -7;

    /**
     * generates weather for next day
     */
    public void generateNewWeather(){
        Random random = new Random();
        if(weatherTrendDays == 0){
            weatherTrendDays = (short) (random.nextInt(MAX_TREND_DAYS));
            positiveTrendWeather = random.nextBoolean();
        } else{
            weatherTrendDays--;
        }
        short tempMod = (short) (random.nextInt(MAX_TEMPERATURE_CHANGE));
        if(this.temperature <= MIN_TEMPERATURE){
            this.temperature += Math.abs(tempMod);
        } else if(this.temperature >= MAX_TEMPERATURE){
            this.temperature -= Math.abs(tempMod);
        } else{
            if(positiveTrendWeather) this.temperature += tempMod;
            else this.temperature -= tempMod;
        }

        int weatherTypeMod = random.nextInt(3);
        if(weatherTypeMod == 0){
            this.weatherType = WeatherType.SUN;
        } else if(weatherTypeMod == 1 || this.temperature > 0){
            this.weatherType = WeatherType.CLOUDS;
        } else if(weatherTypeMod == 2){
            this.weatherType = WeatherType.SNOW;
        }

    }

    /**
     * calculate impact on climber
     * @param isMoving is climber on the move
     * @return impact on climber acclimation
     */
    public short impactFromWeather(boolean isMoving){
        short weatherImpactMod = SUNNY_DAY_MOD;
        if(this.weatherType == WeatherType.CLOUDS){
            weatherImpactMod -= CLOUD_DAY_MOD;
        } else if(this.weatherType == WeatherType.SNOW){
            weatherImpactMod -= SUNNY_DAY_MOD;
        }
        if(!isMoving){
            weatherImpactMod += this.temperature/10;
        }

        return weatherImpactMod;
    }
}
