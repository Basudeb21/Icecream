package itstack.nil.icecream.RetrofitProps

import itstack.nil.icecream.Responses.AllOrderResponse
import itstack.nil.icecream.Responses.CategoriesResponses
import itstack.nil.icecream.Responses.GetAllShopResponses
import itstack.nil.icecream.Responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("inventory/api/login")
    fun userLogin(
        @Field("phone") phone: String,
        @Field("password") password: String

    ): Call<LoginResponse>

    @GET("inventory/api/get-all-orders")
    fun getOrderData(
        @Header("Authorization") token: String
    ): Call<AllOrderResponse> // Expecting the root object here

    @GET("inventory/api/get-all-shops")
    fun getAllShop(
        @Header("Authorization") token: String
    ): Call<GetAllShopResponses>

    @GET("inventory/api/get-gategory")
    fun getAllCategories(
        @Header("Authorization") token: String
    ): Call<CategoriesResponses>

    @POST("inventory/api/add-new-shop")
    fun addNewShop(
        @Field("shop_name") shop_name: String,
        @Field("owner_name") owner_name: String,
        @Field("whatsapp_number") whatsapp_number: String,
        @Field("address") address: String
    ): Call<CategoriesResponses>
}