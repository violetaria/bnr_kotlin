class Barrel<in T>(item: T)


fun main(args: Array<String>) {
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("a shiny new fedora", 10))

    var lootBarrel: Barrel<Loot> = Barrel(Coin(100))

    fedoraBarrel = lootBarrel
}