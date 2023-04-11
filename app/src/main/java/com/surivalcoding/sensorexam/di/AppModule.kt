package com.surivalcoding.sensorexam.di

import android.content.Context
import com.surivalcoding.sensorexam.data.AccelerometerSensor
import com.surivalcoding.sensorexam.domain.AndroidSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSensor(@ApplicationContext context: Context): AndroidSensor {
        return AccelerometerSensor(context)
    }
}