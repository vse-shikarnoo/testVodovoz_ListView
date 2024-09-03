package test.vodovoz.listview.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("ID") val id: Long,
    @SerializedName("NAME") val name: String,
    @SerializedName("data") val productsData: List<Product>,
    var isCurrent: Boolean = false
)
