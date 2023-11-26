package com.shist.data.repository

import android.content.Context
import android.util.Log
import com.shist.data.model.BuildingItemJson
import com.shist.data.repository.mappers.AddressItemDBMapper
import com.shist.data.repository.mappers.BuildingItemDBMapper
import com.shist.data.repository.mappers.IconItemDBMapper
import com.shist.data.repository.mappers.StructuralObjectItemDBMapper
import com.shist.data.retrofit.MapDataApi
import com.shist.data.roomDB.BuildingItemsDatabase
import com.shist.data.roomDB.entities.buildingItem.BuildingItemDB
import com.shist.data.roomDB.entities.buildingItem.BuildingItemJsonMapper
import com.shist.data.roomDB.entities.buildingItem.adressItem.AddressItemJsonMapper
import com.shist.data.roomDB.entities.buildingItem.structuralObjectItem.StructuralObjectItemJsonMapper
import com.shist.data.roomDB.entities.buildingItem.structuralObjectItem.iconItem.IconItemJsonMapper
import com.shist.domain.BuildingItem
import com.shist.domain.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// This is implementation of repository based on its interface
class DataRepositoryImpl(private val buildingItemsDatabase: BuildingItemsDatabase,
                         private val service: MapDataApi,
                         private val buildingItemJsonMapper: BuildingItemJsonMapper,
                         private val buildingItemDBMapper: BuildingItemDBMapper,
                         private val structuralObjectItemJsonMapper: StructuralObjectItemJsonMapper,
                         private val structuralObjectItemDBMapper: StructuralObjectItemDBMapper,
                         private val addressItemJsonMapper: AddressItemJsonMapper,
                         private val addressItemDBMapper: AddressItemDBMapper,
                         private val iconItemJsonMapper: IconItemJsonMapper,
                         private val iconItemDBMapper: IconItemDBMapper,
                         private val context: Context
) : DataRepository {

    // This function checks if given item has ID or not
    private fun isItemWithID(itemJson: BuildingItemJson?): Boolean {
        return if (itemJson == null) {
            false
        } else {
            itemJson.id != null
        }
    }

    // This function checks if given item is empty or not
    private fun isItemNotEmpty(item: BuildingItemDB?): Boolean {
        return if (item == null)
            false
        else {
            item.buildingItemEntityDB.inventoryUsrreNumber != null ||
                    item.buildingItemEntityDB.name != null ||
                    item.buildingItemEntityDB.isModern != null ||
                    item.buildingItemEntityDB.type != null ||
                    item.buildingItemEntityDB.markerPath != null
        }
    }

    // This function is needed to get actual data from server
    override suspend fun loadData() {
        try {
            val items = service.getAllBuildings()
                ?.filter { isItemWithID(it) } // Clean list from items with null id
                ?.mapNotNull {
                    BuildingItemJsonMapper().fromJsonToRoomDB(it)
                } // Use mapNotNull to filter out null values
                ?.filter { isItemNotEmpty(it) } // Clean DB from items with empty data

            // Log the items to see what's being processed
            items?.forEach { item ->
                Log.d("BuildingItemLoader", "Item: $item")
            }

            buildingItemsDatabase.buildingItemsDao().insertBuildingItemsList(items!!)
        } catch (e: Throwable) {
            Log.e("BuildingItemLoader", "Error loading data", e)
            throw e
        }
    }

    // This function return list of all items that are in local database
    override fun getAllBuildings(): Flow<List<BuildingItem>> {
        try {
            return buildingItemsDatabase.buildingItemsDao().getAllBuildingItems().map { list ->
                list.map { BuildingItemDBMapper().fromDBToDomain(it)!! }.filter { buildingItem ->
                    buildingItem.type != TYPE_SCIENTIST_LOCATION } }
        } catch (e: Throwable) {
            throw NullPointerException("Error while getting building items: " +
            "Some item is nullable!\n" + e.message)
        }
    }

    override fun getHistoricalBuildings(): Flow<List<BuildingItem>> {
        try {
            return buildingItemsDatabase.buildingItemsDao().getAllBuildingItems().map { list ->
                list.map { BuildingItemDBMapper().fromDBToDomain(it)!! }.filter { buildingItem -> buildingItem.type == TYPE_HISTORICAL } }
        } catch (e: Throwable) {
            throw NullPointerException("Error while getting building items: " +
                    "Some item is nullable!\n" + e.message)
        }
    }

    override fun getAdministrativeBuildings(): Flow<List<BuildingItem>> {
        try {
            return buildingItemsDatabase.buildingItemsDao().getAllBuildingItems().map { list ->
                list.map { BuildingItemDBMapper().fromDBToDomain(it)!! }.filter { buildingItem -> buildingItem.type == TYPE_ADMINISTRATIVE } }
        } catch (e: Throwable) {
            throw NullPointerException("Error while getting building items: " +
                    "Some item is nullable!\n" + e.message)
        }
    }

    override fun getEducationalBuildings(): Flow<List<BuildingItem>> {
        try {
            return buildingItemsDatabase.buildingItemsDao().getAllBuildingItems().map { list ->
                list.map { BuildingItemDBMapper().fromDBToDomain(it)!! }.filter { buildingItem -> buildingItem.type == TYPE_EDUCATIONAL } }
        } catch (e: Throwable) {
            throw NullPointerException("Error while getting building items: " +
                    "Some item is nullable!\n" + e.message)
        }
    }

    override fun getDormitoryBuildings(): Flow<List<BuildingItem>> {
        try {
            return buildingItemsDatabase.buildingItemsDao().getAllBuildingItems().map { list ->
                list.map { BuildingItemDBMapper().fromDBToDomain(it)!! }.filter { buildingItem -> buildingItem.type == TYPE_DORMITORY } }
        } catch (e: Throwable) {
            throw NullPointerException("Error while getting building items: " +
                    "Some item is nullable!\n" + e.message)
        }
    }

    override fun getMultifunctionalBuildings(): Flow<List<BuildingItem>> {
        try {
            return buildingItemsDatabase.buildingItemsDao().getAllBuildingItems().map { list ->
                list.map { BuildingItemDBMapper().fromDBToDomain(it)!! }.filter { buildingItem -> buildingItem.type == TYPE_MULTIFUNCTIONAL } }
        } catch (e: Throwable) {
            throw NullPointerException("Error while getting building items: " +
                    "Some item is nullable!\n" + e.message)
        }
    }

    override fun getScientistLocations(scientistName: String): Flow<List<BuildingItem>> {
        try {
            return buildingItemsDatabase.buildingItemsDao().getAllBuildingItems().map { list ->
                list.map { BuildingItemDBMapper().fromDBToDomain(it)!! }.filter { buildingItem -> buildingItem.type == TYPE_SCIENTIST_LOCATION } }
        } catch (e: Throwable) {
            throw NullPointerException("Error while getting building items: " +
                    "Some item is nullable!\n" + e.message)
        }
    }

    companion object {
        private const val TYPE_HISTORICAL = "историческое"
        private const val TYPE_EDUCATIONAL = "учебное"
        private const val TYPE_ADMINISTRATIVE = "административное"
        private const val TYPE_DORMITORY = "общежитие"
        private const val TYPE_MULTIFUNCTIONAL = "многофункциональное"
        private const val TYPE_SCIENTIST_LOCATION = "scientist_location"
    }

}