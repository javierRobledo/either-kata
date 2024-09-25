package infra.bootstrap

import application.AddItemToMenuHandler
import domain.Restaurant
import infra.cli.TerminalCli
import infra.inmemory.RestaurantRepositoryInMemory

fun main(vararg args: String) {
    val repository = RestaurantRepositoryInMemory(mutableMapOf("1" to Restaurant("1", emptyMap())))
    val handler = AddItemToMenuHandler(repository)
    val cli = TerminalCli(handler, repository)
    cli.loop()
}