package com.shist.data.repository.mappers

import android.util.Log
import com.shist.data.roomDB.entities.buildingItem.scientistItem.ScientistItemEntityDB
import com.shist.domain.ScientistItem

class ScientistItemDBMapper {

    fun fromDBToDomain(item: ScientistItemEntityDB?): ScientistItem? {
        return if (item == null) {
            null
        } else {
            ScientistItem(
                item.id,
                item.fullName,
                item.imagePath,
                item.description
            )
        }
    }
}