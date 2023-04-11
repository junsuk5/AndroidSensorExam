package com.surivalcoding.sensorexam.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

abstract class AndroidSensor(
    private val context: Context,
    private val sensorFeature: String,
    private val sensorType: Int,
): SensorEventListener {

    private var onSensorValueChanged: ((List<Float>) -> Unit)? = null

    fun setOnSensorValueChangeListener(listener: (List<Float>) -> Unit) {
        onSensorValueChanged = listener
    }

    val hasSensor: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    fun startListening() {
        if (!hasSensor) {
            return
        }

        if (!::sensorManager.isInitialized && sensor == null) {
            sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensor = sensorManager.getDefaultSensor(sensorType)
        }

        sensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopListening() {
        if (!::sensorManager.isInitialized && sensor == null) {
            return
        }

        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (!hasSensor) {
            return
        }

        if (event?.sensor?.type == sensorType) {
            onSensorValueChanged?.invoke(event.values.toList())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}