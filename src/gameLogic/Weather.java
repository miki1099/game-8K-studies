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

    public void generateNewWeather(){
        Random random = new Random();
        if(weatherTrendDays == 0){
            weatherTrendDays = (byte) (random.nextInt(3));
            positiveTrendWeather = random.nextBoolean();
        } else{
            weatherTrendDays--;
        }
        byte tempMod = (byte) (random.nextInt(15));
        if(this.temperature <= -60){
            this.temperature += Math.abs(tempMod);
        } else if(this.temperature >= 25){
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
        byte weatherImpactMod = 0;
        if(this.weatherType == WeatherType.CLOUDS){
            weatherImpactMod -= 2;
        } else if(this.weatherType == WeatherType.SNOW){
            weatherImpactMod -= 7;
        }
        if(!isMoving){
            weatherImpactMod += this.temperature/10;
        }

        return weatherImpactMod;
    }
}
