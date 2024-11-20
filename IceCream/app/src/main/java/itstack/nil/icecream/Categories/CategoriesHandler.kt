package itstack.nil.icecream.Categories

import android.content.Context
import itstack.nil.icecream.Responses.AllOrderData
import itstack.nil.icecream.Responses.AllOrderResponse
import itstack.nil.icecream.Responses.CategoriesData
import itstack.nil.icecream.Responses.CategoriesResponses
import itstack.nil.icecream.RetrofitProps.ApiInterface
import itstack.nil.icecream.RetrofitProps.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesHandler(private val context: Context)  {
    private val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    fun fetchCategories(onResult: (List<CategoriesData>) -> Unit, onError: (String) -> Unit) {
        val token = sharedPreferences.getString("user_token", null)
        if (token.isNullOrEmpty()) {
            onError("Error: User token is missing. Please log in again.")
            return
        }

        val apiService = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        val call = apiService.getAllCategories("Bearer $token")

        call.enqueue(object : Callback<CategoriesResponses> {
            override fun onResponse(
                call: Call<CategoriesResponses>,
                response: Response<CategoriesResponses>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { allCategoriesResponse ->
                        onResult(allCategoriesResponse.data)
                    }
                } else {
                    onError("Failed to load data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CategoriesResponses>, t: Throwable) {
                onError("Error: ${t.message}")
            }
        })
    }
}