package com.example.sensor;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sensor.R;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerEventListener;


    private ImageView imageView;
    private long x;
    private long y;
    private long z;
    private String direction;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelerometerEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                x = Math.round(Math.toDegrees(sensorEvent.values[0]));
                y = Math.round(Math.toDegrees(sensorEvent.values[1]));
                z = Math.round(Math.toDegrees(sensorEvent.values[2]));
//                tvValues.setText(x+"::"+y+"::"+z+"::");
                if (x < -200) {
                    direction = "Avanzar";
                    imageView.setImageResource(R.drawable.avanzar);
                } else if (x > 280) {
                    direction = "Retroceder";
                    imageView.setImageResource(R.drawable.retroceder);
                } else if (y < -200) {
                    direction = "Girar Izquierda";
                    imageView.setImageResource(R.drawable.izquierda);
                } else if (y > 250) {
                    direction = "Girar derecha";
                    imageView.setImageResource(R.drawable.derecha);
                } else {
                    direction = "Stop";
                    imageView.setImageResource(R.drawable.detener);
                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(accelerometerEventListener, accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);


    }
}
