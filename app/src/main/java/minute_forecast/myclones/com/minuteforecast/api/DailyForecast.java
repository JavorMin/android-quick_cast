package minute_forecast.myclones.com.minuteforecast.api;

import java.util.List;

import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.Forecast;
import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.LocationInfo;

public class DailyForecast {
    private LocationInfo city;
    private List<Forecast> list;
    public String getLocationName() {return city.name;}
    public Forecast getForecastForDay(int dayIndex){
        return list.get(dayIndex);
    }
}
