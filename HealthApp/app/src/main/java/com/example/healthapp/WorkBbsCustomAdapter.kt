package com.example.healthapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkBbsCustomAdapter(private val context: Context, private val dto: ArrayList<WorkBbsDto>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.work_bbs_layout_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dto[position], context)
    }

    override fun getItemCount(): Int {
        return dto.size
    }

}

// 게시글의 번호를 매기기위한 변수
var contentNum : Int = 1
class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    private val WorkBbsNum = itemView.findViewById<TextView>(R.id.WorkBbsNum)
    private val WorkBbsTitle = itemView.findViewById<TextView>(R.id.WorkBbsTitle)
    private val WorkBbsWriter = itemView.findViewById<TextView>(R.id.WorkBbsWriter)
    private val WorkBbsWdate = itemView.findViewById<TextView>(R.id.WorkBbsWdate)

    // 데이터 바인딩
    fun bind(dto:WorkBbsDto, context: Context){
        WorkBbsNum.text = contentNum++.toString()
        WorkBbsTitle.text = dto.title
        WorkBbsWriter.text = dto.nickname
        WorkBbsWdate.text = dto.wdate

        //itemView 클릭 시 이벤트
        itemView.setOnClickListener {
            contentNum = 1
            // 게시글 디테일로 이동
            Intent(context, WorkBbsDetailActivity::class.java).apply {

                // 디테일로 가져갈 데이터
                putExtra("WorkBbsData", dto)

                // 새로운 task 생성
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }
    }
}
