package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.viewpager.widget.ViewPager
//import com.example.guru2.Boardpage_Adapter.PurchaseAdapter
//import com.google.android.material.tabs.TabLayout

class BoardPurchaseActivity : AppCompatActivity() {

//    private lateinit var bp_tabLayout: TabLayout
//    private lateinit var bp_viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boardpage_purchase)

//        bp_tabLayout=findViewById(R.id.bp_tablayout)
//        bp_viewPager=findViewById(R.id.bp_viewpage)
//
//        bp_tabLayout.addTab(bp_tabLayout.newTab().setText("전체"))
//        bp_tabLayout.addTab(bp_tabLayout.newTab().setText("의류"))
//        bp_tabLayout.addTab(bp_tabLayout.newTab().setText("화장품"))
//        bp_tabLayout.addTab(bp_tabLayout.newTab().setText("음식"))
//        bp_tabLayout.addTab(bp_tabLayout.newTab().setText("생활품"))
//        bp_tabLayout.addTab(bp_tabLayout.newTab().setText("여성용품"))
//
//        val adapter = PurchaseAdapter(this, supportFragmentManager,
//            bp_tabLayout.tabCount)
//        bp_viewPager.adapter=adapter
//
//        bp_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(bp_tabLayout))
//        bp_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                bp_viewPager.currentItem=tab!!.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?){}
//            override fun onTabReselected(tab: TabLayout.Tab?){}
//        })
    }
}