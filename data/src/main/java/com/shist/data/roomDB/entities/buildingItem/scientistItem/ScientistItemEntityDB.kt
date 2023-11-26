package com.shist.data.roomDB.entities.buildingItem.scientistItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scientistItem")
data class ScientistItemEntityDB(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "fullName")
    val fullName: String?,

    @ColumnInfo(name = "imagePath")
    val imagePath: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "buildingItemId")
    val buildingItemId: String?
)