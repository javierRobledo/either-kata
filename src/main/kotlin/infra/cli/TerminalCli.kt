package infra.cli

import application.AddItemToMenuHandler
import application.AddMenuItem
import application.AddMenuItemError
import application.RestaurantNotFound
import application.TooManyItems
import application.ValidationError
import domain.RestaurantRepository

class TerminalCli(private val handler: AddItemToMenuHandler, private val repository: RestaurantRepository) {

    fun loop() {
        println("Current status:")
        repository.findAll().forEach(::println)
        println("----------------")
        println("Choose next operation:")
        println("1. Add item to menu")
        println("2. Exit")
        val option = readlnOrNull()
        when(option) {
            "1" -> {
                addMenuItem()
                println()
                loop()
            }
            "2" -> return
            else -> {
                println()
                loop()
            }
        }
    }

    private fun addMenuItem() {
        println("Enter restaurant Id, category, item name and price separated by commas:")
        val input = readlnOrNull()
        if (input == null) {
            println("Something went wrong!")
            return
        }
        val (restaurantId, category, name, price) = input.split(",")
        val command = AddMenuItem(restaurantId, category, name, price.toDouble())
        handler.handle(command)
            .onLeft {
                when(it) {
                    is ValidationError -> {
                        println("400 - Bad Request")
                        it.errors.forEach { println("Error: ${it.field} - ${it.message}") }
                    }
                    is RestaurantNotFound -> println("404 - Not found - $it")
                    TooManyItems -> println("409 - Conflict - $it")
                }
            }
    }
}