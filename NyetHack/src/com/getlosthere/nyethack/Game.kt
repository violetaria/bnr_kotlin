package com.getlosthere.nyethack

fun main(args: Array<String>) {
//    val player = Player("madigral",
//            89,
//            true,
//            false)

    val player = Player("madigral", healthPoints = 50, isBlessed = true, isImmortal = false)

    // player Status
    printPlayerStatus(player)

    // player.castFireball(5)
    player.castFireball()
}

private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()})" +
            "\nBlessed: ${if (player.isBlessed) "YES" else "NO"}")

    println("${player.name} ${player.formatHealthStatus()}")
}

