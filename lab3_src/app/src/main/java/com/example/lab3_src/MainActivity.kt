package com.example.lab3_src

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab3_src.data.PortController
import com.example.lab3_src.ui.navigation.PortManagerApp

class MainActivity : ComponentActivity() {
    private val portController: PortController = PortController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            PortManagerApp(portController = portController)
        }
    }
}