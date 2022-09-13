package com.example.pracviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.pracviewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val advList = arrayListOf(
            Advs(R.drawable.cat, "고양이"),
            Advs(R.drawable.dog, "강아지")
        )

        //vp

        //binding.vpAdv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.vpAdv.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향
        binding.vpAdv.adapter = AdvViewPagerAdapter(advList)  // 어댑터 생성

    }


}