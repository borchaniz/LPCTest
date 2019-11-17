package com.lakooz.lpctest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    val fragments = mutableListOf<PotsFragment>()
    override fun createFragment(position: Int): Fragment {
        fragments.add(PotsFragment())
        return fragments.last()
    }

    override fun getItemCount() = 3
}