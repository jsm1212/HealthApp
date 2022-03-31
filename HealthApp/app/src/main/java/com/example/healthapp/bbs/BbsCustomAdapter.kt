package com.example.healthapp.bbs

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDao

class WorkBbsCustomAdapter(private val context: Context, private val dto: ArrayList<BbsDto>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bbs_layout_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dto[position], context)
    }

    override fun getItemCount(): Int {
        return dto.size
    }

}

class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    private val WorkBbsTitle = itemView.findViewById<TextView>(R.id.WorkBbsTitle)
    private val WorkBbsWriter = itemView.findViewById<TextView>(R.id.WorkBbsWriter)
    private val WorkBbsWdate = itemView.findViewById<TextView>(R.id.WorkBbsWdate)

    // 데이터 바인딩
    fun bind(dto: BbsDto, context: Context){
        WorkBbsTitle.text = dto.title
        WorkBbsWriter.text = dto.nickname
        WorkBbsWdate.text = dto.wdate

        //itemView 클릭 시 이벤트
        itemView.setOnClickListener {

            // 게시글 디테일로 이동
            Intent(context, BbsDetailActivity::class.java).apply {
                val detailDto = BbsDao.getInstance().bbsDetail(dto.seq!!, LoginMemberDao.user?.id!!)

                BbsDao.bbsSeq = dto.seq
                // 디테일로 가져갈 데이터
                putExtra("WorkBbsData", detailDto)

                // 새로운 task 생성
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }
    }
}
