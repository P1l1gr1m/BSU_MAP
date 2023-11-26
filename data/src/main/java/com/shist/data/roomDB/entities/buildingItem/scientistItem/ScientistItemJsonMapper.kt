package com.shist.data.roomDB.entities.buildingItem.scientistItem

import com.shist.data.model.ScientistItemJson

class ScientistItemJsonMapper {

    fun fromJsonToRoomDB(itemJson: ScientistItemJson?, buildingItemId: String?): ScientistItemEntityDB? {

        return if (itemJson == null) {
            null
        } else {
            ScientistItemEntityDB(
                itemJson.id!!,
                itemJson.fullName,
                itemJson.imagePath,
                itemJson.description,
                buildingItemId
            )
        }
    }
}