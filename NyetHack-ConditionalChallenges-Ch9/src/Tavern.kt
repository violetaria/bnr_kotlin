import java.io.File
import kotlin.math.roundToInt

const val TAVERN_NAME = "Jojo's Drinks and Dregs"
const val DRAGON_COIN_TO_GOLD_CONVERSION = 1.43

var playerGold = 10
var playerSilver = 10
// 1.43 gold per dragon coin
//var playerDragonCoin = 5.0

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastNames = listOf("Ironfoot", "Fernsworth", "Baggins", "Toodledoot")
val menuList = File("data/tavern-menu-items.txt")
        .readText()
        .split("\n")

val uniquePatrons = mutableSetOf<String>()

val patronGold = mapOf("Eli" to 10.5, "Mordoc" to 8.0, "Sophie" to 5.5)

var caskGallonsTotal: Double = 5.0
const val PINT = 0.125

fun main(args: Array<String>) {
//    if (patronList.contains("Eli")) {
//        println("The tavern master says: Eli's in the back playing cards.")
//    } else {
//        println("The tavern master says: Eli isn't here")
//    }
//
//    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
//        println("The tavern master says: Yea, they're seated by the stew kettle.")
//    } else {
//        println("The tavern master says: Nay, they departed hours ago.")
//    }

//    menuList.forEachIndexed { index, data ->
//        println("$index: $data")
//    }

//    for (patron in patronList) {
//        println("Good evening, $patron")
//    }
//    patronList.forEach { println("Hello $it") }
//    patronList.forEach { patron ->
//        println("Good evening, $patron")
//    }

    printMenu()

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastNames.shuffled().first()
        val name = "$first $last"
        uniquePatrons.add(name)
    }
//    uniquePatrons.forEachIndexed { index, patron ->
//        println("Good evening, $patron - you're #${index + 1} in line.")
//        placeOrder(patron, menuList.shuffled().first())
//    }

    println(patronGold)

    var orders = 0
    while(orders <= 9) {
        placeOrder(uniquePatrons.shuffled().first(),
                menuList.shuffled().first())
        orders++
    }

//    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("shandy,DRAGON'S BREATH,5.91")
//    placeOrder("exlixir,Shirley's Temple,4.12")

}

fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')


//    performPurchase(price.toDouble())
    val message = "$patronName buys a $name ($type) for $price."
    println(message)
    val phrase = if (name.equals("Dragon's Breath", true)) {
            "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patronName says: Thanks for the $name."
    }
    println(phrase)

//    val purchaseSuccessful = performPurchase(price.toDouble())

//    if (purchaseSuccessful) {
//        val message = "Madrigal buys a $name ($type) for $price."
//        println(message)
//
//        if (name.equals("Dragon's Breath", ignoreCase = true)) {
//            pourPint()
//        }
//
//        val phrase = if (name.equals("Dragon's Breath", true)) {
//            "Madigral exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
//        } else {
//            "Madrigal says: Thanks for the $name."
//        }
//        println(phrase)
//    } else {
//        println("$tavernMaster says: You too poor son!")
//    }
}

fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]", RegexOption.IGNORE_CASE)) {
        when(it.value.toLowerCase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

fun performPurchase(price: Double): Boolean {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
//    val totalPurse = playerDragonCoin * DRAGON_COIN_TO_GOLD_CONVERSION
    println("Total purse: $totalPurse")
    if (totalPurse - price < 0) {
        return false
    }
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    playerSilver = (remainingBalance % 1 * 100.0).roundToInt()
    playerGold = remainingBalance.toInt()
//    playerDragonCoin = (remainingBalance / DRAGON_COIN_TO_GOLD_CONVERSION)
    displayBalance()
    return true
}

private fun displayBalance() {
    println("Player's purse balance: Gold $playerGold, Silver: $playerSilver")
//    println("Player's purse balance: Dragon Coin $playerDragonCoin")
}

fun pourPint() {
    caskGallonsTotal -= PINT
    println("Cask is down to $caskGallonsTotal")

    println("${(caskGallonsTotal / PINT).toInt()} pints left")
}

fun printMenu() {
    val welcome = "*** Welcome to $TAVERN_NAME ***"
    println()
    println(welcome)
    println()
    for(item in menuList) {
        val (type, name, price) = item.split(',')
        val dotCount = welcome.length - (name.length + price.length)
        val spaceCount = (welcome.length - type.length) / 2 - 4
        println("${" ".repeat(spaceCount)}-[$type]-${" ".repeat(spaceCount)}")
        println("${name.capitalize()}${".".repeat(dotCount)}$price")
    }
    println()
    println()
}