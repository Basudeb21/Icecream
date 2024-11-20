package itstack.nil.icecream.Categories

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.filled.Favorite
import itstack.nil.icecream.Responses.CategoriesData

@Composable
fun categoryTemplate(categories: List<CategoriesData>?) {
    if (categories.isNullOrEmpty()) {
        Text(text = "No categories found.")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(categories.size) { index ->
                val category = categories[index]

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .padding(10.dp)
                        .height(100.dp)
                        .width(150.dp)
                        .shadow(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite Icon",
                            tint = Color.Blue,
                            modifier = Modifier.size(40.dp)
                        )

                        Text(
                            text = category.name,
                            modifier = Modifier.padding(top = 8.dp),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
