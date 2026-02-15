package com.example.mobile_computing_hw.utils


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.mobile_computing_hw.utils.NotificationHelper.sendNotification
import kotlin.math.abs

class GyroscopeManager(
    private val context: Context,
) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val gyroscope: Sensor? =
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {

            val x = it.values[0]
            val y = it.values[1]
            val z = it.values[2]

            if (abs(x) > 2f || abs(y) > 2f || abs(z) > 2f) {
//                Log.d("GYRO_TEST", "x=$x, y=$y, z=$z")
                sendNotification(context , "Gyro" , "x=$x, y=$y, z=$z")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    fun start() {
        gyroscope?.also {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }
}