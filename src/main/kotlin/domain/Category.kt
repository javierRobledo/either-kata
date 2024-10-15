package domain

import arrow.core.Either
import arrow.core.raise.either

enum class Category {
    STARTERS, MAIN, DESSERTS, DRINKS;

    companion object {
        fun from(value: String): Either<String, Category> = either {
            when (value) {
                "starters" -> STARTERS
                "main" -> MAIN
                "desserts" -> DESSERTS
                "drinks" -> DRINKS
                else -> raise("Unknown category $value")
            }
        }
    }
}