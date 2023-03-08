package com.example.previsaodotempoapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
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


        haveLocation()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1 && grantResults.isNotEmpty()){
            for (i in grantResults){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    getListPrevisao()
                }
            }
        }
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

    fun haveLocation() {
        val lPermission = mutableListOf<String>()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            lPermission.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this, lPermission.toTypedArray(), 1)

        }

    }


}