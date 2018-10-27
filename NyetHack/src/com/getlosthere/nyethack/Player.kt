package com.getlosthere.nyethack

class Player {
    var name = "madigral"
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }

    var healthPoints = 89
    val isBlessed = true
    private val isImmortal = true

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
}