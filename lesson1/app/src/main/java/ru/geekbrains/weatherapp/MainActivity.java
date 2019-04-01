package ru.geekbrains.weatherapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String str;
    private Switch aSwitch;
    private Switch rainSwitch;
    private Switch sunSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setSunSwitch();
        setRainSwitch();
        addCity();
    }

    private void addCity() {
        Button sendButton = (Button) findViewById(R.id.send_btn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.editCityText);
                str = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                windSpeed(intent);
                rainSwitch(intent);
                sunSwitch(intent);
                wetnessSwitch(intent);
                showTemp(intent);
                checkBtn(editText, intent);
            }
        });
    }

    public void checkBtn(EditText editText, Intent intent) {
        // Проверка на введенное значение, если поле пустое, то уведомление
        if (editText.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getResources().getString(R.string.attention));
            builder.setMessage(getResources().getString(R.string.fill_field));
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            intent.putExtra(getResources().getString(R.string.city_show), str);
            startActivity(intent);
        }
    }

    public void showTemp(Intent intent) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        if (checkBox.isChecked()) {
            intent.putExtra(getResources().getString(R.string.temp_show), "View.VISIBLE");
        } else {
            intent.putExtra(getResources().getString(R.string.temp_show), "View.INVISIBLE");
        }
    }

    public void wetnessSwitch(Intent intent) {
        aSwitch = (Switch) findViewById(R.id.switch_for_wetness);
        if (aSwitch.isChecked()) {
            intent.putExtra(getResources().getString(R.string.wetness_show), "View.VISIBLE");
        } else {
            intent.putExtra(getResources().getString(R.string.wetness_show), "View.INVISIBLE");
        }
    }

    public void rainSwitch(Intent intent) {
        if (rainSwitch.isChecked()) {
            intent.putExtra(getResources().getString(R.string.rain_show), "View.VISIBLE");
        } else {
            intent.putExtra(getResources().getString(R.string.rain_show), "View.INVISIBLE");
        }
    }

    public void sunSwitch(Intent intent) {
        if (sunSwitch.isChecked()) {
            intent.putExtra(getResources().getString(R.string.sun_show), "View.VISIBLE");
        } else {
            intent.putExtra(getResources().getString(R.string.sun_show), "View.INVISIBLE");
        }
    }

    public void setSunSwitch() {
        sunSwitch = (Switch) findViewById(R.id.switch_for_sun);
        sunSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sunSwitch.isChecked()) {
                    rainSwitch.setChecked(false);
                }
            }
        });
    }

    public void setRainSwitch() {
        rainSwitch = (Switch) findViewById(R.id.switch_for_rain);
        rainSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rainSwitch.isChecked()) {
                    sunSwitch.setChecked(false);
                }
            }
        });
    }

    public void windSpeed(Intent intent) {
        aSwitch = (Switch) findViewById(R.id.switch_for_speed);
        if (aSwitch.isChecked()) {
            intent.putExtra(getResources().getString(R.string.wind_show), "View.VISIBLE");
        } else {
            intent.putExtra(getResources().getString(R.string.wind_show), "View.INVISIBLE");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menu_clear) {
            EditText editText = (EditText) findViewById(R.id.editCityText);
            if (editText != null) {
                editText.setText("");
                Toast.makeText(this, "Очистили поле город", Toast.LENGTH_SHORT).show();
            }
        }

        if (id == R.id.menu_add) {
            EditText editText = (EditText) findViewById(R.id.editCityText);
            if (editText != null) {
                str = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                windSpeed(intent);
                rainSwitch(intent);
                sunSwitch(intent);
                wetnessSwitch(intent);
                showTemp(intent);
                checkBtn(editText, intent);
            }
        }

        if (id == R.id.menu_info) {
            Toast.makeText(this, "Вызов информации", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        int optionId = R.layout.content_main;

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            optionId = R.layout.nav_gallery;
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_dev) {
            optionId = R.layout.nav_developers;
        } else if (id == R.id.nav_sup) {
            optionId = R.layout.nav_support;
        } else if (id == R.id.nav_sup) {
            optionId = R.layout.nav_support;
        } else if (id == R.id.nav_contacts) {
            optionId = R.layout.nav_contacts;
        } else if (id == R.id.nav_rate) {
            optionId = R.layout.nav_rate;
        }
        ViewGroup parent = (ViewGroup) findViewById(R.id.content);
        parent.removeAllViews();
        View newContent = getLayoutInflater().inflate(optionId, parent, false);
        parent.addView(newContent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
