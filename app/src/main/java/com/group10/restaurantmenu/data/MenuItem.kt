package com.group10.restaurantmenu.data

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val isPopular: Boolean = false,
    val ingredients: List<String> = emptyList(),
    val preparationTime: Int = 10
)

data class CartItem(
    val menuItem: MenuItem,
    var quantity: Int = 1
) {
    val totalPrice: Double get() = menuItem.price * quantity
}
