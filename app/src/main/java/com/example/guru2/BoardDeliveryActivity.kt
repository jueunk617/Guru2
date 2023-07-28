package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
//import com.example.guru2.BoardPage_Fragments_Delivery.All_food
//import com.example.guru2.BoardPage_Fragments_Delivery.Asian_food
//import com.example.guru2.BoardPage_Fragments_Delivery.Chinese_food
//import com.example.guru2.Boardpage_Adapter.PurchaseAdapter
import com.google.android.material.tabs.TabLayout

class BoardDeliveryActivity : AppCompatActivity() {

//
//    private lateinit var bd_tabLayout: TabLayout
//    private lateinit var bd_viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boardpage_delivery)
//
//
//        bd_tabLayout=findViewById(R.id.bd_tablayout)
//        bd_viewPager=findViewById(R.id.bd_tablayout)
//
//        bd_tabLayout.addTab(bd_tabLayout.newTab().setText("전체"))
//        bd_tabLayout.addTab(bd_tabLayout.newTab().setText("한식"))
//        bd_tabLayout.addTab(bd_tabLayout.newTab().setText("중식"))
//        bd_tabLayout.addTab(bd_tabLayout.newTab().setText("일식"))
//        bd_tabLayout.addTab(bd_tabLayout.newTab().setText("양식"))
//        bd_tabLayout.addTab(bd_tabLayout.newTab().setText("아시안"))
//        bd_tabLayout.addTab(bd_tabLayout.newTab().setText("디저트"))
//
//        val adapter = PurchaseAdapter(this, supportFragmentManager,
//            bd_tabLayout.tabCount)
//        bd_viewPager.adapter=adapter
//
//        bd_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(bd_tabLayout))
//        bd_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                bd_viewPager.currentItem=tab!!.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?){}
//            override fun onTabReselected(tab: TabLayout.Tab?){}
//        })
    }
}