package minute_forecast.myclones.com.minuteforecast.api.model.model_helper;

import java.util.List;

import minute_forecast.myclones.com.minuteforecast.api.WeatherType;

public class Forecast {
    private Temperatures temp;
    private double pressure;
    private double humidity;
    private List<GeneralInfo> weather;
    private double speed;
    private double deg;
    private double clouds;

    public String getWeatherShortDescription() {
        return weather.get(0).main;
    }

    public String getWeatherLongDescription() {
        return weather.get(0).description;
    }

    public String getImageURL() {
        return "http://openweathermap.org/img/w/" + weather.get(0).icon + ".png";
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

    public Temperatures getTemperature() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return speed;
    }

    public double getWindDirectionInDegrees() {
        return deg;
    }

    public double getCloudsInPercentage() {
        return clouds;
    }
}
