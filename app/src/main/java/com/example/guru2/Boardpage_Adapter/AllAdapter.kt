package com.example.guru2.Boardpage_Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.guru2.Boardpage_Fragments.All
import com.example.guru2.Boardpage_Fragments.Dailyproduct
import com.example.guru2.Boardpage_Fragments.Fashion
import com.example.guru2.Boardpage_Fragments.Food
import com.example.guru2.Boardpage_Fragments.Ott
import com.example.guru2.Boardpage_Fragments.Sports
import com.example.guru2.Boardpage_Fragments.Ticket
import com.example.guru2.Boardpage_Fragments.Trip


internal class AllAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                All()
            }
            1 ->{
                Dailyproduct()
            }
            2 ->{
                Fashion()
            }
            3 ->{
                Food()
            }
            4 ->{
                Ott()
            }
            5 ->{
                Sports()
            }
            6 ->{
                Ticket()
            }
            7 ->{
                Trip()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}