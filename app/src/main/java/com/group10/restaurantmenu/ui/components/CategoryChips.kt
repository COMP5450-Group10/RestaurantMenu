package com.group10.restaurantmenu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group10.restaurantmenu.ui.theme.DarkText
import com.group10.restaurantmenu.ui.theme.SecondaryText

@Composable
fun CategoryChips(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (isSelected) DarkText else Color.White)
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = getCategoryEmoji(category),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = category,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = if (isSelected) Color.White else SecondaryText
                    )
                }
            }
        }
    }
}

fun getCategoryEmoji(category: String): String {
    return when (category) {
        "All" -> "🍽"
        "Appetizers" -> "🥗"
        "Main Course" -> "🥩"
        "Pasta" -> "🍝"
        "Pizza" -> "🍕"
        "Desserts" -> "🍰"
        "Beverages" -> "☕"
        else -> "🍴"
    }
}
