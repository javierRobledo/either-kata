package domain

interface RestaurantRepository {
    fun findById(id: String): Restaurant?
    fun save(restaurant: Restaurant)
    fun findAll(): List<Restaurant>
}