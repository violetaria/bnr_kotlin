fun main(args: Array<String>) {
    val name = "Madigral"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = true

    // Aura
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    // Player Status
    printPlayerStatus(auraColor = auraColor,
                      isBlessed = isBlessed,
                      healthStatus = healthStatus,
                      name = name)

//    castFireball(5)
    castFireball()
}

private fun printPlayerStatus(auraColor: String, isBlessed: Boolean, name: String, healthStatus: String) {
    println("(Aura: $auraColor)" +
            "\nBlessed: ${if (isBlessed) "YES" else "NO"}")

    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
   if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
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


private fun castFireball(numFireballs: Int = 2) =
    println("A glass of Fireball springs into existence. (x$numFireballs)")