package com.example.previsaodotempoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempoapp.databinding.RecyclerViewListPrevisaoBinding

class ListPrevisaoAdapter(val mContext: Context, val aListTime: List<String>, val aListTemperature: List<Double>, val aListPrecipitation: List<Double>) :
    RecyclerView.Adapter<ListPrevisaoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPrevisaoViewHolder {
        val lBinding =
            RecyclerViewListPrevisaoBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ListPrevisaoViewHolder(lBinding, mContext)
    }

    override fun onBindViewHolder(holder: ListPrevisaoViewHolder, position: Int) {
        holder.fill(aListTime[position], aListTemperature[position], aListPrecipitation[position])
    }

    override fun getItemCount(): Int {
        return aListTime.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}