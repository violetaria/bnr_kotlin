package com.getlosthere.nyethack

fun main(args: Array<String>) {
    val player = Player()

    // com.getlosthere.nyethack.Player Status
    printPlayerStatus(player)

    // player.castFireball(5)
    player.castFireball()
}

private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()})" +
            "\nBlessed: ${if (player.isBlessed) "YES" else "NO"}")

    println("${player.name} ${player.formatHealthStatus()}")
}

