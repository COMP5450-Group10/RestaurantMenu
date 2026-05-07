package com.group10.restaurantmenu.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.group10.restaurantmenu.data.CartItem
import com.group10.restaurantmenu.data.MenuItem

class CartViewModel : ViewModel() {
    private val _items = mutableStateListOf<CartItem>()
    val items: List<CartItem> get() = _items

    val itemCount: Int get() = _items.sumOf { it.quantity }
    val totalAmount: Double get() = _items.sumOf { it.totalPrice }

    fun addItem(menuItem: MenuItem) {
        val existing = _items.find { it.menuItem.id == menuItem.id }
        if (existing != null) {
            val index = _items.indexOf(existing)
            _items[index] = existing.copy(quantity = existing.quantity + 1)
        } else {
            _items.add(CartItem(menuItem = menuItem))
        }
    }

    fun removeItem(menuItemId: String) {
        _items.removeAll { it.menuItem.id == menuItemId }
    }

    fun updateQuantity(menuItemId: String, quantity: Int) {
        val index = _items.indexOfFirst { it.menuItem.id == menuItemId }
        if (index >= 0) {
            if (quantity <= 0) {
                _items.removeAt(index)
            } else {
                _items[index] = _items[index].copy(quantity = quantity)
            }
        }
    }

    fun clearCart() {
        _items.clear()
    }
}
