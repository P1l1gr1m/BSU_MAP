package com.shist.data.roomDB.entities.buildingItem.structuralObjectItem

import com.shist.data.model.StructuralObjectItemIconJson
import com.shist.data.model.StructuralObjectItemJson
import com.shist.data.roomDB.entities.buildingItem.structuralObjectItem.iconItem.IconItemJsonMapper

// This mapper converts a JSON entity to a database entity
class StructuralObjectItemJsonMapper {

    fun fromJsonToRoomDB(itemJson: StructuralObjectItemJson?, buildingId: String?) : StructuralObjectItemDB?
    {
        if (itemJson == null) {
            return null
        }
        else {
            val categoryName: String? = if (itemJson.category == null) {
                null
            } else {
                itemJson.category!!.name
            }

            return StructuralObjectItemDB(
                StructuralObjectItemEntityDB(
                    itemJson.id!!,
                    itemJson.subdivision,
                    itemJson.description,
                    itemJson.website,
                    /*itemJson.buildingId*/buildingId,
                    categoryName
                ),
                IconItemJsonMapper()
                    .fromJsonToRoomDB(StructuralObjectItemIconJson(itemJson.id, itemJson.subdivision, itemJson.logoPath), itemJson.id, /*itemJson.buildingId*/buildingId)!!
            )
        }
    }

}