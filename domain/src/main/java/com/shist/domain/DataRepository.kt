package com.shist.domain

import kotlinx.coroutines.flow.Flow

// This is interface for repository, here you should declare all needed methods and functions
interface DataRepository {

    suspend fun loadData()

    fun getAllBuildings(): Flow<List<BuildingItem>>

    fun getHistoricalBuildings(): Flow<List<BuildingItem>>

    fun getAdministrativeBuildings(): Flow<List<BuildingItem>>

    fun getEducationalBuildings(): Flow<List<BuildingItem>>

    fun getDormitoryBuildings(): Flow<List<BuildingItem>>

    fun getMultifunctionalBuildings(): Flow<List<BuildingItem>>

    fun getScientistLocations(scientistName: String): Flow<List<BuildingItem>>
}