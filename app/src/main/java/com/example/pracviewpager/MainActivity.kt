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
            Advs(R.drawable.dog, "강아지"),
            Advs(R.drawable.tiger, "호랑이")
        )

        /** view pager */
        // 여백, 너비에 대한 정의
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // 여백
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageMargin) // 페이저 너비
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 가져옴
        val offsetPx = screenWidth - pagerWidth - pageMarginPx

        //setPageTransformer이용해 page의 X축 값에 변화 줌
        binding.vpAdv.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx

        }

        binding.vpAdv.offscreenPageLimit = 1 // 몇 개의 페이지를 미리 로드 해둘지
        binding.vpAdv.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향
        binding.vpAdv.adapter = AdvViewPagerAdapter(advList)  // 어댑터 생성

    }


}