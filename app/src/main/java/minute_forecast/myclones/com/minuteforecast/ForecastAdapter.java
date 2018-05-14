package minute_forecast.myclones.com.minuteforecast;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import minute_forecast.myclones.com.minuteforecast.api.HourlyForecast;
import minute_forecast.myclones.com.minuteforecast.databinding.ItemDetailWeatherBinding;


public class ForecastAdapter extends RecyclerView.Adapter<ForecastHolder>{

    private HourlyForecast data;

    public ForecastAdapter(HourlyForecast data) {
        this.data = data;
    }

    @Override
    public ForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDetailWeatherBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_detail_weather, parent, false);
        return new ForecastHolder(binding);
    }

    @Override
    public void onBindViewHolder(ForecastHolder holder, int position) {
        holder.bind(data.getForecast().get(position));
    }

    @Override
    public int getItemCount() {
        return data.getForecast().size();
    }
}
