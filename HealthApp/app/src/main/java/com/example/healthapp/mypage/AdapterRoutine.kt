package com.example.healthapp.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDto
import com.example.healthapp.work.WorkVo

class AdapterRoutine(private val context: Context, private val dataList: ArrayList<WorkVo>)
: RecyclerView.Adapter<AdapterRoutine.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val rtList = itemView.findViewById<TextView>(R.id.myrtList)
        private val rtCount = itemView.findViewById<TextView>(R.id.myrtCount)

        fun bind(dto: WorkVo, context: Context){
            // 운동루틴목록 토의 후 작성
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_routine_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}