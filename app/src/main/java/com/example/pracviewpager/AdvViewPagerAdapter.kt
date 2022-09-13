package com.example.pracviewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pracviewpager.databinding.ItemAdvBinding



class AdvViewPagerAdapter(val advList : ArrayList<Advs>) : RecyclerView.Adapter<AdvViewPagerAdapter.CustomViewHolder>(){

    class CustomViewHolder(private val binding: ItemAdvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(adv: Advs) = with(binding){
            ivAdv.setImageResource(adv.img)
            tvAdv.text = adv.animal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adv, parent, false)
        return CustomViewHolder(ItemAdvBinding.bind(view))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(advList[position]) // 설정한 데이터의 수로 나눈다
    }

    override fun getItemCount(): Int = advList.size // 아이템 갯수를 임의로 억 자리 숫자로 늘린다


}