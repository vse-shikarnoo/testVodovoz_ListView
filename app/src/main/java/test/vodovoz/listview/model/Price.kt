package test.vodovoz.listview.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("PRICE") val price: Int,
    @SerializedName("OLD_PRICE") val oldPrice: Int,
    @SerializedName("QUANTITY_FROM") val quantityFrom: Int,
    @SerializedName("QUANTITY_TO") val quantityTo: Int,
)
