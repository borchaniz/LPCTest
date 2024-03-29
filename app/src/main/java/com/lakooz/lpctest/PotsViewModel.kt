package com.lakooz.lpctest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lakooz.lpctest.model.Pot
import com.lakooz.lpctest.repositories.PotRepository
import java.io.Serializable

class PotsViewModel : ViewModel(), Serializable {

    private val repository = PotRepository.instance
    var category = 0
    val pots: MutableLiveData<List<Pot>> = MutableLiveData()

    fun refresh(category: Int) {
        pots.value = repository.getPots(category)
    }


}