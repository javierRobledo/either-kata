package domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

data class Price(val value: Double) {
    companion object {
        fun from(value: Double): Either<String, Price> = either {
            ensure(value > 0) { "Price must be greater than 0" }
            ensure(value <= 100) { "Price must be less than 100" }

            Price(value)
        }
    }
}