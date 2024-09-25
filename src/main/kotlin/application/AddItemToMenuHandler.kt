package application

import domain.RestaurantRepository

data class AddMenuItem(val restaurantId: String, val category: String, val name: String, val price: Double)

class AddItemToMenuHandler(private val repository: RestaurantRepository) {

    fun handle(command: AddMenuItem) {
        TODO()
    }
}