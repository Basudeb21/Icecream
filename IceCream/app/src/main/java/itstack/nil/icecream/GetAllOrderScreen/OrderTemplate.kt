package itstack.nil.icecream.GetAllOrderScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
fun allOrderTemplate(updated_at: String, shop_name: String, payment_mode: String, grand_total: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 8.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box filling the height of the card, but not the screen
            Box(
                modifier = Modifier
                    .background(ComposeColor.Blue)
                    .fillMaxHeight() // This makes the Box take the full height of the Card's Row
                    .padding(horizontal = 8.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "$updated_at",
                    fontSize = 12.sp,
                    color = ComposeColor.White, // White text color
                    modifier = Modifier.align(Alignment.Center) // Center the text inside the Box
                )
            }

            // Column for the other details
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .fillMaxHeight() // This ensures the Column takes up the full height of the Card
            ) {
                Text(
                    text = "$shop_name",
                    color = ComposeColor.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Payment method $payment_mode",
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "₹$grand_total/-",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Text(
                text = "Full Paid",
                color = ComposeColor.Green, // Green text color
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .background(ComposeColor(0xFF509A4A), RoundedCornerShape(16.dp)) // Light green background with rounded corners
                    .padding(horizontal = 12.dp, vertical = 6.dp) // Padding inside the background
            )
        }
    }
}
