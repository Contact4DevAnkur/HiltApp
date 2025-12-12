package com.example.weatherapp.Repo

import com.example.weatherapp.Model.WeatherInfo
import jakarta.inject.Inject

class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {

    val weatherData = mapOf(
        "New York" to WeatherInfo("New York",22,"Partly Cloude"),
        "Japan" to WeatherInfo("Japan",23,"Partly Cloude"),
        "Tokyo" to WeatherInfo("Tokyo",24,"Partly Cloude"),
        "Berlin" to WeatherInfo("Berlin",30,"Partly Cloude"),
        "London" to WeatherInfo("London",40,"Partly Cloude"),
        "Paris" to WeatherInfo("Paris",35,"Partly Cloude")
    )


    override fun getWeatherInfo(location: String): WeatherInfo {
        return weatherData[location]?: WeatherInfo(location,0,"Unknown")
    }

    override fun getWeatherForLocation(location: String): WeatherInfo {
        return weatherData[location]?: WeatherInfo(location,0,"Unknown")
    }

    override fun getAllLocation(): List<String> {
        return  weatherData.keys.toList()
    }


}