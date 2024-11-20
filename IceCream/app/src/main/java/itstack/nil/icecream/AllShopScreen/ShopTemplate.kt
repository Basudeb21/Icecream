package itstack.nil.icecream.AllShopScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import itstack.nil.icecream.Categories.shopCategories
import androidx.compose.ui.graphics.Color as ComposeColor


@Composable
fun ShopTemplate(
    shopName: String,
    ownerName: String,
    location: String,
    phno: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 8.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box acting as the colored border on the left
            Box(
                modifier = Modifier
                    .fillMaxHeight() // Ensures it touches top and bottom of the Card
                    .width(8.dp) // Fixed width for the border
                    .background(ComposeColor.Blue) // Background color for the border
                    .align(Alignment.CenterVertically) // Align it within the parent container
            )

            // Column for the shop details
            Column(
                modifier = Modifier
                    .weight(1f) // Occupy available space
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = shopName,
                    color = ComposeColor.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1, // Truncate if too long
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Person Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = ownerName,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = location,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "Phone Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = phno,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }

            // Button
            Button(
                onClick = {
                    navController.navigate("showCategories/$shopName/$ownerName/$location")
                },
                modifier = Modifier
                    .height(40.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )
            ) {
                Text(text = "SELECT", color = Color.White)
            }

        }
    }
}
