package com.getlosthere.nyethack

import java.util.*

interface Fightable {
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int
    val damageRole: Int
        get() = (0 until diceCount).map {
            Random().nextInt(diceSides + 1)
        }.sum()

    fun attack(opponent: Fightable): Int
}

abstract class Monster(val name: String,
                       val description: String,
                       override var healthPoints: Int) : Fightable {

    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRole
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}