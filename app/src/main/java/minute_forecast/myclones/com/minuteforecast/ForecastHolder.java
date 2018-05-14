package minute_forecast.myclones.com.minuteforecast;

import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import minute_forecast.myclones.com.minuteforecast.api.ShortForecast;
import minute_forecast.myclones.com.minuteforecast.databinding.ItemDetailWeatherBinding;

public class ForecastHolder extends RecyclerView.ViewHolder {

    private ItemDetailWeatherBinding binding;

    public ForecastHolder(ItemDetailWeatherBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ShortForecast shortForecast) {
        binding.txtTodayDate.setText(new SimpleDateFormat("MM/dd ~ HH:mm").format(new Date(shortForecast.getTimestamp())));
        binding.imgIcon.setImageResource(WeatherUtils.getImageByWeatherType(shortForecast.getWeatherType()));
        binding.txtShortWeather.setText(shortForecast.getWeatherShortDescription());
        binding.grpCard.setBackgroundResource(WeatherUtils.getColorByTemperatureRecView(shortForecast.getTemperature(), shortForecast.getWeatherType()));
        binding.txtTemp.setText(binding.getRoot().getContext().getString(R.string.temperature_holder, (int) shortForecast.getTemperature()));
    }
}
