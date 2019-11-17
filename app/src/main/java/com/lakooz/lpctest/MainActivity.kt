package com.lakooz.lpctest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        setSupportActionBar(toolbar)

        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "Anniversaire"
                    1 -> "Départ"
                    2 -> "Solidaire"
                    else -> ""
                }
            }).attach()


        swipeRefreshLayout.setProgressViewOffset(
            true,
            START_SWIPE_REFRESH,
            resources.getDimension(R.dimen.swipe_refresh_offset).toInt()
        )

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.isRefreshing.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = it
        })


        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPots(this)

        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                val viewPagerIdle = state == ViewPager2.SCROLL_STATE_IDLE
                swipeRefreshLayout.isEnabled = viewPagerIdle
            }
        })

        fab.setOnClickListener {
            viewModel.createPot(viewPager.currentItem, this)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if ((viewPager.adapter as ViewPagerAdapter).fragments.size > viewPager.currentItem)
                    (viewPager.adapter as ViewPagerAdapter).fragments[viewPager.currentItem].refresh()
            }
        })
    }

    companion object {
        private const val START_SWIPE_REFRESH = 50
    }
}