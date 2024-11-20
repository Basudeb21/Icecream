package itstack.nil.icecream.Categories

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import itstack.nil.icecream.Responses.CategoriesData

@Composable
fun shopCategories(navController: NavController, shopName: String, shopOwner: String, address: String) {
    // State to hold fetched categories
    val categoriesState: MutableState<List<CategoriesData>> = remember { mutableStateOf(emptyList()) }
    val context = LocalContext.current

    // Button for navigating to the 'Select Store' screen
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Blue), // Blue background
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 42.dp, horizontal = 16.dp) // Padding around the card
        ) {
            Column(
                modifier = Modifier.padding(16.dp), // Padding inside the card
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "$shopName",
                    fontSize = 20.sp,
                    color = Color.White, // White text
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp)) // Spacing between texts
                Text(
                    text = "Owner: $shopOwner",
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ADDRESS: $address",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        // Fetch categories when the composable is first launched
        LaunchedEffect(Unit) {
            val categoriesHandler = CategoriesHandler(context)
            categoriesHandler.fetchCategories(
                onResult = { categories ->
                    // Update the state with the fetched categories
                    categoriesState.value = categories
                },
                onError = { errorMessage ->
                    // Show error message in case of failure
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        }

        // Display the categories in a grid
        categoryTemplate(categories = categoriesState.value)
    }
}
