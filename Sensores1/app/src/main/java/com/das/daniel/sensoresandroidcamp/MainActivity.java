package com.das.daniel.sensoresandroidcamp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView _tvAcelerometro, _tvProximidad, _tvLuz, _tvGiroscopio, _tvMagnetometro, _tvGravedad, _tvRotacion;
    private SensorManager sensorManager;
    private Sensor mAccelerometer, mProximidad, mLuz, mGiroscopio,mMagnetometro, mGravedad, mRotacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mProximidad=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mLuz=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mGiroscopio=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mMagnetometro=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mGravedad=sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mRotacion=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        _tvAcelerometro= (TextView) findViewById(R.id.tvAcelerometro);
        _tvProximidad= (TextView) findViewById(R.id.tvProximidad);
        _tvLuz= (TextView) findViewById(R.id.tvLuz);
        _tvGiroscopio= (TextView) findViewById(R.id.tvGiroscopio);
        _tvMagnetometro= (TextView) findViewById(R.id.tvMagnetometro);
        _tvGravedad= (TextView) findViewById(R.id.tvGravedad);
        _tvRotacion= (TextView) findViewById(R.id.tvRotacion);

        /*//Saber que sensores tiene nuestro dispositivo
        List<Sensor> lista=sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i=1; i<lista.size(); i++)
        {
            _tvTexto.append(lista.get(i).getName()+"\n"+lista.get(i).getVendor()+"\n"+lista.get(i).getVersion()+"\n"+"\n"+"\n");
        }*/
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            SensorAcelerometro(sensorEvent);
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY)
        {
            SensorProximidad(sensorEvent);
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT)
        {
            SensorLuz(sensorEvent);
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_GYROSCOPE)
        {
            SensorGiroscopio(sensorEvent);
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD)
        {
            SensorMagnetometro(sensorEvent);
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_GRAVITY)
        {
            SensorGravedad(sensorEvent);
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_ROTATION_VECTOR)
        {
            SensorRotacion(sensorEvent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void SensorAcelerometro(SensorEvent sensorEvent)
    {
        float x,y,z;
        x=sensorEvent.values[0];
        y=sensorEvent.values[1];
        z=sensorEvent.values[2];

        _tvAcelerometro.setText("");
        _tvAcelerometro.append("Valor x: "+x+ "\nValor y: "+y+"\nValor z: "+z);
    }

    private void SensorLuz(SensorEvent sensorEvent)
    {
        float valor=sensorEvent.values[0];
        _tvLuz.setText(valor+" lux");
    }
    private void SensorMagnetometro(SensorEvent sensorEvent)
    {
        float valor=sensorEvent.values[0];
        _tvMagnetometro.setText(valor+"uT");
    }
    private void SensorGiroscopio(SensorEvent sensorEvent)
    {
        float x,y,z;
        x=sensorEvent.values[0];
        y=sensorEvent.values[1];
        z=sensorEvent.values[2];

        _tvGiroscopio.setText("");
        _tvGiroscopio.append("Valor x: "+x+ " rad/s\nValor y: "+y+" rad/s\nValor z: "+z+" rad/s");
    }
    private void SensorGravedad(SensorEvent sensorEvent)
    {
        float x,y,z;
        x=sensorEvent.values[0];
        y=sensorEvent.values[1];
        z=sensorEvent.values[2];

        _tvGravedad.setText("");
        _tvGravedad.append("Valor x: "+x+ " m/s^2\nValor y: "+y+" m/s^2\nValor z: "+z+" m/s^2");
    }
    private void SensorRotacion(SensorEvent sensorEvent)
    {
        float x,y,z;
        x=sensorEvent.values[0];
        y=sensorEvent.values[1];
        z=sensorEvent.values[2];

        _tvRotacion.setText("");
        _tvRotacion.append("Valor x: "+x+ "\nValor y: "+y+"\nValor z: "+z);
    }
    private void SensorProximidad(SensorEvent sensorEvent)
    {
        float valor=sensorEvent.values[0];
        _tvProximidad.setText(valor+" cm");


    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mProximidad, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mLuz, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mGiroscopio, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mMagnetometro, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mGravedad, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mRotacion, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
