package com.example.previsaodotempoapp

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempoapp.databinding.RecyclerViewListPrevisaoBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ListPrevisaoViewHolder(
    val mBinding: RecyclerViewListPrevisaoBinding,
    val mContext: Context
) : RecyclerView.ViewHolder(mBinding.root) {


    // setar as informacoes do argumento, e foi realizado as validacoes para decidir qual icone vai ser setado
    fun fill(aListTimeDTO: String, aListTemperature: Double, aListPrecipitation: Double) {
        val lFormatDate = LocalDateTime.parse(aListTimeDTO, DateTimeFormatter.ISO_DATE_TIME)

        mBinding.textViewTime.setText(lFormatDate.format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))

       mBinding.textViewTemperatura.setText(aListTemperature.toString())
       mBinding.textViewPrecipitacao.setText(aListPrecipitation.toString())
        var icon: Int = 0
        if (aListTemperature > 23){
            icon = R.drawable.ic_whatshot
        } else if (aListTemperature < 12){
            icon = R.drawable.ic_severe_cold
        } else {
            icon = R.drawable.ic_deblur
        }
        var iconRain: Int = 0
        if(aListPrecipitation > 2){
            iconRain = R.drawable.ic_thunderstorm
        } else if (aListPrecipitation < 0.5){
            iconRain = R.drawable.ic_sunny
        } else {
            iconRain = R.drawable.ic_cloud
        }
        mBinding.imageViewTemperatura.setImageDrawable(ContextCompat.getDrawable(mContext, icon))
        mBinding.imageViewPrecipitacao.setImageDrawable(ContextCompat.getDrawable(mContext, iconRain))


    }
}

