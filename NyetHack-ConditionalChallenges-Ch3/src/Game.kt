fun main(args: Array<String>) {
    val name = "Madigral"
    var healthPoints = 45
    val isBlessed = true
    val isImmortal = true
    val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()

    // Aura
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor = when(karma) {
        in 0..5 -> "RED"
        in 6..10 -> "ORANGE"
        in 11..15 -> "PURPLE"
        in 16..20 -> "GREEN"
        else -> "NONE"
    }

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    val statusFormatString = "(HP)(A) -> H"

    // Player Status
    println(playerStatus(if(auraVisible) auraColor else "GREEN", isBlessed, healthPoints, "$name $healthStatus"))
}

fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean): String {
    return when (healthPoints) {
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

fun playerStatus(A: String, B: Boolean, HP: Int, H: String): String{
    return "(HP: $HP)(A: $A) ${if(B) "YES" else "NO"} -> $H"
}