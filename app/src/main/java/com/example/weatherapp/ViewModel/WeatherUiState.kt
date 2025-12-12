package com.example.weatherapp.ViewModel

import com.example.weatherapp.Model.WeatherInfo

sealed  class WeatherUiState {
    object  Loading : WeatherUiState()
    data class Success(val weatherInfo: WeatherInfo): WeatherUiState()
    data class Error (val errorMessage: String): WeatherUiState()
}