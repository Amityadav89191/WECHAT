package com.jack.instagramclone.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class viewpagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    val FragmentList= mutableListOf<Fragment>()
    val titleList= mutableListOf<String>()
    override fun getCount(): Int {
    return FragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return FragmentList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get(position)
    }
    fun addFragments(fragment: Fragment,title:String){
        FragmentList.add(fragment)
        titleList.add(title)
    }

}