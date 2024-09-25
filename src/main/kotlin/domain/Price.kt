package domain

data class Price(val value: Double) {
    init {
        if(value <= 0) { throw InvalidFieldException("Price cannot be less than or equal to 0") }
        if(value > 100) { throw InvalidFieldException("Price cannot be more than 100") }
    }
}