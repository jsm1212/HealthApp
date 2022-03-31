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
import com.example.healthapp.bbs.BbsDto
import com.example.healthapp.bbs.BbsDetailActivity


class AdapterWriter(private val context: Context, private val dataList: ArrayList<BbsDto>)
:RecyclerView.Adapter<AdapterWriter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val wtitle = itemView.findViewById<TextView>(R.id.mywTitle)
        private val wdate = itemView.findViewById<TextView>(R.id.mywDate)
        private val wread = itemView.findViewById<TextView>(R.id.mywReadcount)

        fun bind(dto: BbsDto, context: Context){
            wtitle.text = dto.title
            wdate.text = dto.wdate
            wread.text = dto.readcount.toString()

            // 게시글 디테일로 이동
            itemView.setOnClickListener {
                Intent(context, BbsDetailActivity::class.java).apply {
                    BbsDao.bbsSeq = dto.seq
                    putExtra("WorkBbsData", dto)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_write_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}