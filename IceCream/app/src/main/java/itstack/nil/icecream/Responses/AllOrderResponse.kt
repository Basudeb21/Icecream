package itstack.nil.icecream.Responses

data class AllOrderResponse(
    val status: String,
    val data: List<AllOrderData>
)