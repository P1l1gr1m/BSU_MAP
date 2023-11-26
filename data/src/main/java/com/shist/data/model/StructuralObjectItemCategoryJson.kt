package com.shist.data.model

import com.google.gson.annotations.SerializedName

data class StructuralObjectItemCategoryJson(
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String?
)