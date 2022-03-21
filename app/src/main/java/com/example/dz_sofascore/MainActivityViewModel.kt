package com.example.dz_sofascore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dz_sofascore.model.F1Driver

class MainActivityViewModel : ViewModel() {

    val listOfDrivers = MutableLiveData<ArrayList<F1Driver>>()
    val list = ArrayList<F1Driver>()

    fun f1DriverAdd(driver: F1Driver) {
        list.add(driver)
        listOfDrivers.value = list
    }
}
