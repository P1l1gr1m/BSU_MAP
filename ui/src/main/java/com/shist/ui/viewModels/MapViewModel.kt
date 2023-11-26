package com.shist.ui.viewModels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shist.domain.BuildingItem
import com.shist.domain.DataRepository
import com.shist.domain.ScientistItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException

class MapViewModel (application: Application)
    : AndroidViewModel(application), KoinComponent {

    private val dataRepository: DataRepository by inject()

    val allBuildingsFlow: Flow<List<BuildingItem>> = dataRepository.getAllBuildings()
    val historicalBuildingsFlow: Flow<List<BuildingItem>> = dataRepository.getHistoricalBuildings()
    val administrativeBuildingsFlow: Flow<List<BuildingItem>> = dataRepository.getAdministrativeBuildings()
    val educationalBuildingsFlow: Flow<List<BuildingItem>> = dataRepository.getEducationalBuildings()
    val multifunctionalBuildingsFlow: Flow<List<BuildingItem>> = dataRepository.getMultifunctionalBuildings()
    val dormitoryBuildingsFlow: Flow<List<BuildingItem>> = dataRepository.getDormitoryBuildings()
    val scientistLocationsFlow: Flow<List<BuildingItem>> = dataRepository.getScientistLocations("Владимир Иванович Пичета")

    var scientists: MutableList<ScientistItem?>? = mutableListOf()

    val state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.IDLE)

    private fun isConnectedToInternet() : Boolean {
        val context = getApplication<Application>().applicationContext
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    // This function tries to update old data to actual data if possible
    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            state.value = LoadState.LOADING
            try {
                dataRepository.loadData()
                state.value = LoadState.SUCCESS
            } catch (e: Throwable) {
                if (!isConnectedToInternet() && e is IOException) {
                    state.value = LoadState.INTERNET_ERROR
                }
                else if (e is NullPointerException) {
                    state.value = LoadState.EMPTY_DATA_ERROR
                }
                else {
                    state.value = LoadState.UNKNOWN_ERROR
                }
            }
        }
    }

}