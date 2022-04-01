package com.example.healthapp.admin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDao
import com.example.healthapp.bbs.BbsDetailActivity
import com.example.healthapp.bbs.BbsDto

class AdapterBbs(private val context: Context, private val dataList: ArrayList<BbsDto>)
    : RecyclerView.Adapter<AdapterBbs.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val abtitle = itemView.findViewById<TextView>(R.id.abTitle)
        private val abnick = itemView.findViewById<TextView>(R.id.abNick)
        private val abdate = itemView.findViewById<TextView>(R.id.abDate)
        private val abbad = itemView.findViewById<TextView>(R.id.abBad)

        fun bind(dto: BbsDto, context: Context){
            abtitle.text = dto.title
            abnick.text = dto.nickname
            abdate.text = dto.wdate
            abbad.text = dto.bbsLike.toString()

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
        val view = LayoutInflater.from(context).inflate(R.layout.admin_bbs_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}