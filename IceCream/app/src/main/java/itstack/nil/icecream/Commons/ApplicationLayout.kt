package itstack.nil.icecream.Commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import itstack.nil.icecream.AllShopScreen.DisplayAllShop
import itstack.nil.icecream.Categories.CategoriesHandler
import itstack.nil.icecream.Categories.categoryTemplate
import itstack.nil.icecream.Categories.showcategories
import itstack.nil.icecream.GetAllOrderScreen.displayAllOrder
import itstack.nil.icecream.LoginScreen.logOut
import android.widget.Toast


@Composable
fun AppScreen(navController: NavController) {
    val navItemList = listOf(
        NavItem("Category", Icons.Filled.Favorite),
        NavItem("Cart", Icons.Default.ShoppingCart),
        NavItem("All Order", Icons.Default.Menu),
        NavItem("Logout", Icons.Filled.ExitToApp)
    )

    // Track the selected index and ensure logOut is only called once
    var selectedIndex by remember { mutableStateOf(0) }
    var loggedOut by remember { mutableStateOf(false) }  // Track if the user has logged out
    val context = LocalContext.current // Get the context here

    // Handle the bottom navigation bar and prevent repetitive logout
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            if (index == 3 && !loggedOut) { // Only logout if not already logged out
                                loggedOut = true
                                logOut(navController = navController, context = context) // Pass context here
                            } else {
                                selectedIndex = index
                            }
                        },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = "") },
                        label = { Text(text = navItem.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Pass the selectedIndex to ContentScreen
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex = selectedIndex, navController = navController)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int, navController: NavController) {

    when (selectedIndex) {
        0 -> showcategories(navController = navController)
        1 -> DisplayAllShop(navController = navController, LocalContext.current)
        2 -> displayAllOrder(navController = navController)
        // No action needed for logout here since logOut is already triggered
        else -> {
            logOut(navController,LocalContext.current)
        }
    }
}
