package com.lakooz.lpctest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lakooz.lpctest.databinding.PotsFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pots_fragment.*

class PotsFragment : Fragment() {

    var viewModel: PotsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = PotsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = PotAdapter(context!!, list_empty_layout)
        recycler_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(PotsViewModel::class.java)
        viewModel!!.category = (activity as MainActivity).viewPager.currentItem
        viewModel!!.pots.observe(this, Observer {
            (recycler_view.adapter as PotAdapter).setPots(it)
            list_empty_layout.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
        })
    }

    fun refresh() {
        viewModel!!.refresh((activity as MainActivity).viewPager.currentItem)
    }
}