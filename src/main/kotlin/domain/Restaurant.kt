package domain

data class Restaurant(val id: String, val items: Map<Category, Set<MenuItem>>)