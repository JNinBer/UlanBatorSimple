package com.xiamuyao.sample.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.xiamuyao.sample.model.repository.PlaceRepository
import com.xiamuyao.ulanbator.base.BaseViewModel
import com.xiamuyao.ulanbator.extension.businessHandler
import org.kodein.di.generic.instance

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val repository: PlaceRepository by instance()

    var messag = MutableLiveData<String>()

    override fun initData() {

        launch {
            messag.value = businessHandler(repository.getProvinceList()).data.toString()
        }

    }

}