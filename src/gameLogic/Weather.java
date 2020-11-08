package gameLogic;

import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Data
@Builder
class Weather {
    private WeatherType weatherType;
    private byte temperature;
    private byte weatherTrendDays;
    private boolean positiveTrendWeather;

    private final byte MIN_TEMPERATURE = -60;
    private final byte MAX_TEMPERATURE = 10;
    private final byte MAX_TEMPERATURE_CHANGE = 15;
    private final byte MAX_TREND_DAYS = 3;
    private final byte SUNNY_DAY_MOD = 0;
    private final byte CLOUD_DAY_MOD = -3;
    private final byte SNOW_DAY_MOD = -7;

    public void generateNewWeather(){
        Random random = new Random();
        if(weatherTrendDays == 0){
            weatherTrendDays = (byte) (random.nextInt(MAX_TREND_DAYS));
            positiveTrendWeather = random.nextBoolean();
        } else{
            weatherTrendDays--;
        }
        byte tempMod = (byte) (random.nextInt(MAX_TEMPERATURE_CHANGE));
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

    public byte impactFromWeather(boolean isMoving){
        byte weatherImpactMod = SUNNY_DAY_MOD;
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
