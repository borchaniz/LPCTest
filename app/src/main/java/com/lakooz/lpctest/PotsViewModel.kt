package com.lakooz.lpctest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lakooz.lpctest.model.Pot
import com.lakooz.lpctest.repositories.PotRepository
import kotlin.concurrent.thread

class PotsViewModel : ViewModel() {

    private val repository = PotRepository.instance
    var category = 0
    val pots: LiveData<List<Pot>> = MutableLiveData<List<Pot>>().apply {
        var temp: List<Pot> = listOf()
        thread{
            temp = repository.getPots(category)
            Log.d("...........","${repository.getPots(0)}")
        }
        value = temp
    }


}