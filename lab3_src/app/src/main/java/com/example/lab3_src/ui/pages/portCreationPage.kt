package com.example.lab3_src.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lab3_src.data.NavigationConstants
import com.example.lab3_src.data.Port
import com.example.lab3_src.data.PortController

@Composable
fun PortCreationScreen(navController: NavController, portController: PortController, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var docksAmount by remember { mutableStateOf("") }
    var vehiclePrice by remember { mutableStateOf("") }
    var shipServiceTime by remember { mutableStateOf("") }
    var shipServicePrice by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        Button(
            onClick = {
                navController.navigate(NavigationConstants.portsView)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(32, 191, 249, 255)
            ),
            modifier = Modifier
                .padding(bottom = 16.dp),
        ) {
            Text("main page", fontSize = 25.sp)
        }

        TextField(
            value = name,
            onValueChange = { newName -> name = newName },
            label = { Text("Enter port name", fontSize = 25.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        TextField(
            value = address,
            onValueChange = { newAddress -> address = newAddress },
            label = { Text("Enter port address", fontSize = 25.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        TextField(
            value = docksAmount,
            onValueChange = { newDocksAmount -> docksAmount = newDocksAmount },
            label = { Text("Enter port docks amount", fontSize = 25.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        TextField(
            value = vehiclePrice,
            onValueChange = { newVehiclePrice -> vehiclePrice = newVehiclePrice },
            label = { Text("Enter vehicle price", fontSize = 25.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        TextField(
            value = shipServiceTime,
            onValueChange = { newShipServiceTime -> shipServiceTime = newShipServiceTime },
            label = { Text("Enter ship service time", fontSize = 25.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        TextField(
            value = shipServicePrice,
            onValueChange = { newShipServicePrice -> shipServicePrice = newShipServicePrice },
            label = { Text("Enter ship service price", fontSize = 25.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        Button(
            onClick = {
                val docksParsed = docksAmount.toUIntOrNull() ?: 1u
                val vehiclesPriceParsed = vehiclePrice.toUIntOrNull() ?: 0u
                val shipServiceTimeParsed = shipServiceTime.toUIntOrNull() ?: 0u
                val shipServicePriceParsed = shipServicePrice.toUIntOrNull() ?: 0u

                val port = Port(name,address,docksParsed,vehiclesPriceParsed,
                    shipServiceTimeParsed,shipServicePriceParsed)
                portController.addPort(port)

                navController.navigate(NavigationConstants.portsView)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(32, 191, 249, 255)
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Create", fontSize = 25.sp)
        }
    }
}
