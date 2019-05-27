package com.xiamuyao.sample.model.repository

import com.xiamuyao.sample.network.BusinessNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRepository(private val network: BusinessNetwork) {

    suspend fun getProvinceList() = withContext(Dispatchers.IO) {
        return@withContext network.fetchProvinceList()
    }

    companion object {

        private var instance: PlaceRepository? = null

        fun getInstance(network: BusinessNetwork): PlaceRepository {
            if (instance == null) {
                synchronized(PlaceRepository::class.java) {
                    if (instance == null) {
                        instance = PlaceRepository(network)
                    }
                }
            }
            return instance!!
        }
    }
}