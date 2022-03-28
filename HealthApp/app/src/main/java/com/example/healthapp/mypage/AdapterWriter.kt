package com.example.healthapp.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.workbbs.WorkBbsDto

class AdapterWriter(private val context: Context, private val dataList: ArrayList<WorkBbsDto>)
:RecyclerView.Adapter<AdapterWriter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val wtitle = itemView.findViewById<TextView>(R.id.mywTitle)
        private val wdate = itemView.findViewById<TextView>(R.id.mywDate)
        private val wread = itemView.findViewById<TextView>(R.id.mywReadcount)

        fun bind(dto: WorkBbsDto, context: Context){
            wtitle.text = dto.title
            wdate.text = dto.wdate
            wread.text = dto.readcount.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWriter.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_write_layout, parent, false)
        return AdapterWriter.ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterWriter.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}