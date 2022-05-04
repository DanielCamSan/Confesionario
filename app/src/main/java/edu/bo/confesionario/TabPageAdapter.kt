package edu.bo.confesionario

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
//https://www.youtube.com/watch?v=obYxPd2ot7Q
class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int,private val fragments : List<Fragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount() : Int = tabCount

    override fun createFragment(position: Int): Fragment
    {
        return when (position){
            0-> fragments[0]
            1-> fragments[1]
            2-> fragments[2]
            3-> fragments[3]
            4-> fragments[4]
            else -> fragments[0]
        }
    }

}