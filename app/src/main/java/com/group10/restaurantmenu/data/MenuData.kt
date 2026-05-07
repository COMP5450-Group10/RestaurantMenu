package com.group10.restaurantmenu.data

object MenuData {
    val categories = listOf("All", "Appetizers", "Main Course", "Pasta", "Pizza", "Desserts", "Beverages")

    val items = listOf(
        MenuItem(id = "a1", name = "Bruschetta", description = "Toasted bread topped with fresh tomatoes, garlic, basil, and olive oil", price = 8.99, category = "Appetizers", isPopular = true, ingredients = listOf("Bread", "Tomatoes", "Garlic", "Basil", "Olive Oil"), preparationTime = 10),
        MenuItem(id = "a2", name = "Calamari Fritti", description = "Crispy fried squid rings served with marinara sauce", price = 11.99, category = "Appetizers", ingredients = listOf("Squid", "Flour", "Marinara Sauce", "Lemon"), preparationTime = 12),
        MenuItem(id = "a3", name = "Caprese Salad", description = "Fresh mozzarella, tomatoes, and basil with balsamic glaze", price = 9.99, category = "Appetizers", isPopular = true, ingredients = listOf("Mozzarella", "Tomatoes", "Basil", "Balsamic"), preparationTime = 8),

        MenuItem(id = "m1", name = "Grilled Salmon", description = "Atlantic salmon fillet with lemon butter sauce and seasonal vegetables", price = 24.99, category = "Main Course", isPopular = true, ingredients = listOf("Salmon", "Lemon", "Butter", "Asparagus", "Cherry Tomatoes"), preparationTime = 25),
        MenuItem(id = "m2", name = "Filet Mignon", description = "8oz premium beef tenderloin with red wine reduction", price = 34.99, category = "Main Course", ingredients = listOf("Beef Tenderloin", "Red Wine", "Garlic", "Rosemary"), preparationTime = 30),
        MenuItem(id = "m3", name = "Chicken Parmesan", description = "Breaded chicken breast with marinara and melted mozzarella", price = 18.99, category = "Main Course", isPopular = true, ingredients = listOf("Chicken", "Breadcrumbs", "Marinara", "Mozzarella", "Parmesan"), preparationTime = 22),

        MenuItem(id = "p1", name = "Spaghetti Carbonara", description = "Classic Roman pasta with egg, pecorino, pancetta, and black pepper", price = 16.99, category = "Pasta", isPopular = true, ingredients = listOf("Spaghetti", "Egg", "Pecorino", "Pancetta", "Black Pepper"), preparationTime = 18),
        MenuItem(id = "p2", name = "Fettuccine Alfredo", description = "Creamy parmesan sauce with fresh fettuccine pasta", price = 15.99, category = "Pasta", ingredients = listOf("Fettuccine", "Cream", "Parmesan", "Butter", "Garlic"), preparationTime = 15),
        MenuItem(id = "p3", name = "Penne Arrabbiata", description = "Spicy tomato sauce with garlic and red chili flakes", price = 14.99, category = "Pasta", ingredients = listOf("Penne", "Tomatoes", "Garlic", "Chili", "Parsley"), preparationTime = 15),

        MenuItem(id = "z1", name = "Margherita Pizza", description = "San Marzano tomatoes, fresh mozzarella, basil on thin crust", price = 14.99, category = "Pizza", isPopular = true, ingredients = listOf("Dough", "San Marzano Tomatoes", "Mozzarella", "Basil"), preparationTime = 20),
        MenuItem(id = "z2", name = "Quattro Formaggi", description = "Four cheese pizza with mozzarella, gorgonzola, parmesan, and fontina", price = 17.99, category = "Pizza", ingredients = listOf("Dough", "Mozzarella", "Gorgonzola", "Parmesan", "Fontina"), preparationTime = 20),
        MenuItem(id = "z3", name = "Prosciutto e Rucola", description = "Prosciutto, arugula, shaved parmesan with truffle oil drizzle", price = 19.99, category = "Pizza", ingredients = listOf("Dough", "Prosciutto", "Arugula", "Parmesan", "Truffle Oil"), preparationTime = 20),

        MenuItem(id = "d1", name = "Tiramisu", description = "Classic Italian dessert with espresso-soaked ladyfingers and mascarpone", price = 9.99, category = "Desserts", isPopular = true, ingredients = listOf("Ladyfingers", "Espresso", "Mascarpone", "Cocoa"), preparationTime = 5),
        MenuItem(id = "d2", name = "Panna Cotta", description = "Vanilla bean cream dessert with berry compote", price = 8.99, category = "Desserts", ingredients = listOf("Cream", "Vanilla", "Sugar", "Berries"), preparationTime = 5),
        MenuItem(id = "d3", name = "Chocolate Lava Cake", description = "Warm chocolate cake with molten center served with vanilla gelato", price = 11.99, category = "Desserts", ingredients = listOf("Dark Chocolate", "Butter", "Eggs", "Flour", "Gelato"), preparationTime = 15),

        MenuItem(id = "b1", name = "Italian Espresso", description = "Rich and bold single-shot espresso", price = 3.99, category = "Beverages", ingredients = listOf("Coffee Beans"), preparationTime = 3),
        MenuItem(id = "b2", name = "Limoncello Spritz", description = "Refreshing limoncello with prosecco and soda water", price = 12.99, category = "Beverages", isPopular = true, ingredients = listOf("Limoncello", "Prosecco", "Soda Water", "Lemon"), preparationTime = 5),
        MenuItem(id = "b3", name = "Fresh Lemonade", description = "House-made lemonade with fresh mint", price = 5.99, category = "Beverages", ingredients = listOf("Lemon", "Sugar", "Mint", "Water"), preparationTime = 5),
    )
}
