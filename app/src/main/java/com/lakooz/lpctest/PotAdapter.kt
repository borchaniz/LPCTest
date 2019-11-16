package com.lakooz.lpctest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lakooz.lpctest.databinding.PotItemBinding
import com.lakooz.lpctest.model.Pot
import android.view.LayoutInflater


class PotAdapter(private val context: Context, private var emptyView: View? = null) :
    RecyclerView.Adapter<PotAdapter.ViewHolder>() {


    private var pots: List<Pot>? = null
    private var recyclerView: RecyclerView? = null


    fun setPots(pots: List<Pot>?) {
        this.pots = pots
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PotItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = if (pots != null) pots!!.size else 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null

        super.onDetachedFromRecyclerView(recyclerView)
    }

    inner class ViewHolder(val binding: PotItemBinding) : RecyclerView.ViewHolder(binding.root)
}