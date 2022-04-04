package com.example.dz_sofascore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dz_sofascore.model.F1Driver

class MainActivityViewModel : ViewModel() {

    val listOfDrivers = MutableLiveData<ArrayList<F1Driver>>()

    init {
        listOfDrivers.value = arrayListOf(
            F1Driver("Michael", "Schumacher", 40, "M", "Ferrari", "German", "Monte Carlo, Monaco", "Kimi Raikkonen", "Ferrari", "https://www.theplace2.ru/archive/michael_schumacher/img/5-33.jpg"),
            F1Driver("Daniel", "Ricciardo", 32, "M", "McLaren", "Australian", "Spa-Francorchamps, Belgium", "Lando Norris", "Mercedes", "https://www.formula1.com/content/fom-website/en/drivers/daniel-ricciardo/_jcr_content/image.img.640.medium.jpg/1646818924510.jpg"),
            F1Driver("Susie", "Wolff", 39, "F", "Mercedes", "British", "Monza, Italy", "Toto Wolff", "Mercedes", "https://imgresizer.eurosport.com/unsafe/1200x0/filters:format(jpeg):focal(1262x312:1264x310)/origin-imgresizer.eurosport.com/2014/07/01/1269901-27389720-2560-1440.jpg")
        )
    }

    fun f1DriverAdd(driver: F1Driver) {
        listOfDrivers.value?.add(driver)
    }
}
