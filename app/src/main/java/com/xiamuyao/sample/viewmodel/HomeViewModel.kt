package com.xiamuyao.sample.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.xiamuyao.sample.model.bean.Dataa
import com.xiamuyao.sample.model.repository.PlaceRepository
import com.xiamuyao.ulanbator.base.BaseViewModel
import com.xiamuyao.ulanbator.extension.businessHandler
import com.xiamuyao.ulanbator.utlis.LibConstant
import org.kodein.di.generic.instance

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val repository: PlaceRepository by instance()

    var messag = MutableLiveData<String>()

    var itemList = ObservableArrayList<Dataa>()

    override fun initData() {

        launch {
            messag.value = "1"

            itemList.addAll(businessHandler(repository.getProvinceList()).data)
        }

    }

}