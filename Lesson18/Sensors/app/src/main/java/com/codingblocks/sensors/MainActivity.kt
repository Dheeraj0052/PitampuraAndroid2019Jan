package com.codingblocks.sensors

import android.accounts.AccountManager
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.Sensor.*
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val listOfSensors = sm.getSensorList(Sensor.TYPE_ALL)

//        listOfSensors.forEach {
//            Log.e("TAG", "--------------")
//            Log.e("TAG", "Name : " + it.name)
//            Log.e("TAG", "Type : " + it.stringType)
//            Log.e("TAG", "Vendor : " + it.vendor)
//            Log.e("TAG", "Max Event Count : " + it.fifoMaxEventCount.toString())
//            Log.e("TAG", "Sensor ID : " + it.id.toString())
//            Log.e("TAG", "Dynamic sensor : " + it.isDynamicSensor)
//            Log.e("TAG", "Is Wakeup : " + it.isWakeUpSensor)
//            Log.e("TAG", "Max Delay" + it.maxDelay)
//            Log.e("TAG", "Min Delay : " + it.minDelay)
//            Log.e("TAG", "Max Range : " + it.maximumRange)
//            Log.e("TAG", "Power consumption : " + it.power)
//            Log.e("TAG", "Version : " + it.version)
//            Log.e("TAG", "--------------")
    }

    override fun onStart() {
        super.onStart()
        val sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelSensor = sm.getDefaultSensor(TYPE_GRAVITY)

        val proximitySensor = sm.getDefaultSensor(TYPE_PROXIMITY)
        sm.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_UI)
        sm.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_UI)
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //This is called whenever the accuracy of the sensor in use changes.
        //This can happen when the user undergoes a transition from high-low battery and vice versa
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when {
                it.sensor.type == Sensor.TYPE_GRAVITY -> {
                    Log.e("TAG", "acceleration in x : " + it.values[0])
                    Log.e("TAG", "acceleration in y : " + it.values[1])
                    Log.e("TAG", "acceleration in z : " + it.values[2])

                    val red = 0
                    val green = 0
                    val blue =  0

                    val color = Color.rgb(red,green,blue)

                    parentLayout.setBackgroundColor(color)
                }
                it.sensor.type == Sensor.TYPE_PROXIMITY -> {
                    Log.e("TAG", "Distance from the sensor : " + it.values[0])
                }
                else -> Toast.makeText(this, "Unsupported sensor", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sm.unregisterListener(this)
    }

}
