package com.shist.data.model

import com.google.gson.annotations.SerializedName

data class BuildingItemImageJson (
    @SerializedName("id") val id: String?,
    @SerializedName("image_path") var imagePath: String?,
    @SerializedName("image_description") var description: String?
)