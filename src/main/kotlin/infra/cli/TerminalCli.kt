package infra.cli

import application.AddItemToMenuHandler
import application.AddMenuItem
import application.RestaurantNotFoundException
import domain.InvalidFieldException
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
        try {
            println("Enter restaurant Id, category, item name and price separated by commas:")
            val input = readlnOrNull()
            if (input == null) {
                println("Something went wrong!")
                return
            }
            val (restaurantId, category, name, price) = input.split(",")
            val command = AddMenuItem(restaurantId, category, name, price.toDouble())
            handler.handle(command)
        } catch(e: InvalidFieldException) {
            println("400 - Bad Request: ${e.message}")
        } catch (e: IllegalStateException) {
            println("409 - Conflict - ${e.message}")
        } catch(e: RestaurantNotFoundException) {
            println("404 - Not Found - ${e.message}")
        }
    }
}