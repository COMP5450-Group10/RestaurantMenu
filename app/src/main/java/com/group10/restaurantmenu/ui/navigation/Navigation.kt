package com.group10.restaurantmenu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.group10.restaurantmenu.data.MenuData
import com.group10.restaurantmenu.ui.screens.*
import com.group10.restaurantmenu.viewmodel.CartViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ItemDetail : Screen("item_detail/{itemId}") {
        fun createRoute(itemId: String) = "item_detail/$itemId"
    }
    object Cart : Screen("cart")
    object Checkout : Screen("checkout")
}

@Composable
fun AppNavigation(cartViewModel: CartViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                cartViewModel = cartViewModel,
                onItemClick = { item ->
                    navController.navigate(Screen.ItemDetail.createRoute(item.id))
                },
                onCartClick = {
                    navController.navigate(Screen.Cart.route)
                }
            )
        }

        composable(
            route = Screen.ItemDetail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            val item = MenuData.items.find { it.id == itemId }
            if (item != null) {
                ItemDetailScreen(
                    item = item,
                    cartViewModel = cartViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Screen.Cart.route) {
            CartScreen(
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() },
                onCheckout = { navController.navigate(Screen.Checkout.route) }
            )
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() },
                onOrderPlaced = {
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                }
            )
        }
    }
}
