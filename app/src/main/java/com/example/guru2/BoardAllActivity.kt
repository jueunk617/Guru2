package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.guru2.Boardpage_Adapter.AllAdapter
import com.google.android.material.tabs.TabLayout

class BoardAllActivity : AppCompatActivity() {

    private lateinit var ba_tabLayout: TabLayout
    private lateinit var ba_viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boardpage_all)

        ba_tabLayout=findViewById(R.id.ba_tablayout)
        ba_viewPager=findViewById(R.id.ba_viewpage)

        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("전체"))
        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("패션"))
        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("생필품"))
        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("음식"))
        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("OTT"))
        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("운동"))
        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("티켓"))
        ba_tabLayout.addTab(ba_tabLayout.newTab().setText("여행"))

        val adapter = AllAdapter(this, supportFragmentManager,
            ba_tabLayout.tabCount)
        ba_viewPager.adapter=adapter

        ba_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(ba_tabLayout))
        ba_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                ba_viewPager.currentItem=tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?){}
            override fun onTabReselected(tab: TabLayout.Tab?){}
        })
    }
}