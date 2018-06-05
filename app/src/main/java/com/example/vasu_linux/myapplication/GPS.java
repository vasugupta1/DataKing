package com.example.vasu_linux.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

////Magenetic feild class
public class GPS extends AppCompatActivity  implements SensorEventListener  {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);


        ///Start the sensor service///
        sn = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAcc = sn.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sn.registerListener(this,senAcc,sn.SENSOR_DELAY_NORMAL);

        x = (EditText) findViewById(R.id.t1);//textview 1
        y = (EditText) findViewById(R.id.t2);//textview 2
        z = (EditText) findViewById(R.id.t3);//textview 3
        b1 = findViewById(R.id.button1);//start button
        b2 = findViewById(R.id.button2);//stop button
        b5 = findViewById(R.id.ex);//exit button



        final String b1toast = "Start Sensor Readings";
        final String b2toast = "Stop Sensor Readings";


        ///Start the sensor///
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              ///create hte toast when pressed start
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b1toast,Toast.LENGTH_SHORT);
                onResume();
                t1.show();

            }
        }



        );

        ///Stop the sensor///
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b2toast,Toast.LENGTH_SHORT);
                onPause();
                t1.show();
            }
        });




        b3 = findViewById(R.id.b3);//launch light
        b4 = findViewById(R.id.b4);//launch acc





        final String b3toast = "You are in Light Page";
        final String b4toast = "You are in Acclerometer Page";
        ///when button is pressed then launch the light activity and finish the current one///
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b3toast,Toast.LENGTH_SHORT);
                Intent lux = new Intent(GPS.this,Light.class);
                startActivity(lux);
                t1.show();
                finish();


            }
        });


        ///when button is pressed then launch the GPS(magnetic) activity and finish the current one///
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast t1 = Toast.makeText(context,b4toast,Toast.LENGTH_SHORT);
                Intent gps = new Intent(GPS.this,MainActivity.class);
                startActivity(gps);
                t1.show();
                finish();
            }
        });


        ///Exit///
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

    ///onSensorChanged method is used to get the sensor values///
    ///Convert all the values of sensor values into string and display via setText
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float xx = sensorEvent.values[0];
        String xvalue= Float.toString(xx);
        x.setText("x:"+xvalue+"uT");

        float yy = sensorEvent.values[1];
        String yvalue= Float.toString(yy);
        y.setText("y:"+yvalue+"uT");

        float zz = sensorEvent.values[2];
        String zvalue= Float.toString(zz);
        z.setText("z:"+zvalue+"uT");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
