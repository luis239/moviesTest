package com.example.luis.test.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MoviesViewPager (
    fragmentManager: FragmentManager,
    private val items: List<Fragment>,
    private val titles: List<String>
): FragmentPagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = items[position]

    override fun getCount() = items.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}