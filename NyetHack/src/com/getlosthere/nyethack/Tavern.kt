package com.getlosthere.nyethack

import com.getlosthere.nyethack.extensions.random
import java.io.File

const val TAVERN_NAME = "Jojo's Drinks and Dregs"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastNames = listOf("Ironfoot", "Fernsworth", "Baggins", "Toodledoot")
val menuList = File("data/tavern-menu-items.txt")
        .readText()
        .split("\n")

val uniquePatrons = mutableSetOf<String>()
val patronGold = mutableMapOf<String, Double>()

fun main(args: Array<String>) {
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }

    (0..9).forEach {
        val first = patronList.random()
        val last = lastNames.random()
        val name = "$first $last"
        uniquePatrons.add(name)
    }

    uniquePatrons.forEach { patron ->
        patronGold[patron] = 6.0
    }

    var orders = 0
    while(orders <= 9) {
        placeOrder(uniquePatrons.random(),
                menuList.random())
        orders++
    }

    displayPatronBalances()
}

fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')

    val purchaseSuccessful = performPurchase(price.toDouble(), patronName)
    if (purchaseSuccessful) {
        val message = "$patronName buys a $name ($type) for $price."
        println(message)
        val phrase = if (name.equals("Dragon's Breath", true)) {
            "$patronName exclaims: ${"Ah, delicious $name!".toDragonSpeak()}"
        } else {
            "$patronName says: Thanks for the $name."
        }
        println(phrase)
    } else {
        println("We only accept coin for drinks, GET OUT!")
        uniquePatrons.remove(patronName)
    }
}

private fun String.toDragonSpeak() =
    this.replace(Regex("[aeiou]", RegexOption.IGNORE_CASE)) {
        when(it.value.toLowerCase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

fun performPurchase(price: Double, patronName: String): Boolean {
    val totalPurse = patronGold.getValue(patronName)
    if (totalPurse - price < 0) {
        return false
    }
    println("Purchasing item for $price")

    patronGold[patronName] = totalPurse - price
    return true
}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance -> println("$patron, balance: ${"%.2f".format(balance)}") }
}
