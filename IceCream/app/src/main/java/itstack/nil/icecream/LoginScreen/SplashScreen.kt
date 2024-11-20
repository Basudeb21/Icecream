package itstack.nil.icecream.LoginScreen

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import itstack.nil.icecream.R

@Composable
fun splashScreen(navController: NavController){
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        // Wait for 3 seconds and navigate to Home
        kotlinx.coroutines.delay(3000)
        val sharedPreferences =
            context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("user_token", null)

        if (!token.isNullOrEmpty()) {
            navController.navigate("app")
        } else {
            navController.navigate("login")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.icecream),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = "Welcome to Icecream Apk",
        )
    }
}