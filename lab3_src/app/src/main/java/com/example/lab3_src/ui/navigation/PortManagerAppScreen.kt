package com.example.lab3_src.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab3_src.data.NavigationConstants
import com.example.lab3_src.data.Port
import com.example.lab3_src.data.PortController
import com.example.lab3_src.ui.pages.PortCreationScreen
import com.example.lab3_src.ui.pages.PortsViewScreen
import com.example.lab3_src.ui.pages.SelectedPortViewScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortManagerApp(portController: PortController, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    portController.addPort(Port("port1","Kharkiv",5u,5u,10u,20u))
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.Transparent
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Transparent)
                    ) {
                        Text(
                            text = "Port Manager",
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .align(Alignment.Center),
                        )
                        IconButton(
                            onClick = {
                                navController.navigate(NavigationConstants.portCreation)
                            },
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .align(Alignment.CenterEnd),
                        ){
                            Icon(
                                imageVector = Icons.Default.Add, contentDescription = null,
                            )
                        }
                    }
                },
                modifier = modifier.background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(53,125,227,225),
                            Color(21,201,252,255)
                        )
                    ))
            )
        }
    )
    { paddingValues ->
        NavHost(navController = navController, startDestination = NavigationConstants.portsView) {
            composable(route = NavigationConstants.portsView){
                PortsViewScreen(navController, portController, modifier.padding(paddingValues))
            }
            composable(route = NavigationConstants.portCreation){
                PortCreationScreen(navController, portController, modifier.padding(paddingValues))
            }
            composable(route = NavigationConstants.selectedPort){
                SelectedPortViewScreen(navController, portController.selectedPort, modifier.padding(paddingValues))
            }
        }
    }
}
