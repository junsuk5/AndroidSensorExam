package com.surivalcoding.sensorexam.data

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import com.surivalcoding.sensorexam.domain.AndroidSensor

class AccelerometerSensor(
    context: Context,
) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorType = Sensor.TYPE_ACCELEROMETER,
)


class LightSensor(
    context: Context,
) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_LIGHT,
    sensorType = Sensor.TYPE_LIGHT,
)