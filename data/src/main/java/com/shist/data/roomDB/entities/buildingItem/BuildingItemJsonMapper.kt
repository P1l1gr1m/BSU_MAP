package com.shist.data.roomDB.entities.buildingItem

import android.util.Log
import com.shist.data.model.BuildingItemAddressCoordinatesJson
import com.shist.data.model.BuildingItemAddressJson
import com.shist.data.model.BuildingItemImageJson
import com.shist.data.model.BuildingItemJson
import com.shist.data.roomDB.entities.buildingItem.adressItem.AddressItemJsonMapper
import com.shist.data.roomDB.entities.buildingItem.buildingItemImage.BuildingItemImageEntityDB
import com.shist.data.roomDB.entities.buildingItem.buildingItemImage.BuildingItemImageJsonMapper
import com.shist.data.roomDB.entities.buildingItem.scientistItem.ScientistItemJsonMapper
import com.shist.data.roomDB.entities.buildingItem.structuralObjectItem.StructuralObjectItemJsonMapper

// This mapper converts a JSON entity to a database entity
class BuildingItemJsonMapper {
    fun fromJsonToRoomDB(itemJson: BuildingItemJson?): BuildingItemDB? {
        if (itemJson == null) {
            return null
        } else {
            val type: String?
            val markerPath: String?

            if (itemJson.type == null) {
                type = null
                markerPath = null
            } else {
                type = itemJson.type!!.type
                markerPath = itemJson.type!!.markerPath
            }

            val structuralObjectsJson = itemJson.structuralObjects

            val structuralObjectsDB = structuralObjectsJson?.map {
                StructuralObjectItemJsonMapper().fromJsonToRoomDB(it, itemJson.id)
                    ?.structuralItemsEntityDB
            } ?: emptyList()

            val buildingItemImagesDB = itemJson.photos?.map {
                BuildingItemImageJsonMapper().fromJsonToRoomDB(it, itemJson.id)
            } ?: emptyList()

            val iconsDB = structuralObjectsJson?.map {
                StructuralObjectItemJsonMapper().fromJsonToRoomDB(it, itemJson.id)?.icon
            } ?: emptyList()


            val scientistDB = if (itemJson.scientist != null) {
                ScientistItemJsonMapper().fromJsonToRoomDB(itemJson.scientist, itemJson.id)
            } else {
                null
            }

            val addressDB =
                AddressItemJsonMapper().fromJsonToRoomDB(itemJson.address, itemJson.id)

            return BuildingItemDB(
                BuildingItemEntityDB(
                    itemJson.id ?: "",
                    itemJson.inventoryUsrreNumber ?: "",
                    itemJson.name ?: "",
                    itemJson.isModern,
                    type,
                    markerPath,
                    itemJson.order,
                    scientistDB?.id
                ),
                structuralObjectsDB,
                buildingItemImagesDB,
                iconsDB,
                addressDB,
                scientistDB
            )
        }
    }
}