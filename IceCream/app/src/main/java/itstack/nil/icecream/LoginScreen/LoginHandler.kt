package itstack.nil.icecream.LoginScreen

import android.content.Context
import android.content.Intent
import itstack.nil.icecream.RetrofitProps.ApiInterface
import itstack.nil.icecream.RetrofitProps.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import itstack.nil.icecream.Responses.LoginResponse

class LoginHandler(private val phone: String, private val password: String, private val context: Context) {
    val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)

    // Login function
    fun logMeIn(navController: NavController) {
        val apiService = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        val call = apiService.userLogin(phone, password)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val token = response.body()?.token
                    if (!token.isNullOrEmpty()) {
                        saveTokenToPreferences(token)
                        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                        navController.navigate("app") // navigate to the app screen
                    } else {
                        Toast.makeText(context, "Login failed: Token is empty!", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Login failed: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginHandler", "Error: ${t.message}")
                Toast.makeText(context, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Save the token to shared preferences
    private fun saveTokenToPreferences(token: String) {
        sharedPreferences.edit().putString("user_token", token).apply()
    }
}
