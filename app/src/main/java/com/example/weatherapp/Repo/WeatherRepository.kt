package com.example.weatherapp.Repo

import com.example.weatherapp.Model.WeatherInfo

interface WeatherRepository {

    fun getWeatherInfo(location: String): WeatherInfo
    fun getWeatherForLocation(location: String): WeatherInfo
    fun  getAllLocation(): List<String>

}