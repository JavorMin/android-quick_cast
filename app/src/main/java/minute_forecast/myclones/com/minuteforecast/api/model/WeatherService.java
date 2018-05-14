package minute_forecast.myclones.com.minuteforecast.api.model;

import minute_forecast.myclones.com.minuteforecast.api.CurrentWeather;
import minute_forecast.myclones.com.minuteforecast.api.DailyForecast;
import minute_forecast.myclones.com.minuteforecast.api.HourlyForecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String API_KEY = "37426f016190340c55b693d9a76e5015";

    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String apiKey, @Query("units") String units);

    @GET("forecast/daily")
    Call<DailyForecast> getDailyForecast(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String apiKey, @Query("cnt") int daysCount, @Query("units") String units);

    @GET("forecast")
    Call<HourlyForecast> getHourlyForecast(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String apiKey, @Query("cnt") int hoursCount, @Query("units") String units);
}
