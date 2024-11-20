package itstack.nil.icecream.Commons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import itstack.nil.icecream.LoginScreen.loginScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import itstack.nil.icecream.LoginScreen.splashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import itstack.nil.icecream.AllShopScreen.AddNewShop
import itstack.nil.icecream.AllShopScreen.DisplayAllShop
import itstack.nil.icecream.Categories.shopCategories
import itstack.nil.icecream.Categories.showcategories

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetStatusBarIconsColorToBlack()
            IceCreamApp()
        }
    }
}
@Composable
fun IceCreamApp() {
    // Create a NavController
    val navController = rememberNavController()

    // Define the NavHost for navigation
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { splashScreen(navController = navController) }
        composable("login") { loginScreen(navController = navController) }
        composable("app") { AppScreen(navController = navController) }
        composable("addShop") { AddNewShop() }
        composable("showCategories/{shop_name}/{shop_owner}/{address}") { backStackEntry ->
            val shopName = backStackEntry.arguments?.getString("shop_name") ?: ""
            val shopOwner = backStackEntry.arguments?.getString("shop_owner") ?: ""
            val address = backStackEntry.arguments?.getString("address") ?: ""
            shopCategories(navController = navController, shopName, shopOwner, address)
        }
        composable("showCategories") { showcategories(navController = navController) }
        composable("displayAllShop") {
            val context = LocalContext.current
            DisplayAllShop(navController = navController, context = context)
        }
    }
}

@Composable
fun SetStatusBarIconsColorToBlack() {
    val systemUiController = rememberSystemUiController()

    // Set the status bar and navigation bar color
    systemUiController.setSystemBarsColor(
        color = Color.White, // status bar background color
        darkIcons = true // Set dark icons (black icons)
    )
}