package test.vodovoz.listview

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("ID") val id: Int,
    //BASE_URL - '\' + picture
    @SerializedName("DETAIL_PICTURE") val picture: String,
    @SerializedName("EXTENDED_PRICE") val prices: List<Price>
)
