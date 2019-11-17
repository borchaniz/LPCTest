package com.lakooz.lpctest

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lakooz.lpctest.model.Pot
import com.lakooz.lpctest.networking.RestApiClient
import com.lakooz.lpctest.repositories.PotRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintWriter
import java.io.StringWriter


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PotRepository.instance

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    fun getPots(context: MainActivity) {

        RestApiClient.getPots()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Pot>> {

                var disposable: Disposable? = null

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(pots: List<Pot>) {
                    disposable?.dispose()
                    repository.insertAllAndSynchronize(pots)
                    _isRefreshing.value = false
                    (context.viewPager.adapter as ViewPagerAdapter).fragments[context.viewPager.currentItem].viewModel.refresh(context.viewPager.currentItem)
                }

                override fun onError(e: Throwable) {
                    _isRefreshing.value = false
                    val sw = StringWriter()
                    val pw = PrintWriter(sw)
                    e.printStackTrace(pw)
                    Log.d("..............;", "$sw")

                }

            }

            )
    }

    fun createPot(category: Int) {

        RestApiClient.createPot(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Pot> {

                var disposable: Disposable? = null

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(pot: Pot) {
                    disposable?.dispose()
                    repository.createOrUpdate(pot)

                }

                override fun onError(e: Throwable) {
                    //TODO
                }

            }

            )
    }
}