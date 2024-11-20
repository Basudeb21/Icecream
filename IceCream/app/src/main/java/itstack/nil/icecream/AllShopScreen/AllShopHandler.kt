package itstack.nil.icecream.AllShopScreen

import android.content.Context
import itstack.nil.icecream.Responses.AllOrderData
import itstack.nil.icecream.Responses.AllOrderResponse
import itstack.nil.icecream.Responses.AllShopData
import itstack.nil.icecream.Responses.GetAllShopResponses
import itstack.nil.icecream.RetrofitProps.ApiInterface
import itstack.nil.icecream.RetrofitProps.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllShopHandler(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)

    fun fetchAllShop(onResult: (List<AllShopData>) -> Unit, onError: (String) -> Unit) {
        val token = sharedPreferences.getString("user_token", null)
        if (token.isNullOrEmpty()) {
            onError("Error: User token is missing. Please log in again.")
            return
        }

        val apiService = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        val call = apiService.getAllShop("Bearer $token")

        call.enqueue(object : Callback<GetAllShopResponses> {
            override fun onResponse(
                call: Call<GetAllShopResponses>,
                response: Response<GetAllShopResponses>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { allShopResponse ->
                        onResult(allShopResponse.data)
                    } ?: onError("Failed to load data: Empty response body")
                } else {
                    onError("Failed to load data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetAllShopResponses>, t: Throwable) {
                onError("Error: ${t.message}")
            }
        })
    }


}