import kotlin.math.roundToInt

const val TAVERN_NAME = "Jojo's Drinks and Dregs"
const val DRAGON_COIN_TO_GOLD_CONVERSION = 1.43

//var playerGold = 10
//var playerSilver = 10
// 1.43 gold per dragon coin
var playerDragonCoin = 5.0

var caskGallonsTotal: Double = 5.0
const val PINT = 0.125

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
    placeOrder("shandy,Dragon's Breath,5.91")
    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("shandy,DRAGON'S BREATH,5.91")
//    placeOrder("exlixir,Shirley's Temple,4.12")


}

fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

//    val data = menuData.split(',')
//    val type = data[0]
//    val name = data[1]
//    val price = data[2]

    val (type, name, price) = menuData.split(',')

    val purchaseSuccessful = performPurchase(price.toDouble())

    if (purchaseSuccessful) {
        val message = "Madrigal buys a $name ($type) for $price."
        println(message)

        if (name.equals("Dragon's Breath", ignoreCase = true)) {
            pourPint()
        }

        val phrase = if (name.equals("Dragon's Breath", true)) {
            "Madigral exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
        } else {
            "Madrigal says: Thanks for the $name."
        }
        println(phrase)
    } else {
        println("$tavernMaster says: You too poor son!")
    }
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
    val totalPurse = playerDragonCoin * DRAGON_COIN_TO_GOLD_CONVERSION
    println("Total purse: $totalPurse")
    if (totalPurse - price < 0) {
        return false
    }
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

//    playerSilver = (remainingBalance % 1 * 100.0).roundToInt()
//    playerGold = remainingBalance.toInt()
    playerDragonCoin = (remainingBalance / DRAGON_COIN_TO_GOLD_CONVERSION)
    displayBalance()
    return true
}

private fun displayBalance() {
//    println("Player's purse balance: Gold $playerGold, Silver: $playerSilver")
    println("Player's purse balance: Dragon Coin $playerDragonCoin")
}

fun pourPint() {
    caskGallonsTotal -= PINT
    println("Cask is down to $caskGallonsTotal")

    println("${(caskGallonsTotal / PINT).toInt()} pints left")
}