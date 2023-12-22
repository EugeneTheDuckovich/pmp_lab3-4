package com.example.lab3_src.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lab3_src.data.NavigationConstants
import com.example.lab3_src.data.PortController

@Composable
fun PortsViewScreen(navController: NavController, portController: PortController, modifier: Modifier = Modifier) {
    var portListMutableState by remember{ portController.ports }

    var firstPortName by remember { mutableStateOf("") }
    var secondPortName by remember { mutableStateOf("") }
    var comparisonResult by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ){
        LazyColumn{
            items(items = portListMutableState, itemContent = {port ->
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                        .graphicsLayer {
                            clip = true
                            shape = RoundedCornerShape(5.dp)
                        }
                        .fillMaxWidth()
                        .background(Color(32, 191, 249, 255)),
                ) {
                    ClickableText(
                        onClick = {
                            portController.selectedPort = port
                            navController.navigate(NavigationConstants.selectedPort)
                        },
                        text = buildAnnotatedString{
                            withStyle(style = SpanStyle(fontSize = 25.sp,
                                textDecoration = TextDecoration.Underline, color = Color.White)
                            ){
                                append(port.name)
                            }
                        },
                        modifier = Modifier.align(Alignment.Center),
                    )

                    IconButton(
                        onClick = {
                            portController.removePort(port)
                            portListMutableState = portController.ports.value
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .background(Color.Red),
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            })
        }

        TextField(
            value = firstPortName,
            onValueChange = { newName -> firstPortName = newName },
            label = {
                Text(
                    text = "Enter first port name",
                    fontSize = 25.sp
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        TextField(
            value = secondPortName,
            onValueChange = { newName -> secondPortName = newName },
            label = {
                Text(
                    text = "Enter second port name",
                    fontSize = 25.sp
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 25.sp),
        )

        Button(
            onClick = {
                val firstPort = portController.findByName(firstPortName)
                val secondPort = portController.findByName(secondPortName)

                if(firstPort == null || secondPort == null){
                    comparisonResult = "invalid input"
                }
                else if(firstPort == secondPort){
                    comparisonResult = "ports are equal by functioning docks count"
                }
                else if(firstPort > secondPort){
                    comparisonResult = "port $firstPortName has more functioning docks than $secondPortName"
                }
                else if(firstPort < secondPort){
                    comparisonResult = "port $secondPortName has more functioning docks than $firstPortName"
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(32, 191, 249, 255)
            ),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = "Compare ports",
                fontSize = 25.sp,
            )
        }

        Text(
            text ="comparison result: $comparisonResult",
            fontSize = 25.sp,
        )
    }

}
