package com.getlosthere.nyethack

// open allows for subclassing
open class Room (val name: String){

    protected open val dangerLevel = 5

    open fun load() = "Nothing much to see here..."

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel"
}