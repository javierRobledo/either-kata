package infra.cli

import application.AddItemToMenuHandler
import application.AddMenuItem
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
        } catch(e: Exception) {
            /*
            Gestionar errores e informar al cliente. Incluir el código http que habríamos devuelto si esto fuese un controller.
                - En caso de problema por validación, devolver código 400 e imprimir por pantalla campos que no son válidos
                - En caso de problema por demasiados items, devolver código 409
                - En caso de que no exista el restaurante, devolver código 404""".trimMargin())
             */
            println("Something went wrong!")

        }
    }
}