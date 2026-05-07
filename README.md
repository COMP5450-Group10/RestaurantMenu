# La Bella Italia - Restaurant Menu App

## Group #10 Challenge - Restaurant Menu App

A feature-rich Android restaurant menu application built with **Kotlin** and **Jetpack Compose**, featuring custom styling, multiple screens, and full ordering functionality.

## GitHub Repository

https://github.com/YOUR_USERNAME/restaurant_menu_app

## Features

- **Browse Menu**: View menu items organized by categories (Appetizers, Main Course, Pasta, Pizza, Desserts, Beverages)
- **Search**: Real-time search functionality to find menu items
- **Category Filtering**: Filter items by food category using chip selectors
- **Item Details**: Detailed view with ingredients, preparation time, and pricing
- **Shopping Cart**: Add/remove items, adjust quantities with stepper controls
- **Checkout**: Complete ordering with Dine-in, Takeout, or Delivery options
- **Order Confirmation**: Animated confirmation dialog with estimated time
- **Material Design 3**: Modern UI with Jetpack Compose

## Screenshots

### Home Screen
The main screen displays the restaurant menu with search bar, category filters, and scrollable menu item list.

### Item Detail Screen
Shows full details including ingredients, preparation time, price, and add-to-cart functionality.

### Cart Screen
Displays all added items with quantity controls and order total calculation.

### Checkout Screen
Order form with customer information, order type selection (Dine-in/Takeout/Delivery), and order summary with tax calculation.

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

## How to Configure and Run

### Prerequisites

1. Install Android Studio (latest stable, Hedgehog or newer)
2. Android SDK 34
3. Set up an Android emulator (API 26+) or connect a physical device

### Setup Steps

```bash
# 1. Clone the repository
git clone https://github.com/YOUR_USERNAME/restaurant_menu_app.git

# 2. Open in Android Studio
#    File -> Open -> select the project folder

# 3. Sync Gradle
#    Android Studio will auto-sync; or click "Sync Now" in the toolbar

# 4. Run the app
#    Click the green Run button or press Shift+F10
```

## Dependencies

| Package | Version | Purpose |
|---------|---------|---------|
| Kotlin | 2.0.0 | Programming language |
| Jetpack Compose BOM | 2024.02.00 | UI toolkit |
| Material 3 | (BOM managed) | Design system |
| Navigation Compose | 2.7.7 | Screen navigation |
| ViewModel Compose | 2.7.0 | State management |
| Accompanist FlowLayout | 0.34.0 | Flexible layout for ingredient chips |

## Architecture

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material Design 3)
- **State Management**: ViewModel + Compose State
- **Navigation**: Navigation Compose with typed routes
- **Data**: Static menu data (easily replaceable with API/database)

## Team Work Division

- **Member 1**: Home screen, search, category filtering
- **Member 2**: Item detail screen, menu data models
- **Member 3**: Cart screen, ViewModel state management
- **Member 4**: Checkout screen, order flow, navigation

## Build Requirements

- Android Studio Hedgehog (2023.1.1) or newer
- Kotlin 2.0.0
- Gradle 8.5
- Min SDK: 26 (Android 8.0)
- Target SDK: 34 (Android 14)
