package com.shist.data.model

import com.google.gson.annotations.SerializedName

/*
data class StructuralObjectItemJson(
    @SerializedName("id") var id: String?,
    @SerializedName("subdivision") var subdivision: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("website") var website: String?,
    @SerializedName("buildingId") var buildingId: String?,
    @SerializedName("category") var category: StructuralObjectItemCategoryJson?,
    @SerializedName("icon") var icon: StructuralObjectItemIconJson?
)*/

data class StructuralObjectItemJson(
    @SerializedName("id") var id: String?,
    @SerializedName("subdivision") var subdivision: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("website") var website: String?,
    @SerializedName("category") var category: StructuralObjectItemCategoryJson?,
    @SerializedName("logo_path") var logoPath: String?,
    @SerializedName("location_photos") var locationPhotos: List<BuildingItemImageJson?>?
)