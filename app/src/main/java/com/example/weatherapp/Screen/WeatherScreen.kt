package com.example.weatherapp.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ViewModel.WeatherUiState
import com.example.weatherapp.ViewModel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val locations by viewModel.location.collectAsState()
    val selectedLocation by viewModel.selectedLocation.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather App With Hilt") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationSelector(
                locations=locations,
                selectedLocation=selectedLocation,
                onLocationSelected = { location ->
                    viewModel.selectedLocationChanged(location)
                }
            )
            Spacer(modifier = Modifier.padding(16.dp))
            when (uiState) {
                is WeatherUiState.Loading -> {
                    Text(text = "Loading...", color = androidx.compose.ui.graphics.Color.Gray)
                }
                is WeatherUiState.Error ->
                {
                    Text(text = "Loading...", color = androidx.compose.ui.graphics.Color.Red)
                }
                is WeatherUiState.Success -> {
                    val weatherInfo = (uiState as WeatherUiState.Success).weatherInfo
                    WeatherInfoDisplay(weatherInfo = weatherInfo)
                }
                }
        }
    }
}