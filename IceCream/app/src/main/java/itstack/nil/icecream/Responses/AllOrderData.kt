package itstack.nil.icecream.Responses

data class AllOrderData(
    val cash: String,
    val created_at: String,
    val discount: String,
    val grand_total: String,
    val gst: String,
    val id: Int,
    val is_paid: Int,
    val online: String,
    val order_number: String,
    val owner_name: String,
    val payment_mode: String,
    val salesman_id: Int,
    val shop_id: Int,
    val shop_name: String,
    val sub_total: String,
    val total_product_count: Int,
    val updated_at: String,
    val whatsapp_number: String
)