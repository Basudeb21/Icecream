package itstack.nil.icecream.LoginScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import itstack.nil.icecream.R
import itstack.nil.icecream.ui.theme.LoginBG
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun loginScreen(navController: NavController){
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
            modifier = Modifier.fillMaxSize()
    ) {
        Row (modifier = Modifier.fillMaxWidth()
            .background(LoginBG)
            .weight(.6f)){
            Image(painter = painterResource(R.drawable.icecream),
                    contentDescription = null,
                modifier = Modifier.fillMaxSize()
                )
        }
        Row(modifier = Modifier.fillMaxWidth()
            .weight(.4f)) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(top = 50.dp)
            ) {
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    placeholder = { Text("Enter phone number") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Person Icon") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    placeholder = { Text("Enter password") },
                    leadingIcon = { Image(painter = painterResource(R.drawable.password), contentDescription = "Person Icon") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
                Button(
                    onClick = {
                        when {
                            phone.isEmpty() -> {
                                Toast.makeText(context, "Phone number is required!", Toast.LENGTH_SHORT).show()
                            }
                            !phone.matches("\\d{10}".toRegex()) -> {
                                Toast.makeText(context, "Enter a valid 10-digit phone number!", Toast.LENGTH_SHORT).show()
                            }
                            password.isEmpty() -> {
                                Toast.makeText(context, "Password is required!", Toast.LENGTH_SHORT).show()
                            }
                            password.length < 6 -> {
                                Toast.makeText(context, "Password must be at least 6 characters!", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                val loginHandler = LoginHandler(phone, password, context)
                                loginHandler.logMeIn(navController)
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 10.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send Icon", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Login", color = Color.White)
                }


            }
        }
    }
}
