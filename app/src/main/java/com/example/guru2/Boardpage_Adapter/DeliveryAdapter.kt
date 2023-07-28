package com.example.guru2.Boardpage_Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.guru2.BoardPage_Fragments_Delivery.All_food
import com.example.guru2.BoardPage_Fragments_Delivery.Asian_food
import com.example.guru2.BoardPage_Fragments_Delivery.Chinese_food
import com.example.guru2.BoardPage_Fragments_Delivery.Dessert
import com.example.guru2.BoardPage_Fragments_Delivery.Japanese_food
import com.example.guru2.BoardPage_Fragments_Delivery.Korean_food
import com.example.guru2.BoardPage_Fragments_Delivery.Western_food
//
//
//internal class DeliveryAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {
//
//
//    override fun getItem(position: Int): Fragment {
//        return when(position){
//            0 ->{
//                All_food()
//            }
//            1 ->{
//                Korean_food()
//            }
//            2 ->{
//                Chinese_food()
//            }
//            3 ->{
//                Japanese_food()
//            }
//            4 ->{
//                Western_food()
//            }
//            5 ->{
//                Asian_food()
//            }
//            6 ->{
//                Dessert()
//            }
//            else -> getItem(position)
//        }
//    }
//    override fun getCount(): Int {
//        return totalTabs
//    }
//}