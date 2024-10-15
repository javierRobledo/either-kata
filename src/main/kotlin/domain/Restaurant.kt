package domain

import application.TooManyItems
import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

data class Restaurant(val id: String, val items: Map<Category, Set<MenuItem>>) {
    fun addItem(category: Category, item: MenuItem): Either<TooManyItems, Restaurant> = either {
        val itemsInCategory = items[category] ?: emptySet()
        ensure(itemsInCategory.size < 10) { TooManyItems }

        Restaurant(id, items + (category to (itemsInCategory + item)))
    }
}