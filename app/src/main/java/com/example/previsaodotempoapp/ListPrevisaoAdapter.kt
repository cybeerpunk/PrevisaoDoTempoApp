package com.example.previsaodotempoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempoapp.databinding.RecyclerViewListPrevisaoBinding
import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import com.example.previsaodotempoapp.dto.TimeDTO

 class ListPrevisaoAdapter(val mContext: Context, val aListPrevisao: List<String>) :
    RecyclerView.Adapter<ListPrevisaoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPrevisaoViewHolder {
        val lBinding =
            RecyclerViewListPrevisaoBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ListPrevisaoViewHolder(lBinding)
    }

    override fun onBindViewHolder(holder: ListPrevisaoViewHolder, position: Int) {
        holder.fill(aListPrevisao[position])
    }

    override fun getItemCount(): Int {
        return aListPrevisao.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}