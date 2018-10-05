fun main(args: Array<String>) {
//    println({
//        val currentYear = 2018
//        "Welcome to SimVillage, Mayor! (copyright $currentYear)"
//    }())

//    val greetingFunction: (String) -> String = { playerName ->
//    val greetingFunction: (String) -> String = {
//        val currentYear = 2018
//        "Welcome to SimVillage, $it! (copyright $currentYear)"
//    }

//    val greetingFunction: (String, Int) -> String = { playerName, numBuildings ->
//    val greetingFunction = { playerName: String, numBuildings: Int ->
//        println("Adding $numBuildings houses.")
//        val currentYear = 2018
//        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
//    }
//    println(greetingFunction("Terri", 10))

    val greetingFunction = { playerName: String, numBuildings: Int ->
        println("Adding $numBuildings houses.")
        val currentYear = 2018
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }

//    runSimulation("Guyal", greetingFunction)

    // pass function reference
//    runSimulation("Guyal", ::printConstruction) { playerName, numBuildings ->
//        println("Adding $numBuildings houses.")
//        val currentYear = 2018
//        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
//    }

    runSimulation()
}

//inline fun runSimulation(playerName: String,
//                         costPrinter: (Int) -> Unit,
//                         greetingFunction: (String, Int) -> String){
//    var numBuildings = (1..10).shuffled().last()
//    costPrinter(numBuildings)
//    println(greetingFunction(playerName, numBuildings))
//}

fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Guyal"))
    // num buildings increases b/c the anonymous function has access to variables defined outside of it!
    println(greetingFunction("Guyal"))
}

fun printConstruction(numBuildings: Int) {
    val cost = 500
    println("Construction cost ${cost * numBuildings}")
}

fun configureGreetingFunction(): (String) -> String {
    val structureType = "hospitals"
    var numBuildings = (1..10).shuffled().last()
    return { playerName: String ->
        numBuildings+=1
        println("Adding $numBuildings $structureType.")
        val currentYear = 2018
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}