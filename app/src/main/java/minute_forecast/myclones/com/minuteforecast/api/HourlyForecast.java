package minute_forecast.myclones.com.minuteforecast.api;

import java.util.List;

import minute_forecast.myclones.com.minuteforecast.api.model.model_helper.LocationInfo;

public class HourlyForecast {
    private LocationInfo city;
    private List<ShortForecast> list;
    public String getLocationName() {
        return city.name;
    }
    public List<ShortForecast> getForecast(){
        return list;
    }
}
