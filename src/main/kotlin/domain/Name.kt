package domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

data class Name(val value: String) {
    companion object {
        fun from(value: String): Either<String, Name> = either {
            ensure(value.isNotEmpty()) { "Name cannot be empty" }
            ensure(value.length <= 50) { "Name cannot be longer than 50 characters" }
            ensure(value.matches(Regex("^[a-zA-Z0-9- ]*\$"))) { "Name can only contain alphanumeric characters and hyphens" }

            Name(value)
        }
    }
}