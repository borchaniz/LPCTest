package com.lakooz.lpctest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.lakooz.lpctest.databinding.PotsFragmentBinding
import com.lakooz.lpctest.model.Pot
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pots_fragment.*

class PotsFragment : Fragment() {

    lateinit var viewModel : PotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = PotsFragmentBinding.inflate(inflater, container, false)


        viewModel.pots.observe(this, Observer {
            (recycler_view.adapter as PotAdapter).setPots(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = PotAdapter(context!!, list_empty_layout)
        recycler_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel  = ViewModelProviders.of(this).get(PotsViewModel::class.java)
        viewModel.category = (activity as MainActivity).viewPager.currentItem


    }
}