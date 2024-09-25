package infra.inmemory

import domain.Restaurant
import domain.RestaurantRepository

class RestaurantRepositoryInMemory(private val restaurants: MutableMap<String, Restaurant>): RestaurantRepository {
    override fun findById(id: String): Restaurant? = restaurants[id]

    override fun save(restaurant: Restaurant) {
        restaurants[restaurant.id] = restaurant
    }
}