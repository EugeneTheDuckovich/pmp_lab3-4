package com.example.lab3_src.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class PortController {
    private val _ports: MutableList<Port> = mutableStateListOf()

    public val ports: MutableState<List<Port>>
        get() = mutableStateOf(_ports.toList())

    public var selectedPort: Port? = null

    fun addPort(port: Port) {
        _ports.add(port)
    }

    fun removePort(port: Port) {
        _ports.remove(port)
    }

    fun clear() {
        _ports.clear()
    }

    fun findByName(name: String): Port? {
        return _ports.find { p -> p.name == name }
    }
}