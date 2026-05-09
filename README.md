# La Bella Italia - Restaurant Menu App

## Group #10 Challenge - Restaurant Menu App

A feature-rich Android restaurant menu application built with **Kotlin** and **Jetpack Compose**, featuring custom styling, multiple screens, and full ordering functionality.

## GitHub Repository

[https://github.com/COMP5450-Group10/RestaurantMenu.git]

## Installation and Setup

### Prerequisites
1. Install Android Studio (latest stable, Hedgehog or newer)
2. Android SDK 34
3. Set up an Android emulator (API 26+) or connect a physical device

### Setup Steps
```bash
# 1. Clone the repository
git clone https://github.com/COMP5450-Group10/RestaurantMenu.git

# 2. Open in Android Studio
#    File -> Open -> select the project folder

# 3. Sync Gradle
#    Android Studio will auto-sync; or click "Sync Now" in the toolbar

# 4. Run the app
#    Click the green Run button or press Shift+F10
```

### Dependencies
| Package | Version | Purpose |
|---------|---------|---------|
| Kotlin | 2.0.0 | Programming language |
| Jetpack Compose BOM | 2024.02.00 | UI toolkit |
| Material 3 | (BOM managed) | Design system |
| Navigation Compose | 2.7.7 | Screen navigation |
| ViewModel Compose | 2.7.0 | State management |
| Accompanist FlowLayout | 0.34.0 | Flexible layout for ingredient chips |

### Build Requirements
- Android Studio Hedgehog (2023.1.1) or newer
- Kotlin 2.0.0
- Gradle 8.5
- Min SDK: 26 (Android 8.0)
- Target SDK: 34 (Android 14)

## Features

### Core Functionality
- Browse Menu: View menu items organized into 6 categories (Appetizers, Main Course, Pasta, Pizza, Desserts, Beverages).
- Search: Search for items by name.
- Category Filtering: Filter items by food category.
- Item Details: View with price, ingredients, and preparation time.
- Shopping Cart: See selected items and add more or remove items.
- Checkout: Ordering with three options (Dine-in, Takeout, or Delivery).
- Order Confirmation: Confirmation dialog with estimated prep time.

### Technical Features
- State Management: Cart state centralized using ViewModel pattern.
- Navigation: Screen transitions using Navigation Compose.
- Responsive Design: Edge to edge display including inset handling.
- Data Models: Data classes used for cart management and menu items.
- Real time Updates: Price calculations and cart totals updated dynamically

## Screenshots

### Home Screen
This is the main screen that displays the restaurant menu. It has a search bar, category filters, and scrollable menu item list. Here a user can find items and navigate to more detailed views.

<img width="360" height="780" alt="image" src="https://github.com/user-attachments/assets/7c1f435b-8638-48d3-8fdc-e3aa1290fb1e" />

### Item Information Screen
Information about the food is displayed (name, preparation time, description, price) here. It is accessed by clicking on a food item on the home screen. The user is also able to add the food to their cart on this screen.

<img width="360" height="780" alt="image" src="https://github.com/user-attachments/assets/9ee04f4f-fa7c-4574-8afa-05b844baa33c" />

### Shopping Cart Screen
It displays all the food items the user has selected along with individual item totals, overall order total as well as a clear cart button to remove all items from the cart and a checkout button for the user to proceed to checkout. It is accessed by clicking on the shopping cart icon at the top right of the home screen.

<img width="360" height="780" alt="image" src="https://github.com/user-attachments/assets/44135b6a-8d68-4f0e-8752-1e34553a5b73" />

### Checkout Screen
An order form where the user can select the order type, enter their personal information as well as view the order summary. Once the user is ready they can place their order by clicking on the place order button. This screen is accessed by clicking on the checkout button on the Shopping Cart Screen.

<img width="360" height="780" alt="image" src="https://github.com/user-attachments/assets/87b4e3f0-6536-46c3-abee-327cc8b1c110" />

## Project Structure

```
restaurant_menu_app/
├── app/src/main/java/com/group10/restaurantmenu/
│   ├── MainActivity.kt                    # App entry point
│   ├── data/
│   │   ├── MenuItem.kt                    # Data models (MenuItem, CartItem)
│   │   └── MenuData.kt                    # Static menu data (18 items, 6 categories)
│   ├── viewmodel/
│   │   └── CartViewModel.kt              # Cart state management (ViewModel)
│   └── ui/
│       ├── theme/
│       │   └── Theme.kt                  # Material 3 theme and colors
│       ├── navigation/
│       │   └── Navigation.kt             # NavHost and route definitions
│       ├── components/
│       │   ├── CategoryChips.kt          # Horizontal category filter chips
│       │   └── MenuItemCard.kt           # Menu item list card
│       └── screens/
│           ├── HomeScreen.kt             # Main menu browsing screen
│           ├── ItemDetailScreen.kt       # Individual item detail view
│           ├── CartScreen.kt             # Shopping cart screen
│           └── CheckoutScreen.kt         # Order checkout screen
├── app/build.gradle.kts                   # App-level dependencies
├── build.gradle.kts                       # Project-level plugins
├── settings.gradle.kts                    # Module includes
└── README.md                              # This file
```

### File Descriptions

#### Core Application Files
- MainActivity.kt: Activity that hosts the entire Compose application and also sets up theme and navigation.
- Navigation.kt: Defines navigation graph with 4 screens (Home, ItemDetail, Cart, Checkout) and handles the parameter passing between them.

#### Data Layer
- MenuItem.kt: Data classes for `MenuItem` (id, name, category, description, price, ingredients, prepTime, imageRes) and `CartItem` (menuItem, quantity).
- MenuData.kt: Static data source containing 18 predefined menu items from 6 categories.

#### ViewModel Layer
- CartViewModel.kt: Manages the state of the cart, handles add or remove operations, quantity updates, total calculations, and provides cart item count.

#### UI Components
- CategoryChips.kt: Reusable horizontal scrollable row of filter chips for category selection.
- MenuItemCard.kt: Card component displaying menu item preview with name, category badge, price, image, and navigation to details.

#### Screens
- HomeScreen.kt: Main screen with search bar, category filters, and scrollable menu grid. Also implements search and filter logic.
- ItemDetailScreen.kt: Detailed item view with image, preparation time, ingredient chips using FlowLayout, and add to cart functionality.
- CartScreen.kt: Shopping cart display with item removal, quantity steppers, empty cart state, and checkout navigation.
- CheckoutScreen.kt: Order form with text fields, order type radio buttons, order summary, conditional delivery address field, and confirmation dialog.

## Architecture

### Design Pattern
- Model: Data classes (`MenuItem`, `CartItem`) and data source (`MenuData`).
- View: Composable UI screens and components.
- ViewModel: `CartViewModel` manages logic and state.

### State Management
- ViewModel State: Cart items and calculations managed in `CartViewModel`.
- Compose State: UI state (search queries, filters, dialogs) managed with `remember` and `mutableStateOf`.
- Navigation State: Navigation handled by Navigation Compose with NavBackStackEntry.
