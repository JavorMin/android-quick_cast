package minute_forecast.myclones.com.minuteforecast;

import static minute_forecast.myclones.com.minuteforecast.api.WeatherType.*;

public class WeatherUtils {

    public static int getColorByTemperatureMain(double temperatureInCelcius, int weatherType) {

        if (weatherType == TUNDERSTORM){
            return R.drawable.main_tunder_f;
        }else if (weatherType == RAIN){
            return R.drawable.main_rain_f;
        }

        if (temperatureInCelcius > 25) {
            return R.drawable.main_sunny_f;
        } else if (temperatureInCelcius > 15) {
            return R.drawable.main_normal_f;
        } else if (temperatureInCelcius > 0) {
            return R.drawable.main_normal_f;
        }else {
            return R.drawable.main_fog_f;
        }
    }

    public static int getColorByTemperatureRecView(double temperatureInCelcius, int weatherType) {

        if (weatherType == TUNDERSTORM){
            return R.drawable.rec_tunder_f;
        }else if (weatherType == RAIN){
            return R.drawable.rec_rain_f;
        }

        if (temperatureInCelcius > 25) {
            return R.drawable.rec_sunny_f;
        } else if (temperatureInCelcius > 15) {
            return R.drawable.rec_normal_f;
        } else if (temperatureInCelcius > 0) {
            return R.drawable.rec_normal_f;
        }else {
            return R.drawable.rec_fog_f;
        }
    }

    public static int getImageByWeatherType(int weatherType) {
        switch (weatherType) {
            case TUNDERSTORM:
                return R.drawable.ic_weather_cloud_bolt_rain;
            case DRIZZLE:
                return R.drawable.ic_weather_cloud_storm;
            case RAIN:
                return R.drawable.ic_weather_cloud_heavy_rain;
            case SNOW:
                return R.drawable.ic_weather_cloud_more_snow;
            case FOG:
                return R.drawable.ic_weather_cloud_wind;
            case CLOUDS:
                return R.drawable.ic_weather_clouds;
            case VARIOUS:
                return R.drawable.ic_weather_storm;
            case EXTREME:
                return R.drawable.ic_weather_storm;
            case CLEAR:
                return R.drawable.ic_weather_sun;
            default:
                return R.drawable.ic_weather_sun;
        }
    }


}
