package com.shist.data.model

import com.google.gson.annotations.SerializedName

data class BuildingItemTypeJson(
    @SerializedName("type_name") var type: String?,
    @SerializedName("marker_path") var markerPath: String?
)