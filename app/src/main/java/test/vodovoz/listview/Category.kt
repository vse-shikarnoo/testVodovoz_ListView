package test.vodovoz.listview

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("ID") val id: Int,
    @SerializedName("NAME") val name: String,
    @SerializedName("data") val productsData: List<Product>
)
