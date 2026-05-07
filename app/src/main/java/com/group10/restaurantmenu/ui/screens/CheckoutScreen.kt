package com.group10.restaurantmenu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import com.group10.restaurantmenu.ui.theme.*
import com.group10.restaurantmenu.viewmodel.CartViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CheckoutScreen(
    cartViewModel: CartViewModel,
    onBack: () -> Unit,
    onOrderPlaced: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var orderType by remember { mutableStateOf("Dine-in") }
    var isProcessing by remember { mutableStateOf(false) }
    var showConfirmation by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var addressError by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val subtotal = cartViewModel.totalAmount
    val tax = subtotal * 0.13
    val delivery = if (orderType == "Delivery") 5.99 else 0.0
    val total = subtotal + tax + delivery

    if (showConfirmation) {
        AlertDialog(
            onDismissRequest = {},
            icon = {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFE8F5E9)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SuccessGreen,
                        modifier = Modifier.size(36.dp)
                    )
                }
            },
            title = {
                Text(
                    "Order Confirmed!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W700
                )
            },
            text = {
                Text(
                    "Thank you, $name!\nYour $orderType order will be ready\nin 25-35 minutes.",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = SecondaryText,
                    lineHeight = 22.sp
                )
            },
            confirmButton = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(DarkText)
                        .clickable {
                            showConfirmation = false
                            onOrderPlaced()
                        }
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Done",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White
                    )
                }
            }
        )
    }

    Scaffold(
        containerColor = LightBackground,
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = LightBackground.copy(alpha = 0.95f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .shadow(8.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isProcessing) SecondaryText else DarkText)
                            .clickable(enabled = !isProcessing) {
                                nameError = name.isBlank()
                                phoneError = phone.isBlank()
                                addressError = orderType == "Delivery" && address.isBlank()

                                if (!nameError && !phoneError && !addressError) {
                                    isProcessing = true
                                    scope.launch {
                                        delay(2000)
                                        isProcessing = false
                                        cartViewModel.clearCart()
                                        showConfirmation = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isProcessing) {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp)
                            )
                        } else {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Place Order",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.W600,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.White.copy(alpha = 0.2f))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "$${String.format("%.2f", total)}",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.W600,
                                        color = Color.White
                                    )
                                }
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
                        text = "Checkout",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkText
                    )
                }
            }

            // Order type
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        text = "Order Type",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = DarkText
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp, RoundedCornerShape(14.dp))
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White)
                            .padding(4.dp)
                    ) {
                        Row {
                            listOf("Dine-in" to "🍽", "Takeout" to "📦", "Delivery" to "🚗").forEach { (type, emoji) ->
                                val isSelected = orderType == type
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(11.dp))
                                        .background(if (isSelected) DarkText else Color.Transparent)
                                        .clickable { orderType = type }
                                        .padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(emoji, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = type,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.W600,
                                            color = if (isSelected) Color.White else SecondaryText
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Your Details
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 28.dp)) {
                    Text(
                        text = "Your Details",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = DarkText
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Name field
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it; nameError = false },
                        label = { Text("Full Name") },
                        leadingIcon = {
                            Icon(Icons.Default.PersonOutline, null, tint = SecondaryText)
                        },
                        isError = nameError,
                        supportingText = if (nameError) {{ Text("Name is required", color = ErrorRed) }} else null,
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = DarkText
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Phone field
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it; phoneError = false },
                        label = { Text("Phone Number") },
                        leadingIcon = {
                            Icon(Icons.Default.Phone, null, tint = SecondaryText)
                        },
                        isError = phoneError,
                        supportingText = if (phoneError) {{ Text("Phone is required", color = ErrorRed) }} else null,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = DarkText
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (orderType == "Delivery") {
                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = address,
                            onValueChange = { address = it; addressError = false },
                            label = { Text("Delivery Address") },
                            leadingIcon = {
                                Icon(Icons.Default.LocationOn, null, tint = SecondaryText)
                            },
                            isError = addressError,
                            supportingText = if (addressError) {{ Text("Address is required", color = ErrorRed) }} else null,
                            maxLines = 2,
                            shape = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = DarkText
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Summary
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        text = "Summary",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = DarkText
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(18.dp)
                    ) {
                        Column {
                            // Items
                            cartViewModel.items.forEach { cartItem ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(22.dp)
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(LightBackground),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "${cartItem.quantity}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.W600,
                                            color = DarkText
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = cartItem.menuItem.name,
                                        fontSize = 15.sp,
                                        color = Color(0xFF424245),
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = "$${String.format("%.2f", cartItem.totalPrice)}",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W500,
                                        color = DarkText
                                    )
                                }
                            }

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = LightBackground
                            )

                            // Subtotal
                            SummaryRow("Subtotal", subtotal)
                            Spacer(modifier = Modifier.height(6.dp))
                            SummaryRow("Tax (13%)", tax)
                            if (orderType == "Delivery") {
                                Spacer(modifier = Modifier.height(6.dp))
                                SummaryRow("Delivery", delivery)
                            }
                            Spacer(modifier = Modifier.height(12.dp))

                            // Total
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.W700,
                                    color = DarkText
                                )
                                Text(
                                    text = "$${String.format("%.2f", total)}",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.W700,
                                    color = DarkText
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun SummaryRow(label: String, amount: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 15.sp, color = SecondaryText)
        Text(
            text = "$${String.format("%.2f", amount)}",
            fontSize = 15.sp,
            color = Color(0xFF424245)
        )
    }
}
