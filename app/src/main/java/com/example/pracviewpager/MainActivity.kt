package com.example.pracviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.pracviewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val numBanner = 3 //배너 총 수
    private var currentPosition = Int.MAX_VALUE / 2 // 왼쪽으로도 무한 스크롤, 오른쪽으로도 무한 스크롤 할 수 있도록 현재 위치를 딱 중간으로 설정한다.
    private val myHandler = MyHandler()
    private val intervalTime = 1500.toLong() // 1.5초 마다 페이지 넘기기

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

        binding.vpAdv.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향
        binding.vpAdv.adapter = AdvViewPagerAdapter(advList)  // 어댑터 생성
        binding.tvTotal.text = numBanner.toString() // 총 페이지 숫자 반환
        binding.vpAdv.setCurrentItem(currentPosition, false) // 현재 위치 지정. 처음 시작하면 item1이 나오는데 걔가 currentItem이고 걔가 max/2 position으로 정해진 상태임


        binding.vpAdv.apply {
            //현재 몇번재 배너잉ㄴ지 보여주는 숫자를 변경함
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvCurrent.text = "${position%3  + 1}" // 여기도 %3
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손으로 스크롤하지 않는 상태
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                        // 뷰페이저 손으로 스크롤하는 상태
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }
    }

    private fun autoScrollStart(intervalTime: Long){
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개, ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    private fun autoScrollStop(){
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }

    private inner class MyHandler : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if(msg.what == 0) {
                binding.vpAdv.setCurrentItem(++currentPosition, true) // 보여지는 currentItem을 position+1한 걸로 지정해라. 즉, 다음 페이지로 이동이 됨
                autoScrollStart(intervalTime)
            }
        }
    }


}