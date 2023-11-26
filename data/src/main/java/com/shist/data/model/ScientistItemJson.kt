package com.shist.data.model

import com.google.gson.annotations.SerializedName

data class ScientistItemJson(
    @SerializedName("id") var id: String?,
    @SerializedName("fullname") var fullName: String?,
    @SerializedName("image_path") var imagePath: String?,
    @SerializedName("description") var description: String?
)