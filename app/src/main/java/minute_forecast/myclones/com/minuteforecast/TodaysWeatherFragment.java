package minute_forecast.myclones.com.minuteforecast;


import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.net.URL;

import minute_forecast.myclones.com.minuteforecast.api.CurrentWeather;
import minute_forecast.myclones.com.minuteforecast.api.model.Api;
import minute_forecast.myclones.com.minuteforecast.databinding.FragmentTodaysWeatherBinding;
import minute_forecast.myclones.com.minuteforecast.databinding.IncludeWeatherCardBinding;


public class TodaysWeatherFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_GPS = 4;

    private FragmentTodaysWeatherBinding binding;
    private IncludeWeatherCardBinding grpCurrentWether;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location location;

    private OnFragmentInteractionListener listener;

    public TodaysWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todays_weather, container, false);
        setupViews();
        grpCurrentWether.weatherStats.setVisibility(View.VISIBLE);
        return binding.getRoot();
    }

    private void setupViews() {
        grpCurrentWether = DataBindingUtil.bind(binding.grpTodaysWeather.getRoot());

        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshSelected();
            }
        });
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_GPS);
        } else {
            getLocationAndRefresh();
        }
    }

    private void fadeInAnimation(View view) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        fadeOut.setDuration(3000);

        AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeOut);
        mAnimationSet.start();
    }


    @SuppressLint("MissingPermission")
    private void getLocationAndRefresh() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            TodaysWeatherFragment.this.location = location;
                            onRefreshSelected();
                        }
                    }
                });
    }


    private void onRefreshSelected() {
        updateData();
        fadeInAnimation(grpCurrentWether.weatherStats);
    }

    private void updateData() {
        if (location != null) {
            Api.getInstance().getCurrentWeather(location.getLatitude(), location.getLongitude(), new Api.DataListener<CurrentWeather>() {
                @Override
                public void onSuccess(CurrentWeather data) {
                    updateCurrentWeather(data);
                    binding.swiperefresh.setRefreshing(false);
                }

                @Override
                public void onError() {
                    binding.swiperefresh.setRefreshing(false);
                    Toast.makeText(getContext(), "Error while updating current weather", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GPS: {
                //If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocationAndRefresh();
                }
            }
            return;
        }
    }

    private void updateCurrentWeather(CurrentWeather data) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(data.getLocationName());
        grpCurrentWether.txtShortWeather.setText(data.getWeatherShortDescription());
        grpCurrentWether.imgIcon.setImageResource(WeatherUtils.getImageByWeatherType(data.getWeatherType()));
        grpCurrentWether.imgBackground.setImageResource(WeatherUtils.getColorByTemperatureMain(data.getTemperature(), data.getWeatherType()));
        grpCurrentWether.txtTemp.setText(getString(R.string.temperature_holder, (int) data.getTemperature()));
        grpCurrentWether.txtDescription.setText(data.getWeatherLongDescription());
        grpCurrentWether.txtClouds.setText(getString(R.string.percentage_placeholder, (int) data.getCloudinessInPercentage()));
        grpCurrentWether.txtWind.setText(getString(R.string.m_per_s_placeholder, (int) data.getWindSpeed()));
        grpCurrentWether.txtHumidity.setText(getString(R.string.percentage_placeholder, (int) data.getHumidity()));
        grpCurrentWether.icCelcius.setText("º");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(URL url);
    }
}
