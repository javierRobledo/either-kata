package infra.bootstrap

import application.AddItemToMenuHandler
import infra.cli.TerminalCli
import infra.inmemory.RestaurantRepositoryInMemory

fun main(vararg args: String) {
    val repository = RestaurantRepositoryInMemory(mutableMapOf())
    val handler = AddItemToMenuHandler(repository)
    val cli = TerminalCli(handler)
    cli.loop()
}