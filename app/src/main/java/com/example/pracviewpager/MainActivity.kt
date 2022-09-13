package com.example.pracviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.pracviewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val numBanner = 3 //배너 총 수

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

        /**어댑터 생성*/
        binding.vpAdv.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향
        binding.vpAdv.adapter = AdvViewPagerAdapter(advList)  // 어댑터 생성


        /**인디케이터*/
        //인디케이터 뷰페이저와 결합
        binding.indicator.setViewPager2(binding.vpAdv) // 인디케이터 설정

        /**현재 배너 위치 표시하기*/
        //private으로 위에서 전역변수로 총 페이지 수 선언함
        binding.tvTotal.text = numBanner.toString()

        //현재 몇번재 배너잉ㄴ지 보여주는 숫자를 변경함
        binding.vpAdv.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvCurrent.text = "${position+1}" //position +1 (position이 0부터 시작하므로)
                }
            })
        }

        //모두 보기 클릭시 기능 -> 원하는 걸로 구현하면 됨
        binding.checkpages.setOnClickListener {
            Toast.makeText(this, "모두 보기 클릭함", Toast.LENGTH_SHORT).show()
        }
    }


}