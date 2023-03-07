package com.example.previsaodotempoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempoapp.api.ListPrevisaoRepository
import com.example.previsaodotempoapp.databinding.ActivityMainBinding
import com.example.previsaodotempoapp.dto.TimeDTO
import com.example.previsaodotempoapp.framework.ScreenSlidePagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    lateinit var mListTimeDTO: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        getListPrevisao()

    }

    fun setupAdapter() {
        val lAdapter = ListPrevisaoAdapter(this, mListTimeDTO)
        mBinding.recyclerViewHours.adapter = lAdapter
        mBinding.recyclerViewHours.layoutManager = LinearLayoutManager(this)
    }

    fun getListPrevisao() {
        try {
            val lRepository = ListPrevisaoRepository().getValuePrevisao()
            mListTimeDTO = lRepository.hourly.time
            if (!mListTimeDTO.isNullOrEmpty())
                setupAdapter()
            else
                throw Exception("Não foi possível concluir a requisição")
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
        }


    }
}