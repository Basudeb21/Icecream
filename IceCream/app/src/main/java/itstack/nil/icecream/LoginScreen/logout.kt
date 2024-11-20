package itstack.nil.icecream.LoginScreen

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController

fun logOut(navController: NavController, context: Context) {
    val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)

    sharedPreferences.edit().remove("user_token").apply()

    Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT).show()

    navController.navigate("login") {
        popUpTo("login") { inclusive = true }
        launchSingleTop = true
    }
}