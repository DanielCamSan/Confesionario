package edu.bo.confesionario.publications

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
//https://www.youtube.com/watch?v=obYxPd2ot7Q
class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int,private val fragments : List<Fragment>) : FragmentStateAdapter(activity) {
    private var currentPosition : Int = 0
    override fun getItemCount() : Int = tabCount
    fun getCurrentPosition() : Int = currentPosition
    override fun createFragment(position: Int): Fragment
    {
        currentPosition = position//if (position in 0..4) position else 0
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