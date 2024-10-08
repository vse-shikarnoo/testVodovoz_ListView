package test.vodovoz.listview.model

import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("TOVARY") val tovary: List<Category>
)
