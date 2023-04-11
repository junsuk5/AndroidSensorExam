package com.surivalcoding.sensorexam.presentation

import androidx.lifecycle.ViewModel
import com.surivalcoding.sensorexam.domain.AndroidSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sensor: AndroidSensor
): ViewModel() {
    private var _state = MutableStateFlow<List<Float>>(emptyList())
    val state = _state.asStateFlow()

    init {
        sensor.startListening()
        sensor.setOnSensorValueChangeListener { values ->
            _state.update {
                values
            }
        }
    }

    override fun onCleared() {
        sensor.stopListening()
        super.onCleared()
    }
}