package itstack.nil.icecream.Categories

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import itstack.nil.icecream.Responses.CategoriesData
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun showcategories(navController: NavController) {
    val categoriesState: MutableState<List<CategoriesData>> = remember { mutableStateOf(emptyList()) }
    val context = LocalContext.current

    // Button for navigating to the 'Select Store' screen
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                navController.navigate("displayAllShop")
            },
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 45.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(
                text = "Select Store",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 5.dp),
                color = Color.White
            )
        }

        LaunchedEffect(Unit) {
            val categoriesHandler = CategoriesHandler(context)
            categoriesHandler.fetchCategories(
                onResult = { categories ->
                    categoriesState.value = categories
                },
                onError = { errorMessage ->
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        }

        categoryTemplate(categories = categoriesState.value)
    }
}
