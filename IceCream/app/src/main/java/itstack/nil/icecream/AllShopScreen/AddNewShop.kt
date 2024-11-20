package itstack.nil.icecream.AllShopScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itstack.nil.icecream.R

@Composable
@Preview(showBackground = true)
fun AddNewShop(){
    var shop_name by remember { mutableStateOf("") }
    var owner_name by remember { mutableStateOf("") }
    var wp_no by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 35.dp)
    ) {
        OutlinedTextField(
            value = shop_name,
            onValueChange = { shop_name = it },
            label = { Text("Shop Name") },
            placeholder = { Text("Enter shop name") },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp), // Makes the field as wide as the screen
            singleLine = true,
            leadingIcon = { Icon(
                painter = painterResource(R.drawable.store), contentDescription = null
            ) }
        )

        OutlinedTextField(
            value = owner_name,
            onValueChange = { owner_name = it },
            label = { Text("Owner Name") },
            placeholder = { Text("Enter shop owner name") },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp), // Makes the field as wide as the screen
            singleLine = true,
            leadingIcon = { Icon(
                Icons.Default.Person, contentDescription = null
            ) }
        )

        OutlinedTextField(
            value = wp_no,
            onValueChange = { wp_no = it },
            label = { Text("Whatsapp Number") },
            placeholder = { Text("Enter whatsapp number") },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp), // Makes the field as wide as the screen
            singleLine = true,
            leadingIcon = { Icon(
                Icons.Default.Call, contentDescription = null
            ) }
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Shop Location") },
            placeholder = { Text("Enter shop location") },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp), // Makes the field as wide as the screen
            singleLine = true,
            leadingIcon = { Icon(
                Icons.Default.LocationOn, contentDescription = null
            ) }
        )
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth() // Makes the button full width
                .padding(16.dp), // Optional padding to give it space from edges
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue) // Optional styling
        ) {
            Text(
                text = "Add Data",
                fontSize = 18.sp,
                color = Color.White, // White text color
                modifier = Modifier.padding(vertical = 10.dp) // Padding inside the button
            )
        }



    }

}