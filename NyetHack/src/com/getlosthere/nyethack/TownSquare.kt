package com.getlosthere.nyethack

class TownSquare : Room("Town Square") {

    override val dangerLevel = super.dangerLevel - 3

    private val bellSound = "GWONG"

    final override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"

    fun ringBell() = "The bell tower announces your arrival. $bellSound"
}