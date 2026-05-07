package com.group10.restaurantmenu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group10.restaurantmenu.ui.components.getCategoryEmoji
import com.group10.restaurantmenu.ui.theme.*
import com.group10.restaurantmenu.viewmodel.CartViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onBack: () -> Unit,
    onCheckout: () -> Unit
) {
    var showClearDialog by remember { mutableStateOf(false) }

    if (showClearDialog) {
        AlertDialog(
            onDismissRequest = { showClearDialog = false },
            icon = {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFFFFEBEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = null,
                        tint = ErrorRed,
                        modifier = Modifier.size(28.dp)
                    )
                }
            },
            title = { Text("Clear Cart?", fontWeight = FontWeight.W600) },
            text = { Text("This will remove all items from your cart.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        cartViewModel.clearCart()
                        showClearDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = ErrorRed)
                ) { Text("Clear All", fontWeight = FontWeight.W600) }
            },
            dismissButton = {
                TextButton(onClick = { showClearDialog = false }) {
                    Text("Cancel", fontWeight = FontWeight.W600)
                }
            }
        )
    }

    Scaffold(
        containerColor = LightBackground,
        bottomBar = {
            if (cartViewModel.items.isNotEmpty()) {
                Surface(
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp,
                    color = LightBackground.copy(alpha = 0.95f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "${cartViewModel.itemCount} item${if (cartViewModel.itemCount > 1) "s" else ""}",
                                fontSize = 14.sp,
                                color = SecondaryText
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "$${String.format("%.2f", cartViewModel.totalAmount)}",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.W700,
                                color = DarkText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .shadow(8.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(DarkText)
                                .clickable { onCheckout() }
                                .padding(horizontal = 28.dp, vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Checkout",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .shadow(4.dp, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .clickable { onBack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            modifier = Modifier.size(18.dp),
                            tint = DarkText
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = "Your Cart",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (cartViewModel.items.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .shadow(4.dp, RoundedCornerShape(12.dp))
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .clickable { showClearDialog = true },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.DeleteOutline,
                                contentDescription = "Clear cart",
                                modifier = Modifier.size(20.dp),
                                tint = ErrorRed
                            )
                        }
                    }
                }
            }

            if (cartViewModel.items.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 120.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .shadow(8.dp, RoundedCornerShape(24.dp))
                                .clip(RoundedCornerShape(24.dp))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🛒", fontSize = 44.sp)
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Your cart is empty",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W600,
                            color = DarkText
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Start adding delicious items\nfrom our menu",
                            fontSize = 15.sp,
                            color = SecondaryText,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(DarkText)
                                .clickable { onBack() }
                                .padding(horizontal = 24.dp, vertical = 14.dp)
                        ) {
                            Text(
                                text = "Browse Menu",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W600,
                                color = Color.White
                            )
                        }
                    }
                }
            } else {
                items(cartViewModel.items, key = { it.menuItem.id }) { cartItem ->
                    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(4.dp, RoundedCornerShape(20.dp))
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White)
                                .padding(14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Emoji
                                Box(
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(LightBackground),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = getCategoryEmoji(cartItem.menuItem.category),
                                        fontSize = 28.sp
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = cartItem.menuItem.name,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W600,
                                        color = DarkText
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = "$${String.format("%.2f", cartItem.menuItem.price)}",
                                        fontSize = 14.sp,
                                        color = SecondaryText
                                    )
                                }

                                // Quantity stepper
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(LightBackground)
                                        .padding(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.White)
                                            .clickable {
                                                cartViewModel.updateQuantity(
                                                    cartItem.menuItem.id,
                                                    cartItem.quantity - 1
                                                )
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Remove,
                                            contentDescription = "Decrease",
                                            modifier = Modifier.size(16.dp),
                                            tint = DarkText
                                        )
                                    }

                                    Text(
                                        text = "${cartItem.quantity}",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W600,
                                        color = DarkText,
                                        modifier = Modifier.padding(horizontal = 14.dp)
                                    )

                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.White)
                                            .clickable {
                                                cartViewModel.updateQuantity(
                                                    cartItem.menuItem.id,
                                                    cartItem.quantity + 1
                                                )
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Increase",
                                            modifier = Modifier.size(16.dp),
                                            tint = DarkText
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = "$${String.format("%.2f", cartItem.totalPrice)}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W700,
                                    color = DarkText
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
