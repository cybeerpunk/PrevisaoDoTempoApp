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
    val mStoreClima = StoreClimaDTO()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        AddressPreference(this).get()
        if(!mStoreClima.listDetalhesDTO.equals(null)){
            setInformationCache()
        }


        haveLocation()

    }

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


    fun setupAdapter() {
        val lAdapter = ListPrevisaoAdapter(this, mListTimeDTO, mListTemperature, mListPrecipitation)
        mBinding.recyclerViewHours.adapter = lAdapter
        mBinding.recyclerViewHours.layoutManager = LinearLayoutManager(this)
    }

    fun getListPrevisao() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                /*val lRepository = ListPrevisaoRepository().getValuePrevisao()*/
                val lRepository = ListPrevisaoRepository(this@MainActivity).getValueWithLocation(mLatitude, mLongitude)
                val lAddres = ListPrevisaoRepository(this@MainActivity).getNameOfLocation(mLatitude,mLongitude)
                mStoreClima.listDetalhesDTO = lRepository
                mStoreClima.objeticLocationDTO = lAddres


                ListPrevisaoRepository(this@MainActivity).storeClima(mStoreClima)
                withContext(Dispatchers.Main) {
                    mListTemperature = lRepository.hourly.temperature_2m
                    mListPrecipitation = lRepository.hourly.precipitation
                    mListTimeDTO = lRepository.hourly.time
                    if (!mListTimeDTO.isNullOrEmpty())
                        setupAdapter()
                    else
                        throw Exception("Não foi possível concluir a requisição")
                    mBinding.textViewNomeDoLocal.text = lAddres.address.city + " " + lAddres.address.state + " " + lAddres.address.country
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

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
            lClient.lastLocation.addOnSuccessListener { location: Location? ->
                mLatitude = location?.latitude.toString()
                mLongitude = location?.longitude.toString()

                Toast.makeText(this, "$mLatitude $mLongitude", Toast.LENGTH_SHORT).show()
                getListPrevisao()

            }
        }


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

     fun setInformationCache(){
         mBinding.textViewNomeDoLocal.text = mStoreClima.objeticLocationDTO.address.city + " " + mStoreClima.objeticLocationDTO.address.state + " " + mStoreClima.objeticLocationDTO.address.country
         mListTemperature = mStoreClima.listDetalhesDTO.hourly.temperature_2m
         mListPrecipitation = mStoreClima.listDetalhesDTO.hourly.precipitation
         mListTimeDTO = mStoreClima.listDetalhesDTO.hourly.time

         setupAdapter()
     }


}




