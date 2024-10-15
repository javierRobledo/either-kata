package application

import arrow.core.Either
import arrow.core.Either.Companion.zipOrAccumulate
import arrow.core.nel
import arrow.core.raise.either
import arrow.core.raise.ensure
import domain.Category
import domain.MenuItem
import domain.Name
import domain.Price
import domain.RestaurantRepository

sealed interface AddMenuItemError
data class InvalidField(val field: String, val message: String)
data class ValidationError(val errors: List<InvalidField>) : AddMenuItemError // -> 400
data object TooManyItems : AddMenuItemError // -> 409
data object RestaurantNotFound : AddMenuItemError // -> 404

data class AddMenuItem(val restaurantId: String, val category: String, val name: String, val price: Double)

class AddItemToMenuHandler(private val repository: RestaurantRepository) {

    fun handle(command: AddMenuItem): Either<AddMenuItemError, Unit> = either {
        val restaurant = repository.findById(command.restaurantId)
        ensure(restaurant != null) { RestaurantNotFound }

        val restaurantWithItem = zipOrAccumulate(
            parseCategory(command.category),
            parseItem(command)
        ) { category, item -> restaurant.addItem(category, item).bind() }
            .mapLeft { it.flatten() }
            .mapLeft { ValidationError(it) }
            .bind()

        repository.save(restaurantWithItem)

    }

    private fun parseCategory(value: String) =
        Category.from(value)
            .mapLeft { InvalidField("category", it).nel() }

    private fun parseItem(command: AddMenuItem): Either<List<InvalidField>, MenuItem> {
        val name = Name.from(command.name)
            .mapLeft { InvalidField("name", it).nel() } // Importante, estos errores se refieren al campo del command, no del modelo
        val price = Price.from(command.price)
            .mapLeft { InvalidField("price", it).nel() } // Importante, estos errores se refieren al campo del command, no del modelo

        return zipOrAccumulate(name, price, ::MenuItem)
    }
}