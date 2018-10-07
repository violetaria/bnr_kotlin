const val TAVERN_NAME = "Jojo's Drinks and Dregs"

fun main(args: Array<String>) {
//    placeOrder("shandy,Dragon's Breath,5.91")
    placeOrder("shandy,DRAGON'S BREATH,5.91")
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

    val(type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)

    val phrase = if (name.equals("Dragon's Breath", true)) {
        "Madigral exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "Madrigal says: Thanks for the $name."
    }
    println(phrase)
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