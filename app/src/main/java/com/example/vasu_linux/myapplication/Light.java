package com.example.vasu_linux.myapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Light extends AppCompatActivity implements SensorEventListener {

    ///Declare Sensor Manager///
    private SensorManager sn;
    private Sensor senAcc;
    ///Declare all the needed buttons and TextView///
    private EditText x ;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        ///When created create the sensor manager and the sensor///
        sn = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAcc = sn.getDefaultSensor(Sensor.TYPE_LIGHT);
        sn.registerListener(this,senAcc,sn.SENSOR_DELAY_NORMAL);


        x = (EditText) findViewById(R.id.tv1);///Textview to show the lux values

        b1 = findViewById(R.id.b1);//Start lux
        b2 = findViewById(R.id.b2);//Stop lux
        b3 = findViewById(R.id.b3);//start Mainactivity
        b4 = findViewById(R.id.b4);//start GPS (magnetic)_activity.
        b5 = findViewById(R.id.ex);//Exit the app



        final String b1toast = "Start Sensor Readings";
        final String b2toast = "Stop Sensor Readings";


        ///Start Button///
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b1toast, Toast.LENGTH_SHORT);
                onResume();
                t1.show();

            }
        });
        ///Stop button///
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b2toast, Toast.LENGTH_SHORT);
                onPause();
                t1.show();
            }
        });


        final String b3toast = "You are in  Acclerometer Page";
        final String b4toast = "You are in  Magnetic Field Page";

        ///When pressed start mainactivity show toast and finish///
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b3toast, Toast.LENGTH_SHORT);
                Intent acc = new Intent(Light.this,MainActivity.class);
                startActivity(acc);
                t1.show();
                finish();

            }
        });

        ///When pressed start gps(magnetic) show toast and finish///
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b4toast, Toast.LENGTH_SHORT);
                Intent gps = new Intent(Light.this,GPS.class);
                startActivity(gps);
                t1.show();
                finish();

            }
        });
        ///exit the app///
        final String exToast = "DATA KING EXIT";
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,exToast, Toast.LENGTH_SHORT);
                t1.show();
                finish();
                System.exit(0);
            }
        });


    }




    protected void onPause() {
        super.onPause();
        sn.unregisterListener(this);
    }


    protected void onResume() {
        super.onResume();
        sn.registerListener(this, senAcc, SensorManager.SENSOR_DELAY_NORMAL);


    }





    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {



        Sensor mySensor = sensorEvent.sensor;
        /////Convert the sensor value into string and display via setText
        if (mySensor.getType() == Sensor.TYPE_LIGHT) {
            float xx = sensorEvent.values[0];
            String luxv= Float.toString(xx);
            x.setText("LUX:"+luxv+" lu");
        }




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        /////Dont use
    }
}
