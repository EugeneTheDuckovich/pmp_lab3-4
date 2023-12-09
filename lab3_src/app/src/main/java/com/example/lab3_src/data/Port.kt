package com.example.lab3_src.data

import kotlin.math.min

class Port(
    name: String,
    address: String,
    docksAmount: UInt,
    vehiclePrice: UInt,
    shipServiceTime: UInt,
    shipServicePrice: UInt
) : Comparable<Port> {
    public var name: String = name
        private set

    public var address: String = address
        private set

    public var workersAmount: UInt = 0u
        private set

    public var vehiclesAmount: UInt = 0u
        private set

    public var vehiclePrice: UInt = vehiclePrice
        private set

    public var docksAmount: UInt = docksAmount
        private set

    public var shipServiceTime: UInt = shipServiceTime
        private set

    public var shipServicePrice: UInt = shipServicePrice
        private set

    public val functioningDocksAmount
        get() = min(min(docksAmount, workersAmount / 15u),vehiclesAmount / 5u)

    init {
        this.vehiclesAmount = docksAmount * 5u
        this.workersAmount = docksAmount * 15u
    }

    public constructor(port: Port):
        this(port.name,port.address,port.vehiclePrice,port.docksAmount,
            port.shipServiceTime,port.shipServicePrice)
    {}

    public fun getIncomeAfterService(shipsAmount: UInt) : Int {
        return (shipServicePrice.toInt() - vehiclePrice.toInt() * 5) * shipsAmount.toInt();
    }

    public fun hireWorker() {
        workersAmount++;
    }

    public fun fireWorker() {
        workersAmount--;
    }

    operator fun inc(): Port {
        docksAmount++;
        vehiclesAmount+=5u;
        return this;
    }

    override fun equals(other: Any?): Boolean {
        return when{
            this === other -> true
            other !is Port -> false
            else -> this.functioningDocksAmount == other.functioningDocksAmount
        }
    }

    // Override hashCode for consistency with equals
    override fun hashCode(): Int {
        return docksAmount.hashCode()
    }

    override fun compareTo(other: Port): Int {
        return this.functioningDocksAmount.compareTo(other.functioningDocksAmount);
    }
}

fun Port.getServiceTime(shipsAmount: UInt): UInt {
    var shipsAmountCopy = shipsAmount
    var time = 0u

    while(shipsAmountCopy >= docksAmount){
        shipsAmountCopy -= docksAmount
        time += shipServiceTime
    }

    if(shipsAmountCopy > 0u){
        time += shipServiceTime
    }

    return time
}