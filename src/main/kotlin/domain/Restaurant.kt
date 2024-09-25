package domain

data class Restaurant(val id: String, val items: Map<Category, Set<MenuItem>>) {

    fun addItem(category: Category, item: MenuItem): Restaurant {
        val itemsInCategory = items[category] ?: emptySet()
        check(itemsInCategory.size < 10) { "Too many items" }
        
        return Restaurant(id, items + (category to (itemsInCategory + item)))
    }
}