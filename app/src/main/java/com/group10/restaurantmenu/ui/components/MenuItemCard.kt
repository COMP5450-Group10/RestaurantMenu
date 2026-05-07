package com.group10.restaurantmenu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group10.restaurantmenu.data.MenuItem
import com.group10.restaurantmenu.ui.theme.*

@Composable
fun MenuItemCard(
    item: MenuItem,
    onTap: () -> Unit,
    onAddToCart: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = Color.Black.copy(alpha = 0.04f))
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onTap() }
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Emoji placeholder
            Box(
                modifier = Modifier
                    .size(82.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(getCategoryColor(item.category).copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getCategoryEmoji(item.category),
                    fontSize = 36.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = DarkText,
                        modifier = Modifier.weight(1f)
                    )
                    if (item.isPopular) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(PopularBackground)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "★ Popular",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.W600,
                                color = PopularOrange
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.description,
                    fontSize = 13.sp,
                    color = SecondaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${String.format("%.2f", item.price)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Outline)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = SecondaryText,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "${item.preparationTime} min",
                        fontSize = 13.sp,
                        color = SecondaryText
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Add button
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(DarkText)
                            .clickable { onAddToCart() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

fun getCategoryColor(category: String): Color {
    return when (category) {
        "Appetizers" -> Color(0xFF34C759)
        "Main Course" -> Color(0xFFFF6B35)
        "Pasta" -> Color(0xFFFFCC02)
        "Pizza" -> Color(0xFFFF3B30)
        "Desserts" -> Color(0xFFAF52DE)
        "Beverages" -> Color(0xFF007AFF)
        else -> Color(0xFF8E8E93)
    }
}
