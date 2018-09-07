fun main(args: Array<String>) {
    val name = "Madigral"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = true

    // Aura
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor = if(auraVisible) "GREEN" else "NONE"

    val healthStatus = when(healthPoints) {
        100         ->  "is in excellent condition."
        in 90..99   -> "has a few scratches"
        in 75..89   -> if(isBlessed) {
            "has minor wounds but is healing quite quickly!"
        } else {
            "has minor wounds."
        }
        in 15..74   ->  "looks pretty hurt!"
        else        ->  "is in awful condition!"
    }

    // Player Status
    println("(Aura: $auraColor)" +
            "\nBlessed: ${if(isBlessed) "YES" else "NO"}")

    println("$name $healthStatus")
}