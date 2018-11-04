package com.getlosthere.nyethack

import java.io.File

class Player (_name: String,
              var healthPoints: Int = 100,
              val isBlessed: Boolean,
              private val isImmortal: Boolean) {
    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }

    private val hometown by lazy { selectHometown() }
    var currentPosition = Coordinate(0, 0)

    constructor(name: String) : this(name,
            isImmortal = false,
            isBlessed = true) {
        if (name.toLowerCase() == "kar") {
            healthPoints = 40
        }
    }

    init {
        require(healthPoints > 0) { "healthPoints must be greater than 0."}
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    fun castFireball(numFireballs: Int = 2) =
            println("A glass of Fireball springs into existence. (x$numFireballs)")

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        return if (auraVisible) "GREEN" else "NONE"
    }

    fun formatHealthStatus() =
            when (healthPoints) {
                100 -> "is in excellent condition."
                in 90..99 -> "has a few scratches"
                in 75..89 -> if (isBlessed) {
                    "has minor wounds but is healing quite quickly!"
                } else {
                    "has minor wounds."
                }
                in 15..74 -> "looks pretty hurt!"
                else -> "is in awful condition!"
            }

    private fun selectHometown() = File("data/towns.txt")
                .readText()
                .split("\n")
                .shuffled()
                .first()
}