package com.getlosthere.nyethack

import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    Game.play()
}

object Game {

    private val player = Player("madrigal")
    private var currentRoom : Room = TownSquare()
    private var isGameInProgress = true

    private var worldMap = listOf(
            listOf(currentRoom, Room("Tavern"), Room("Back Room")),
            listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer!")
        // player.castFireball(5)
        player.castFireball()
    }

    fun play() {
        isGameInProgress = true

        while(isGameInProgress) {
            println("======= NYET HACK =======")
            println(currentRoom.description())
            println(currentRoom.load())

            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
            println("======= ~~~~~~~~~ =======\n")
        }

        println("Thanks for playing. Goodbye.")
    }

    private fun move(directionInput: String) =
            try {
                val direction = Direction.valueOf(directionInput.toUpperCase())
                val newPosition = direction.updateCoordinate(player.currentPosition)
                if (!newPosition.isInBounds) {
                    throw IllegalStateException("$direction is out of bounds.")
                }

                val newRoom = worldMap[newPosition.y][newPosition.x]
                player.currentPosition = newPosition
                currentRoom = newRoom
                "OK, so you move $direction to the ${currentRoom.name}.\n${currentRoom.load()}"
            } catch (e: Exception) {
                "Invalid direction: $directionInput"
            }

    fun ringBell() = if(currentRoom is TownSquare) {
        (currentRoom as TownSquare).ringBell()
    } else {
        "There is no bell."
    }



    private fun endGame() :String {
        isGameInProgress = false
        return "Game over"
    }

    private fun map() = try {
        for (x in worldMap.indices) {
            for (y in worldMap[x].indices) {
                if ( Coordinate(x, y) == player.currentPosition) {
                    print(" X ")
                } else {
                    print(" O ")
                }
            }
            println()
        }
        "\n"
    } catch (e: Exception) {
        "map issue!"
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()})" +
                "\nBlessed: ${if (player.isBlessed) "YES" else "NO"}")

        println("${player.name} ${player.formatHealthStatus()}")
    }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            Thread.sleep(1000)
            slay(it)
        }

        "Combat complete."
    } ?: "There's nothing to here to fight."

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if (player.healthPoints <= 0) {
            println(">>>>> you have been defeated! Thanks for playing <<<<<")
            exitProcess(0)
        }
        if (monster.healthPoints <= 0) {
            println(">>>>> ${monster.name} has been defeated! <<<<<")
            currentRoom.monster = null
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"

        fun processCommand() = when(command.toLowerCase()) {
            "move" -> move(argument)
            "map" -> map()
            "ring" -> ringBell()
            "fight" -> fight()
            "quit" -> endGame()
            else -> commandNotFound()
        }
    }
}
