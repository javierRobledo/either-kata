package application

import domain.Category
import domain.MenuItem
import domain.Name
import domain.Price
import domain.RestaurantRepository

data class AddMenuItem(val restaurantId: String, val category: String, val name: String, val price: Double)

class AddItemToMenuHandler(private val repository: RestaurantRepository) {

    fun handle(command: AddMenuItem) {
        val restaurant = repository.findById(command.restaurantId) ?: throw RestaurantNotFoundException("Restaurant not found")
        val category = Category.fromString(command.category)
        val price = Price(command.price)
        val name = Name(command.name)

        val modifiedRestaurant = restaurant.addItem(category, MenuItem(name, price))
        repository.save(modifiedRestaurant)
    }
}