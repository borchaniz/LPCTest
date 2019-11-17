package com.lakooz.lpctest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.lakooz.lpctest.model.Pot
import com.lakooz.lpctest.networking.RestApiClient
import com.lakooz.lpctest.repositories.PotRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


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
                    (context.viewPager.adapter as ViewPagerAdapter).fragments[context.viewPager.currentItem].viewModel.refresh(
                        context.viewPager.currentItem
                    )
                }

                override fun onError(e: Throwable) {
                    _isRefreshing.value = false
                    Snackbar.make(
                        context.root,
                        "Oups, une erreur est survenue!",
                        Snackbar.LENGTH_LONG
                    ).show()

                }

            }

            )
    }

    fun createPot(category: Int, context: MainActivity) {

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
                    Snackbar.make(
                        context.root,
                        "Pot créé avec succès",
                        Snackbar.LENGTH_LONG
                    ).show()

                }

                override fun onError(e: Throwable) {
                    Snackbar.make(
                        context.root,
                        "Oups, une erreur est survenue!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }

            )
    }
}