package gameLogic;

import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Data
@Builder
class Weather {
    private WeatherType weatherType;
    private byte temperature;


    public void generateNewWeather(){
        Random random = new Random();
        byte tempMod = (byte) (random.nextInt(20) - 10);
        if(this.temperature <= -60){
            this.temperature += Math.abs(tempMod);
        } else if(this.temperature >= 25){
            this.temperature -= Math.abs(tempMod);
        } else{
            this.temperature += tempMod;
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

    public byte impactFromWeather(){
        byte weatherImpactMod = 0;
        if(this.weatherType == WeatherType.CLOUDS){
            weatherImpactMod -= 2;
        } else if(this.weatherType == WeatherType.SNOW){
            weatherImpactMod -= 7;
        }

        weatherImpactMod += this.temperature/10;

        return weatherImpactMod;
    }
}
