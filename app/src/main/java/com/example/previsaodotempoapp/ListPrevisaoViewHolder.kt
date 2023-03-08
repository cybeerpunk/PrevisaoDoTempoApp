package com.example.previsaodotempoapp

import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempoapp.databinding.RecyclerViewListPrevisaoBinding
import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import com.example.previsaodotempoapp.dto.TimeDTO


class ListPrevisaoViewHolder(
    val mBinding: RecyclerViewListPrevisaoBinding,
) : RecyclerView.ViewHolder(mBinding.root) {

    fun fill(aListDetalhesDTO: String) {
        mBinding.textViewTime.setText(aListDetalhesDTO)
       // mBinding.textViewTemperatura.setText(aListDetalhesDTO.temperature_2m.toString())
       // mBinding.textViewPrecipitacao.setText(aListDetalhesDTO.precipitation.toString())


    }
}

