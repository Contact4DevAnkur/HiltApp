package com.example.weatherapp.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationSelector(
    locations: List<String>,
    selectedLocation: String,
    onLocationSelected: (String) -> Unit
) {
    Column {
        Text(text = "Select a location", style = MaterialTheme.typography.headlineLarge , modifier = Modifier.padding(bottom = 8.dp))
        locations.forEach {

            locations->
            Row (modifier = Modifier.fillMaxWidth()
                .selectable(selected = (locations==selectedLocation),
                onClick = {onLocationSelected(locations)})
                .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
              Text(
                  text = locations,
                  style = MaterialTheme.typography.bodyLarge,
                  modifier = Modifier.padding(start = 16.dp)
              )
                RadioButton(
                    selected = (locations==selectedLocation),
                    onClick = {onLocationSelected(locations)}
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = locations)
            }
        }
    }
}

