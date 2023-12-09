package com.example.lab3_src.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lab3_src.data.NavigationConstants
import com.example.lab3_src.data.Port
import com.example.lab3_src.data.getServiceTime

@Composable
fun SelectedPortViewScreen(navController: NavController, port: Port?, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
    ){

        Button(
            onClick = {
                navController.navigate(NavigationConstants.portsView)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(32, 191, 249, 255)
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Text("main page", fontSize = 25.sp)
        }

        if(port != null){
            var portMutableState by remember{ mutableStateOf(value = port, policy= neverEqualPolicy()) }

            var shipsCount by remember { mutableStateOf("") }
            var shipsServiceTime by remember { mutableStateOf("") }
            var shipsServiceIncome by remember { mutableStateOf("") }

            Column{
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                )

                Text(
                    text = "Port information",
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "Name: ${portMutableState.name}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Address: ${portMutableState.address}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Docks count: ${portMutableState.docksAmount}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Functioning docks count: ${portMutableState.functioningDocksAmount}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Vehicle count: ${portMutableState.vehiclesAmount}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Vehicle price: ${portMutableState.vehiclePrice}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Workers count: ${portMutableState.workersAmount}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Ship service time: ${portMutableState.shipServiceTime}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Ship service price: ${portMutableState.shipServicePrice}",
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                )

                Text(
                    text ="Port functionality",
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                )

                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                )

                Button(
                    onClick = {
                        portMutableState++
                    },
                    modifier = Modifier
                        .padding(bottom = 8.dp, top = 8.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(32, 191, 249, 255)
                    ),
                ) {
                    Text(
                        text = "Increment docks",
                        fontSize = 25.sp
                    )
                }

                Button(
                    onClick = {
                        portMutableState.hireWorker()
                        portMutableState = portMutableState
                    },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(32, 191, 249, 255)
                    ),
                ) {
                    Text(
                        text ="Hire Worker",
                        fontSize = 25.sp
                    )
                }

                Button(
                    onClick = {
                        portMutableState.fireWorker()
                        portMutableState = portMutableState
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(32, 191, 249, 255)
                    ),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text ="Fire Worker",
                        fontSize = 25.sp
                    )
                }

                TextField(
                    value = shipsCount,
                    onValueChange = { newShipsCount -> shipsCount = newShipsCount },
                    label = { Text("Enter ships count", fontSize = 25.sp) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                )

                Button(
                    onClick = {
                        val shipsCountParsed = shipsCount.toUIntOrNull() ?: 0u
                        shipsServiceTime = port.getServiceTime(shipsCountParsed).toString()
                        shipsServiceIncome = port.getIncomeAfterService(shipsCountParsed).toString()

                        port.fireWorker()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(32, 191, 249, 255)
                    ),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Calculate service values",
                        fontSize = 25.sp
                    )
                }

                Text(
                    text = "Time needed for service: $shipsServiceTime",
                    fontSize = 25.sp
                )

                Text(
                    text = "Income after service: $shipsServiceIncome",
                    fontSize = 25.sp
                )
            }
        }
        else{
            Text("You did not select the port, go back to main page",
                fontSize = 25.sp)
        }
    }
}
