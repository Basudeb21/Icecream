package itstack.nil.icecream.GetAllOrderScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import itstack.nil.icecream.Responses.AllOrderData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun displayAllOrder(navController: NavController) {
    var orders by remember { mutableStateOf<List<AllOrderData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val orderHandler = OrderHandler(context)

    LaunchedEffect(Unit) {
        orderHandler.fetchOrders(
            onResult = { data ->
                orders = data
                isLoading = false
            },
            onError = { error ->
                errorMessage = error
                isLoading = false
            }
        )
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Red, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = errorMessage ?: "Unknown error",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()
            .padding(top = 30.dp)) {
            items(orders.size) { index ->
                val order = orders[index]

                allOrderTemplate(
                    updated_at = formatDate(order.updated_at),
                    shop_name = order.shop_name,
                    payment_mode = order.payment_mode,
                    grand_total = order.grand_total
                )
            }
        }
    }
}

fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM\ndd\nyyyy", Locale.getDefault())
        val date: Date = inputFormat.parse(dateString) ?: return ""
        outputFormat.format(date)
    } catch (e: Exception) {
        "Invalid\nDate"
    }
}
