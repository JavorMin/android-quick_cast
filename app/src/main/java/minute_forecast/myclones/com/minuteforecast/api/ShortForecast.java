package minute_forecast.myclones.com.minuteforecast.api;

import java.util.List;

import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.Clouds;
import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.GeneralInfo;
import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.MainMeasurements;
import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.Wind;

public class ShortForecast {
    private long dt;
    private MainMeasurements main;
    private List<GeneralInfo> weather;
    private Clouds clouds;
    private Wind wind;

    public long getTimestamp() {
        return dt * 1000;
    }

    public String getWeatherShortDescription() {
        return weather.get(0).main;
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
}
