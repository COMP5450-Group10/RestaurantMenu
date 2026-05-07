package com.group10.restaurantmenu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.group10.restaurantmenu.data.MenuItem
import com.group10.restaurantmenu.ui.components.getCategoryColor
import com.group10.restaurantmenu.ui.components.getCategoryEmoji
import com.group10.restaurantmenu.ui.theme.*
import com.group10.restaurantmenu.viewmodel.CartViewModel

@Composable
fun ItemDetailScreen(
    item: MenuItem,
    cartViewModel: CartViewModel,
    onBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar("${item.name} added to cart")
            showSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = LightBackground,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.9f))
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
            }
        },
        bottomBar = {
            // Bottom add-to-cart bar
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = LightBackground.copy(alpha = 0.95f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Price", fontSize = 13.sp, color = SecondaryText)
                        Text(
                            text = "$${String.format("%.2f", item.price)}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W700,
                            color = DarkText
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                            .shadow(8.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(DarkText)
                            .clickable {
                                cartViewModel.addItem(item)
                                showSnackbar = true
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add to Cart",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Hero section
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    getCategoryColor(item.category).copy(alpha = 0.12f),
                                    getCategoryColor(item.category).copy(alpha = 0.06f),
                                    LightBackground
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getCategoryEmoji(item.category),
                        fontSize = 100.sp
                    )
                }
            }

            // Content
            item {
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    // Name and price
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.name,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.W700,
                                color = DarkText,
                                lineHeight = 36.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = item.category,
                                fontSize = 15.sp,
                                color = SecondaryText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .shadow(6.dp, RoundedCornerShape(14.dp))
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.White)
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "$${String.format("%.2f", item.price)}",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.W700,
                                color = DarkText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Info chips
                    Row {
                        InfoChip(
                            icon = Icons.Default.Schedule,
                            label = "${item.preparationTime} min"
                        )
                        if (item.isPopular) {
                            Spacer(modifier = Modifier.width(10.dp))
                            InfoChip(
                                icon = Icons.Default.TrendingUp,
                                label = "Popular",
                                highlighted = true
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // Description
                    Text(
                        text = "About",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = DarkText
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item.description,
                        fontSize = 16.sp,
                        color = Color(0xFF424245),
                        lineHeight = 24.sp
                    )

                    // Ingredients
                    if (item.ingredients.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(28.dp))
                        Text(
                            text = "Ingredients",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = DarkText
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        FlowRow(
                            mainAxisSpacing = 8.dp,
                            crossAxisSpacing = 8.dp
                        ) {
                            item.ingredients.forEach { ingredient ->
                                Box(
                                    modifier = Modifier
                                        .shadow(2.dp, RoundedCornerShape(10.dp))
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color.White)
                                        .padding(horizontal = 14.dp, vertical = 9.dp)
                                ) {
                                    Text(
                                        text = ingredient,
                                        fontSize = 14.sp,
                                        color = Color(0xFF424245)
                                    )
                                }
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
fun InfoChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    highlighted: Boolean = false
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (highlighted) PopularBackground else Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (highlighted) PopularOrange else SecondaryText,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.W500,
                color = if (highlighted) PopularOrange else Color(0xFF424245)
            )
        }
    }
}
