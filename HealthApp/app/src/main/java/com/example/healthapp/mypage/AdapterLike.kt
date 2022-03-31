package com.example.healthapp.mypage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDetailActivity
import com.example.healthapp.bbs.BbsDto

// 회원테이블에 좋아요 누른 글 목록 생성
class AdapterLike(private val context: Context, private val dataList: ArrayList<BbsDto>)
    : RecyclerView.Adapter<AdapterLike.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val ltitle = itemView.findViewById<TextView>(R.id.mylTitle)
        private val lperson = itemView.findViewById<TextView>(R.id.mylPerson)
        private val ldate = itemView.findViewById<TextView>(R.id.mylDate)

        fun bind(dto: BbsDto, context: Context){
            ltitle.text = dto.title
            lperson.text = dto.nickname
            ldate.text = dto.wdate

            // 게시글 디테일로 이동
            itemView.setOnClickListener {
                Intent(context, BbsDetailActivity::class.java).apply {
                    putExtra("WorkBbsData", dto)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_like_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}