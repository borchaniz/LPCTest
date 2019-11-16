package com.lakooz.lpctest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
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

        // TODO : set up view model


        swipeRefreshLayout.setOnRefreshListener {
            // TODO
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                val viewPagerIdle = state == ViewPager2.SCROLL_STATE_IDLE
                swipeRefreshLayout.isEnabled = viewPagerIdle
            }
        })

        fab.setOnClickListener {
            // TODO
        }
    }

    companion object {
        private const val START_SWIPE_REFRESH = 50
    }
}
