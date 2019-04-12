package ru.geekbrains.weatherapp;

import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // передача названия города
        showNameCity();
        // показать или скрыть температуру
        showTemp();
        // показать или скрыть скорость ветра
        showSpeedWind();
        // показать или скрыть влажность
        showWetness();
        // показать дождь
        showRain();
        // показать солнце
        showSun();
        // вывод текущей даты
        showDate();
    }

    private void showNameCity() {
        TextView textView = (TextView) findViewById(R.id.city);
        textView.setText(getIntent().getExtras().getString(getResources().getString(R.string.city_show)));
    }

    private void showDate() {
        DateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM, HH:mm", Locale.getDefault());
        TextView textDate = (TextView) findViewById(R.id.date_id);
        textDate.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));
    }

    // Показать влажность
    public void showWetness() {
        TextView wetness = (TextView) findViewById(R.id.wetness);
        String wet = getIntent().getExtras().getString(getResources().getString(R.string.wetness_show));
        if (wet.equals("View.VISIBLE")) {
            wetness.setVisibility(View.VISIBLE);
        } else {
            wetness.setVisibility(View.INVISIBLE);
        }
    }

    // Показать скорость ветра
    public void showSpeedWind() {
        TextView wind = (TextView) findViewById(R.id.wind_speed);
        String windSpeed = getIntent().getExtras().getString(getResources().getString(R.string.wind_show));
        if (windSpeed.equals("View.VISIBLE")) {
            wind.setVisibility(View.VISIBLE);
        } else {
            wind.setVisibility(View.INVISIBLE);
        }
    }

    // Будет ли дождь?
    public void showRain() {
        Group group = findViewById(R.id.group_rain);
        String str = getIntent().getExtras().getString(getResources().getString(R.string.rain_show));
        if (str.equals("View.VISIBLE")) {
            group.setVisibility(View.VISIBLE);
        } else {
            group.setVisibility(View.INVISIBLE);
        }
    }

    // Солнце
    public void showSun() {
        Group group = findViewById(R.id.group_sun);
        String str = Objects.requireNonNull(getIntent().getExtras()).getString(getResources().getString(R.string.sun_show));
        if (str.equals("View.VISIBLE")) {
            group.setVisibility(View.VISIBLE);
        } else {
            group.setVisibility(View.INVISIBLE);
        }
    }

    // Показать температуру
    public void showTemp() {
        Group group = findViewById(R.id.group);
        String s = getIntent().getExtras().getString(getResources().getString(R.string.temp_show));
        if (s.equals("View.VISIBLE")) {
            group.setVisibility(View.VISIBLE);
        } else {
            group.setVisibility(View.INVISIBLE);
        }
    }
}
