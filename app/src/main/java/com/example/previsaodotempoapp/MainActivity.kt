package com.example.previsaodotempoapp

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempoapp.api.ListPrevisaoRepository
import com.example.previsaodotempoapp.databinding.ActivityMainBinding
import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import com.example.previsaodotempoapp.dto.StoreClimaDTO
import com.example.previsaodotempoapp.store.AddressPreference
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    lateinit var mListTimeDTO: List<String>
    lateinit var mListTemperature: List<Double>
    lateinit var mListPrecipitation: List<Double>
    lateinit var mLatitude: String
    lateinit var mLongitude: String
    var mStoreClima = StoreClimaDTO()
    lateinit var mAdapter: ListPrevisaoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mStoreClima = AddressPreference(this).get()

        clickTextRefresh()

        if(mStoreClima.listDetalhesDTO.latitude != null && mStoreClima.objeticLocationDTO.address.city!= "") {
            setInformationCache()
        } else {
            haveLocation()
        }



    }
       //Função utilizada para pegar o resultado da pergunta de permissão do usuário
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty()) {
            for (i in grantResults) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Passou Resquest Permissions Result", Toast.LENGTH_SHORT)
                        .show()
                    haveLocation()
                }
            }
        }
    }

     //Utilizado para setar as informações do RecyclerView
    fun setupAdapter() {
        mAdapter = ListPrevisaoAdapter(this, mListTimeDTO, mListTemperature, mListPrecipitation)
        mBinding.recyclerViewHours.adapter = mAdapter
        mBinding.recyclerViewHours.layoutManager = LinearLayoutManager(this)

        mAdapter.notifyDataSetChanged() // utilizado para recarregar a informações do Recycler
    }

    // Utilizada para fazer as requisições de lat e long, endereço do usuario, limpar cache e guardar um novo, setar as novas informações das variaveis.
    fun getListPrevisao() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                /*val lRepository = ListPrevisaoRepository().getValuePrevisao()*/
                val lRepository = ListPrevisaoRepository(this@MainActivity).getValueWithLocation(mLatitude, mLongitude)
                val lAddress = ListPrevisaoRepository(this@MainActivity).getNameOfLocation(mLatitude,mLongitude)
                mStoreClima.listDetalhesDTO = lRepository
                mStoreClima.objeticLocationDTO = lAddress

                ListPrevisaoRepository(this@MainActivity).clearData()
                ListPrevisaoRepository(this@MainActivity).storeClima(mStoreClima)

                withContext(Dispatchers.Main) {
                    mListTemperature = lRepository.hourly.temperature_2m
                    mListPrecipitation = lRepository.hourly.precipitation
                    mListTimeDTO = lRepository.hourly.time
                    if (!mListTimeDTO.isNullOrEmpty())
                        setupAdapter()
                    else
                        throw Exception("Não foi possível concluir a requisição")
                    mBinding.textViewNomeDoLocal.text = lAddress.address.city + " " + lAddress.address.state + " " + lAddress.address.country
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
  // Para verificar se ter permissão de localização e se não tiver vai solicitar
    fun haveLocation() {
        val lPermission = mutableListOf<String>()

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            lPermission.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this, lPermission.toTypedArray(), 1)

        } else {
            val lClient = LocationServices.getFusedLocationProviderClient(this)
            lClient.lastLocation.addOnSuccessListener { location: Location? -> // busca lat e long pega pelo usuario pela ultima vez
                mLatitude = location?.latitude.toString()
                mLongitude = location?.longitude.toString()

                getListPrevisao()

            }
        }

       //Busca da lat e long mais dificil
//            val lLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            val lLocation =
//                lLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//
//            if (lLocation != null) {
//                mLatitude = lLocation.latitude.toString()
//                mLongitude = lLocation.longitude.toString()
//                getListPrevisao()
//
//            }
//        }

    }
     // utilizada para setar todas as variaveis do cache nas variaveis globais, para começar o app direto com as informacoes já salva no cache, para que não seja realizada a requisicao de novo
     fun setInformationCache(){
         mBinding.textViewNomeDoLocal.text = mStoreClima.objeticLocationDTO.address.city + " " + mStoreClima.objeticLocationDTO.address.state + " " + mStoreClima.objeticLocationDTO.address.country
         mListTemperature = mStoreClima.listDetalhesDTO.hourly.temperature_2m
         mListPrecipitation = mStoreClima.listDetalhesDTO.hourly.precipitation
         mListTimeDTO = mStoreClima.listDetalhesDTO.hourly.time

         setupAdapter()
     }

    fun clickTextRefresh(){
        mBinding.textViewNomeDoLocal.setOnClickListener {
            mListTemperature = emptyList()
            mListPrecipitation = emptyList()
            mListTimeDTO = emptyList()
            setupAdapter()
            haveLocation()

        }
    }


}




