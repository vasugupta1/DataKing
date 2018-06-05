package com.example.vasu_linux.myapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//// First activity to be launched
////Remember GPS == magnetic class

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    ///Create Sensor Managers and the sensor for the acc sensor///
    private SensorManager sn;
    private Sensor senAcc;

    ///Declare all the needed buttons and TextView///
    private EditText x ;
    private EditText y ;
    private EditText z ;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///Start the sensor service///
        sn = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ///Set the senAcc as Acclerometer///
        senAcc = sn.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ///Delay normal while you listen the sensor///
        sn.registerListener(this,senAcc,sn.SENSOR_DELAY_NORMAL);

        x = (EditText) findViewById(R.id.tv1);
        y = (EditText) findViewById(R.id.tv2);
        z = (EditText) findViewById(R.id.tv3);

        b1 = findViewById(R.id.b1);////Start Button
        b2 = findViewById(R.id.b2);////Stop Button
        b3 = findViewById(R.id.b3);////Light Button
        b4 = findViewById(R.id.b4);////Mag Button
        b5 = findViewById(R.id.ex);///exit button
        b6 = findViewById(R.id.cc);///launch bluetooth activity


        final String b1toast = "Start Sensor Readings";
        final String b2toast = "Stop Sensor Readings";

        ///resume when pressed and show toast t1///
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onResume();
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b1toast,Toast.LENGTH_SHORT);
                t1.show();

            }
        });
        ///Stop when pressed and show the toast///
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b2toast,Toast.LENGTH_SHORT);
                t1.show();
                onPause();
            }
        });

        final String toast1 = "You are in Light Sensor Page";
        final String toast2 = "You are in Light Magnetic Field Sensor Page";

        ///Open Light activity, show toast and finish this activity///
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent lux = new Intent(MainActivity.this,Light.class);
                Toast t1 = Toast.makeText(context, toast1,Toast.LENGTH_LONG);
                startActivity(lux);
                t1.show();
                finish();

            }
        });


        ///Open GPS(magnetic) activity, show toast and finish this activity///
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t2 = Toast.makeText(context,toast2,Toast.LENGTH_LONG);
                Intent gps = new Intent(MainActivity.this,GPS.class);
                startActivity(gps);
                t2.show();
                finish();

            }
        });
        ///finish this activity///
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

        ///Open Bluetooth activity, show toast///
        final String bt = "BLUETOOTH ACTIVITY";
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent bt = new Intent(MainActivity.this,BlueActivity.class);
              startActivity(bt);

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

    ///onSensorChanged method is used to get the sensor values///
    ///Convert all the values of sensor values into string and display via setText
    @Override
    public void onSensorChanged (SensorEvent sensorEvent){

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            ///xx = x value ///
            float xx = sensorEvent.values[0];
            String xvalue= Float.toString(xx);
            x.setText("x:"+xvalue);
            ///yy = y value///
            float yy = sensorEvent.values[1];
            String yvalue= Float.toString(yy);
            y.setText("y:"+yvalue);
            ///zz = z value///
            float zz = sensorEvent.values[2];
            String zvalue= Float.toString(zz);
            z.setText("z:"+zvalue);

        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        ///Dont use///
    }
}
