package itstack.nil.icecream.GetAllOrderScreen

import android.content.Context
import itstack.nil.icecream.Responses.AllOrderData
import itstack.nil.icecream.Responses.AllOrderResponse
import itstack.nil.icecream.RetrofitProps.ApiInterface
import itstack.nil.icecream.RetrofitProps.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderHandler(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)

    fun fetchOrders(onResult: (List<AllOrderData>) -> Unit, onError: (String) -> Unit) {
        val token = sharedPreferences.getString("user_token", null)
        if (token.isNullOrEmpty()) {
            onError("Error: User token is missing. Please log in again.")
            return
        }

        val apiService = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        val call = apiService.getOrderData("Bearer $token")

        call.enqueue(object : Callback<AllOrderResponse> {
            override fun onResponse(
                call: Call<AllOrderResponse>,
                response: Response<AllOrderResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { allOrderResponse ->
                        onResult(allOrderResponse.data)
                    }
                } else {
                    onError("Failed to load data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AllOrderResponse>, t: Throwable) {
                onError("Error: ${t.message}")
            }
        })
    }
}

