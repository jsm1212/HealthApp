package com.example.healthapp.bbs

import android.annotation.SuppressLint
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
    private val WorkBbsReplyCount = itemView.findViewById<TextView>(R.id.WorkBbsReplyCount)
    private val WorkBbsReadCount = itemView.findViewById<TextView>(R.id.WorkBbsReadCount)
    private val WorkBbsLikeCount = itemView.findViewById<TextView>(R.id.WorkBbsLikeCount)

    // 데이터 바인딩
    @SuppressLint("SetTextI18n")
    fun bind(dto: BbsDto, context: Context){
        // 작성일 split
        val dateArr = dto.wdate?.split(":")
        var titleStr = ""
        if(dto.title!!.length >= 20){
            titleStr = dto.title!!.substring(0 until 20) + "..."
        }else{
            titleStr = dto.title
        }
        val replyCount = BbsReplyDao.getInstance().getReplyCount(dto.seq!!)

        // 리사이클러뷰 데이터세팅
        // 제목
        WorkBbsTitle.text = titleStr
        // 작성자
        WorkBbsWriter.text = dto.nickname
        // 작성일
        WorkBbsWdate.text = "${dateArr!![0]}:${dateArr!![1]}"
        // 댓글 수
        WorkBbsReplyCount.text = "$replyCount 🗨"
        // 좋아요 수
        WorkBbsLikeCount.text =  dto.bbsLike.toString() + " 💪"
        // 조회수
        WorkBbsReadCount.text =  dto.readcount.toString() + " 👀"

        //itemView 클릭 시 이벤트
        itemView.setOnClickListener {

            // 게시글 디테일로 이동
            Intent(context, BbsDetailActivity::class.java).apply {
                val detailDto = BbsDao.getInstance().bbsDetail_M(ReadCountBbsDto(dto.seq!!, LoginMemberDao.user?.id!!))

                BbsDao.bbsSeq = dto.seq
                // 디테일로 가져갈 데이터
                putExtra("WorkBbsData", detailDto)

                // 새로운 task 생성
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }
    }
}
