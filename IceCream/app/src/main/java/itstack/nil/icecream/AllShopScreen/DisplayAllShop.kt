package itstack.nil.icecream.AllShopScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.style.TextOverflow
import itstack.nil.icecream.Categories.shopCategories
import itstack.nil.icecream.Responses.AllShopData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayAllShop(navController: NavController, context: Context) {
    val allShopHandler = remember { AllShopHandler(context) }
    var shopList by remember { mutableStateOf<List<AllShopData>?>(null) }
    var filteredShopList by remember { mutableStateOf<List<AllShopData>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val localContext = LocalContext.current

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                allShopHandler.fetchAllShop(
                    onResult = { data ->
                        shopList = data
                        filteredShopList = data // Initialize filtered list
                        isLoading = false
                    },
                    onError = { error ->
                        isLoading = false
                        Toast.makeText(localContext, error, Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }

    // UI
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (shopList.isNullOrEmpty()) {
            Toast.makeText(context, "No shops available.", Toast.LENGTH_SHORT).show()
        } else {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Search Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 8.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        placeholder = {
                            Text(
                                text = "Search by shop name, owner name, or phone number",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search Icon",
                                tint = Color.Gray
                            )
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color.Gray,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedPlaceholderColor = Color.Gray
                        )
                    )

                    // Search Button
                    IconButton(
                        onClick = {
                            // Perform filtering based on the search query
                            filteredShopList = shopList?.filter { shop ->
                                shop.shop_name.contains(searchQuery, ignoreCase = true) ||
                                        shop.owner_name.contains(searchQuery, ignoreCase = true) ||
                                        shop.whatsapp_number.contains(searchQuery, ignoreCase = true)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray
                        )
                    }
                }

                // Shop List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                ) {
                    items(filteredShopList ?: emptyList()) { shop ->
                        ShopTemplate(
                            shopName = shop.shop_name,
                            ownerName = shop.owner_name,
                            location = shop.address,
                            phno = shop.whatsapp_number,
                            navController = navController
                        )
                    }
                }
            }
        }

        // Floating Action Button (FAB)
        FloatingActionButton(
            onClick = {
                navController.navigate("addShop")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 48.dp, end = 16.dp),
            containerColor = Color.Blue
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Shop",
                tint = Color.White
            )
        }
    }
} 