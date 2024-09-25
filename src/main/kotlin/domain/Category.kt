package domain

enum class Category {
    STARTERS, MAIN, DESSERTS, DRINKS;

    companion object {
        fun fromString(value: String): Category {
            return when (value) {
                "starters" -> STARTERS
                "main" -> MAIN
                "desserts" -> DESSERTS
                "drinks" -> DRINKS
                else -> throw InvalidFieldException("Invalid category: $value")
            }
        }
    }
}