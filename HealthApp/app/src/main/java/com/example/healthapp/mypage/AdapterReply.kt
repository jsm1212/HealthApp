package com.example.healthapp.mypage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDao
import com.example.healthapp.bbs.BbsReplyDto
import com.example.healthapp.bbs.BbsDetailActivity

// putExtra수정수정수정 BbsDto 넘기기
class AdapterReply(private val context: Context, private val dataList: ArrayList<BbsReplyDto>)
: RecyclerView.Adapter<AdapterReply.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val rpreply = itemView.findViewById<TextView>(R.id.myrpReply)
        private val rpdate = itemView.findViewById<TextView>(R.id.myrpDate)
        private val rplike = itemView.findViewById<TextView>(R.id.myrpLike)

        fun bind(dto: BbsReplyDto, context: Context){
            rpreply.text = dto.content
            rpdate.text = dto.wdate
            rplike.text = dto.replyLike.toString()

            val moveData = BbsDao.bbsData

            // 게시글 디테일로 이동
            itemView.setOnClickListener {
                Intent(context, BbsDetailActivity::class.java).apply {
                    BbsDao.bbsSeq = dto.replyNum
                    putExtra("WorkBbsData", moveData)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_reply_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}
