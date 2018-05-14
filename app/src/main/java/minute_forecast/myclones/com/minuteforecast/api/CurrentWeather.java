package minute_forecast.myclones.com.minuteforecast.api;

import java.util.List;

import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.Clouds;
import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.GeneralInfo;
import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.MainMeasurements;
import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.Wind;

public class CurrentWeather {
    private String name;
    private List<GeneralInfo> weather;
    private MainMeasurements main;
    private Wind wind;
    private Clouds clouds;

    public String getLocationName() {
        return name;
    }

    public String getWeatherShortDescription() {
        return weather.get(0).main;
    }

    public String getWeatherLongDescription() {
        return weather.get(0).description;
    }

    public int getWeatherType() {
        if (weather.get(0).id == WeatherType.CLEAR) {
            return WeatherType.CLEAR;
        } else if (weather.get(0).id / 10 == WeatherType.EXTREME) {
            return WeatherType.EXTREME;
        } else {
            return weather.get(0).id / 100;
        }
    }

    public double getTemperature() {
        return main.temp;
    }

    public double getMaxTemperature() {
        return main.temp_max;
    }

    public double getMinTemperature() {
        return main.temp_min;
    }

    public double getPressure() {
        return main.pressure;
    }

    public double getHumidity() {
        return main.humidity;
    }

    public double getWindSpeed() {
        return wind.speed;
    }

    public double getWindDirectionInDegree() {
        return wind.deg;
    }

    public double getCloudinessInPercentage() {
        return clouds.all;
    }

}
