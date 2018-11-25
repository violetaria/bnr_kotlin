package com.getlosthere.nyethack

// open allows for subclassing
open class Room (val name: String){

    protected open val dangerLevel = 5

    var monster: Monster? = Goblin()

    open fun load() = "Nothing much to see here..."

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel\n" +
            "Creature: ${monster?.description ?: "none."}"
}

fun Room.configurePitGoblin(block: Room.(Goblin) -> Goblin): Room {
    val goblin = block(Goblin("Pit Goblin", description = "An Evil Pit Goblin"))
    monster = goblin
    return this
}