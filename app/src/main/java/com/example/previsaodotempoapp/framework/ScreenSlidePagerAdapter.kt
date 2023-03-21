package com.example.previsaodotempoapp.framework

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenSlidePagerAdapter (fm: FragmentManager?, lf: Lifecycle?) :
    FragmentStateAdapter(fm!!, lf!!) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment) // utilizado para adicionar a lista de fragment na activity
    }
}