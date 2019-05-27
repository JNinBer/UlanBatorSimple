package com.xiamuyao.sample.network.api

import com.xiamuyao.sample.model.bean.Dataa
import com.xiamuyao.ulanbator.net.BaseResponse
import retrofit2.http.GET

interface BusinessService {

    @GET("chapters/json")
    suspend fun getProvinces(): BaseResponse<List<Dataa>>

}