package com.example.starbucksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbucksapp.ui.theme.StarbucksAppTheme

data class MenuItem(val id: Int, val name: String, val desc: String, val image: Int)
data class Cart(val id: Int, val items: MutableList<MenuItem>)

class MainActivity : ComponentActivity() {

    private val dummyMenu = listOf(
        MenuItem(1, "Caramel Macchiato", "Espresso with vanilla syrup, milk, and caramel.", R.drawable.caramel),
        MenuItem(2, "Caffè Latte", "Rich espresso balanced with steamed milk.", R.drawable.latte),
        MenuItem(3, "Mocha", "Chocolate and espresso with milk.", R.drawable.mocha),
        MenuItem(4, "Matcha Latte", "Creamy matcha green tea with steamed milk.", R.drawable.matcha),
        MenuItem(5, "Vanilla Sweet Cream Cold Brew", "Cold brew topped with vanilla sweet cream.", R.drawable.coldbrew),
        MenuItem(6, "Chocolate Croissant", "Buttery croissant filled with rich chocolate.", R.drawable.croissant),
        MenuItem(7, "Banana Bread", "Moist banana bread with walnuts.", R.drawable.banana_bread),
        MenuItem(8, "Strawberry Acai Refresher", "Sweet strawberry with açaí and green coffee extract.", R.drawable.acai),
        MenuItem(9, "Iced Shaken Espresso", "Espresso shaken with ice and classic syrup.", R.drawable.shaken_espresso),
        MenuItem(10, "Blueberry Muffin", "Soft muffin with sweet blueberries.", R.drawable.blueberry_muffin)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarbucksAppTheme {
                val cart = remember { mutableStateListOf<MenuItem>() }
                var selectedTab by remember { mutableStateOf(0) }
                var showOnboarding by remember { mutableStateOf(true) }

                if (showOnboarding) {
                    OnboardingScreen { showOnboarding = false }
                } else {
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                BottomNavigationItem(
                                    selected = selectedTab == 0,
                                    onClick = { selectedTab = 0 },
                                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                    label = { Text("Home") }
                                )
                                BottomNavigationItem(
                                    selected = selectedTab == 1,
                                    onClick = { selectedTab = 1 },
                                    icon = { Icon(Icons.Default.LocalCafe, contentDescription = null) },
                                    label = { Text("Menu") }
                                )
                                BottomNavigationItem(
                                    selected = selectedTab == 2,
                                    onClick = { selectedTab = 2 },
                                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                                    label = { Text("Cart") }
                                )
                                BottomNavigationItem(
                                    selected = selectedTab == 3,
                                    onClick = { selectedTab = 3 },
                                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                                    label = { Text("Profile") }
                                )
                            }
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            when (selectedTab) {
                                0 -> HomeScreen()
                                1 -> MenuScreen(dummyMenu, cart)
                                2 -> CartScreen(cart)
                                3 -> ProfileScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF00704A)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo Starbucks
            Image(
                painter = painterResource(id = R.drawable.starbucks_logo),
                contentDescription = "Starbucks Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 24.dp)
            )

            Text(
                text = "Welcome to",
                color = Color.White,
                fontSize = 22.sp
            )

            Text(
                text = "Starbucks App",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Order coffee, collect stars, and enjoy rewards!",
                color = Color(0xFFD1E7DD),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 12.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onContinue,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text("Get Started", color = Color(0xFF00704A), fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun HomeScreen() {
    val recommendedMenu = listOf(
        MenuItem(1, "Caramel Macchiato", "Espresso with vanilla syrup, milk, and caramel.", R.drawable.caramel),
        MenuItem(2, "Caffè Latte", "Rich espresso balanced with steamed milk.", R.drawable.latte),
        MenuItem(3, "Mocha", "Chocolate and espresso with milk.", R.drawable.mocha)
    )

    val coffeeQuotes = listOf(
        "Coffee is a hug in a mug.",
        "Life begins after coffee.",
        "A yawn is a silent scream for coffee.",
        "Brew-tiful mornings start here."
    )

    val nearbyStores = listOf(
        "Starbucks - Mall Kelapa Gading",
        "Starbucks - Central Park Jakarta",
        "Starbucks - Plaza Indonesia",
        "Starbucks - Pacific Place"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Welcome to Starbucks!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00704A)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "☕ Coffee Quotes",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        coffeeQuotes.forEach {
            Text("• $it", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "📍 Nearby Starbucks",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        nearbyStores.forEach {
            Text("• $it", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "🍽️ Recommended Menu",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(recommendedMenu) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = 6.dp,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = item.name,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(item.desc, fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun MenuScreen(menuItems: List<MenuItem>, cart: MutableList<MenuItem>) {
    // Menyimpan jumlah tiap item yang ditambahkan ke cart
    val itemQuantities = remember { mutableStateMapOf<Int, Int>() }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        items(menuItems) { item ->
            val quantity = itemQuantities[item.id] ?: 0

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(item.desc, fontSize = 14.sp, color = Color.Gray)
                    }

                    // Quantity controls
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (quantity > 0) {
                            IconButton(onClick = {
                                if (quantity > 0) {
                                    itemQuantities[item.id] = quantity - 1
                                    cart.remove(item)
                                }
                            }) {
                                Icon(Icons.Default.Remove, contentDescription = "Decrease")
                            }
                            Text("$quantity", modifier = Modifier.padding(horizontal = 8.dp))
                        }

                        IconButton(onClick = {
                            itemQuantities[item.id] = quantity + 1
                            cart.add(item)
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CartScreen(cart: List<MenuItem>) {
    val groupedCart = cart.groupingBy { it.id }.eachCount()
    val distinctItems = cart.distinctBy { it.id }

    val total = groupedCart.map { (id, count) ->
        val item = cart.find { it.id == id }
        5.0 * count  // asumsi harga tiap item $5, nanti bisa kamu custom per item
    }.sum()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Your Cart", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        if (cart.isEmpty()) {
            Text("Your cart is empty.")
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp)
            ) {
                items(distinctItems) { item ->
                    val qty = groupedCart[item.id] ?: 0

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = item.image),
                                contentDescription = item.name,
                                modifier = Modifier
                                    .size(70.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text(item.desc, fontSize = 14.sp, color = Color.Gray)
                                Text("Qty: $qty", fontSize = 14.sp)
                            }
                            Text(
                                "$${String.format("%.2f", 5.0 * qty)}",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("$${String.format("%.2f", total)}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Handle checkout logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00704A))
            ) {
                Text("Order Now", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Profile Picture Dummy
        Surface(
            shape = CircleShape,
            elevation = 8.dp,
            modifier = Modifier.size(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile_placeholder), // Ganti dengan gambar kamu
                contentDescription = "Profile Picture",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Hi, Helsa Putri 👋", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Gold Member", color = Color(0xFF00704A), fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(20.dp))

        // Rewards Card
        Card(
            backgroundColor = Color(0xFFE8F5E9),
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("🎉 Starbucks Rewards", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Points: 1245")
                Text("Level: Gold")
                LinearProgressIndicator(
                    progress = 0.65f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    color = Color(0xFF00704A)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menu List
        val profileOptions = listOf(
            Pair(Icons.Default.Person, "Edit Profile"),
            Pair(Icons.Default.History, "Order History"),
            Pair(Icons.Default.Settings, "Settings"),
            Pair(Icons.Default.ExitToApp, "Logout")
        )

        profileOptions.forEach { (icon, label) ->
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(icon, contentDescription = null, tint = Color(0xFF00704A))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(label, fontSize = 16.sp)
                }
            }
        }
    }
}
