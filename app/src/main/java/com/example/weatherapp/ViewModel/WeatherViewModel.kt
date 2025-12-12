package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel(){

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _location = MutableStateFlow<List<String>>(emptyList())
    val location: StateFlow<List<String>> = _location.asStateFlow()

    private val _selectLocation= MutableStateFlow<String>("")
    val selectedLocation: StateFlow<String> =_selectLocation.asStateFlow()


    init {
        loadLocations()
    }

    private fun loadLocations() {
     viewModelScope.launch {
             val locationList =weatherRepository.getAllLocation()
         _location.value=locationList
         if (locationList.isNotEmpty())
         {
             _selectLocation.value= (locationList[0])
             loadWeatherForLocation(locationList[0])
         }
     }
    }

    private fun loadWeatherForLocation(location: String)
    {
        viewModelScope.launch {
           _uiState.value = WeatherUiState.Loading

            val weatherInfo = weatherRepository.getWeatherForLocation( location)
            _uiState.value = WeatherUiState.Success(weatherInfo)

        }
    }

      fun selectedLocationChanged(location: String)
    {
        _selectLocation.value=location
        loadWeatherForLocation(location)
    }
}