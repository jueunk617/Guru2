package com.example.guru2.Boardpage_Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.guru2.BoardPage_Fragments_Purchase.All_purchase
import com.example.guru2.BoardPage_Fragments_Purchase.Clothing
import com.example.guru2.BoardPage_Fragments_Purchase.Cosmetics
import com.example.guru2.BoardPage_Fragments_Purchase.Food_purchase
import com.example.guru2.BoardPage_Fragments_Purchase.Goods
import com.example.guru2.BoardPage_Fragments_Purchase.Womenproducts


//internal class PurchaseAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {
//
//
//    override fun getItem(position: Int): Fragment {
//        return when(position){
//            0 ->{
//                All_purchase()
//            }
//            1 ->{
//                Clothing()
//            }
//            2 ->{
//                Cosmetics()
//            }
//            3 ->{
//                Food_purchase()
//            }
//            4 ->{
//                Goods()
//            }
//            5 ->{
//                Womenproducts()
//            }
//            else -> getItem(position)
//        }
//    }
//    override fun getCount(): Int {
//        return totalTabs
//    }
//}