package test.vodovoz.listview.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("ID") val id: Long,
    //BASE_URL - '\' + picture
    @SerializedName("DETAIL_PICTURE") val picture: String,
    @SerializedName("EXTENDED_PRICE") val prices: List<Price>
)
