package minute_forecast.myclones.com.minuteforecast;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.net.URL;

import minute_forecast.myclones.com.minuteforecast.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements TodaysWeatherFragment.OnFragmentInteractionListener, DetailWeatherFragment.OnFragmentInteractionListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        setupTabs();
    }

    private void setupTabs() {
        PagerAdapter adapter = new PagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.grpTabs.setupWithViewPager(binding.viewPager);
        binding.grpTabs.getTabAt(0).setIcon(R.drawable.ic_eye);
        binding.grpTabs.getTabAt(1).setIcon(R.drawable.ic_timer);
    }


    @Override
    public void onFragmentInteraction(URL url) {
    }
}
