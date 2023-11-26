package com.shist.data.roomDB.entities.buildingItem.buildingItemImage

import com.shist.data.model.BuildingItemImageJson

// This mapper converts a JSON entity to a database entity
class BuildingItemImageJsonMapper {

    fun fromJsonToRoomDB(itemJson: BuildingItemImageJson?, buildingId: String?) : BuildingItemImageEntityDB?
    {
        return if (itemJson == null) {
            null
        } else {
            BuildingItemImageEntityDB((itemJson.id!! + "/" + itemJson.imagePath),
                itemJson.description,
                itemJson.imagePath,
                buildingId)
        }
    }

}