package com.shist.data.model

import com.google.gson.annotations.SerializedName

/*
data class BuildingItemJson(
    @SerializedName("structuralObjects") val structuralObjects: List<StructuralObjectItemJson?>?,
    @SerializedName("id") var id: String?,
    @SerializedName("inventoryUsrreNumber") var inventoryUsrreNumber: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("isModern") var isModern: String?,
    @SerializedName("address") var address: BuildingItemAddressJson?,
    @SerializedName("type") var type: BuildingItemTypeJson?
)*/

data class BuildingItemJson(
    @SerializedName("id") var id: String?,
    @SerializedName("latitude") var latitude: String?,
    @SerializedName("longitude") var longitude: String?,
    @SerializedName("address") var address: BuildingItemAddressJson?,
    @SerializedName("name") var name: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("type") var type: BuildingItemTypeJson?,
    @SerializedName("scientist") var scientist: ScientistItemJson,
    @SerializedName("order") var order: String?,
    @SerializedName("photos") var photos: List<BuildingItemImageJson?>?,
    @SerializedName("structural_objects") val structuralObjects: List<StructuralObjectItemJson?>?,
    @SerializedName("inventoryUsrreNumber") var inventoryUsrreNumber: String?,
    @SerializedName("isModern") var isModern: Boolean?,
    @SerializedName("coordinates") var coordinates: BuildingItemAddressCoordinatesJson?
)
