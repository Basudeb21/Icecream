package itstack.nil.icecream.Responses

data class AllShopData(
    val address: String,
    val created_at: String,
    val freezer: String,
    val freezer_capacity: String,
    val freezer_serial_number: String,
    val id: Int,
    val is_visible: Int,
    val owner_name: String,
    val shop_name: String,
    val updated_at: String,
    val whatsapp_number: String
)