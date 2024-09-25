package domain

data class Name(val value: String) {
    init {
        if(value.isBlank()) { throw InvalidFieldException("Name cannot be blank") }
        if(value.length > 50) { throw InvalidFieldException("Name cannot be more than 50 characters") }
        if(!value.matches(Regex("^[a-zA-Z0-9- ]+\$") )) { throw InvalidFieldException("Name can only contain letters, numbers, spaces and hypens") }
    }
}