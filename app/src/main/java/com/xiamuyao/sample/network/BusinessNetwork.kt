package com.xiamuyao.sample.network

import com.xiamuyao.sample.network.api.BusinessService


class BusinessNetwork {

    private val placeService = ServiceCreator.create(BusinessService::class.java)


    suspend fun fetchProvinceList() = placeService.getProvinces()


    companion object {

        private var business: BusinessNetwork? = null

        fun getInstance(): BusinessNetwork {
            if (business == null) {
                synchronized(BusinessNetwork::class.java) {
                    if (business == null) {
                        business =
                            BusinessNetwork()
                    }
                }
            }
            return business!!
        }

    }

}