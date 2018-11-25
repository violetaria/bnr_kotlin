val String.numVowels
    get() = count { "aeiouy".contains(it)}

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun <T> T.easyPrint() : T {
    println(this)
    return this
}

infix fun String?.printWithDefault(default: String) = print(this ?: default)

fun main(args: Array<String>) {
    "Madrigal has left the building".easyPrint().addEnthusiasm().easyPrint()

    42.easyPrint()

    "Madrigal has left the building".numVowels.easyPrint()

    val nullableString: String? = null
    // with infix
    nullableString printWithDefault "Default String"
    // without infix
    nullableString.printWithDefault("Default String")
}