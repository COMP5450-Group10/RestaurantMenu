package com.group10.restaurantmenu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group10.restaurantmenu.data.MenuData
import com.group10.restaurantmenu.data.MenuItem
import com.group10.restaurantmenu.ui.components.CategoryChips
import com.group10.restaurantmenu.ui.components.MenuItemCard
import com.group10.restaurantmenu.ui.theme.*
import com.group10.restaurantmenu.viewmodel.CartViewModel

@Composable
fun HomeScreen(
    cartViewModel: CartViewModel,
    onItemClick: (MenuItem) -> Unit,
    onCartClick: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    val filteredItems = remember(selectedCategory, searchQuery) {
        MenuData.items.filter { item ->
            val matchesCategory = selectedCategory == "All" || item.category == selectedCategory
            val matchesSearch = searchQuery.isEmpty() ||
                    item.name.contains(searchQuery, ignoreCase = true) ||
                    item.description.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = LightBackground
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "La Bella Italia",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W700,
                            color = DarkText
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Authentic Italian Cuisine",
                            fontSize = 15.sp,
                            color = SecondaryText
                        )
                    }

                    // Cart button
                    val cartCount = cartViewModel.itemCount
                    Box(
                        modifier = Modifier
                            .shadow(8.dp, RoundedCornerShape(14.dp))
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (cartCount > 0) DarkText else Color.White)
                            .clickable { onCartClick() }
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ShoppingBag,
                                contentDescription = "Cart",
                                tint = if (cartCount > 0) Color.White else DarkText,
                                modifier = Modifier.size(20.dp)
                            )
                            if (cartCount > 0) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "$cartCount",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W600,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }

            // Search bar
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .shadow(4.dp, RoundedCornerShape(14.dp))
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White)
                ) {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = {
                            Text(
                                "Search dishes, ingredients...",
                                color = SecondaryText,
                                fontSize = 16.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = SecondaryText
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Clear",
                                        tint = SecondaryText,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Category chips
            item {
                Spacer(modifier = Modifier.height(8.dp))
                CategoryChips(
                    categories = MenuData.categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Section header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (selectedCategory == "All") "All Dishes" else selectedCategory,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkText
                    )
                    Text(
                        text = "${filteredItems.size} items",
                        fontSize = 15.sp,
                        color = SecondaryText
                    )
                }
            }

            // Menu items
            items(filteredItems, key = { it.id }) { item ->
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    MenuItemCard(
                        item = item,
                        onTap = { onItemClick(item) },
                        onAddToCart = {
                            cartViewModel.addItem(item)
                        }
                    )
                }
            }
        }
    }
}
