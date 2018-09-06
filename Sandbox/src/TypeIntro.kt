// compile time constant
const val MAX_EXPERIENCE: Int = 5000

fun main(args: Array<String>) {
    var hasSteed = false;
    val playerName = "Estragon"
    var experiencePoints = 5
    val pubName = "Unicorn's Horn"
    val publicanName = "publican"
    var playerGold = 0
    var drinks = arrayListOf("mead", "wine", "La Croix")

    experiencePoints += 5

    println(experiencePoints)
    println(playerName)

    // pub challenge
    println("\n\n<< $playerName enters the $pubName >>")
    speak(publicanName, "Do you need to stable a steed?")
    if(hasSteed) {
        speak(playerName, "Yes, I have a steed and would like to stable it. I have $playerGold gold pieces, and I would also like a drink.")
    } else {
        speak(playerName, "I have no steed. But I do have $playerGold gold pieces, and I would like a drink.")
    }
    speak(publicanName, "Excellent, I have ${drinks.joinToString(separator = ", ")}")

    // magic mirror
    println("\n\n<< $playerName encounters a magic mirror >>")
    println("${playerName.reversed()}")
}

fun speak(speaker: String, words: String){
    println("[$speaker] $words")
}

