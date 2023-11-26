package com.shist.data.roomDB.entities.buildingItem.scientistItem

import androidx.room.*
import com.shist.data.roomDB.entities.buildingItem.adressItem.AddressItemEntityDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ScientistItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneScientistItem(item: ScientistItemEntityDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScientistItemsList(items: List<ScientistItemEntityDB>): List<Long>

    @Update
    suspend fun updateOneScientistItem(item: ScientistItemEntityDB)

    @Update
    suspend fun updateAllScientistItems(items: List<ScientistItemEntityDB>)

    @Delete
    suspend fun deleteOneScientistItem(item: ScientistItemEntityDB)

    @Delete
    suspend fun deleteAllScientistItems(items: List<ScientistItemEntityDB>)

    @Query("SELECT * FROM scientistItem")
    fun getAllScientistItems(): Flow<List<ScientistItemEntityDB>>

    @Query("SELECT * FROM scientistItem WHERE id = :neededId")
    fun getScientistItemById(neededId: String): Flow<ScientistItemEntityDB>

}