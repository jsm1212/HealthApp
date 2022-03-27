package com.example.healthapp.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.WorkBbsDto

// 트레이너 dto 추가되면 수정
class AdapterTrainer(private val context: Context, private val dataList: ArrayList<WorkBbsDto>)
    : RecyclerView.Adapter<AdapterTrainer.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val atid = itemView.findViewById<TextView>(R.id.atId)
        private val atnick = itemView.findViewById<TextView>(R.id.atNick)
        private val atname = itemView.findViewById<TextView>(R.id.atName)
        private val atemail = itemView.findViewById<TextView>(R.id.atEmail)
        private val attel = itemView.findViewById<TextView>(R.id.atTel)
        private val atregi = itemView.findViewById<TextView>(R.id.atRegiDate)
        private val atmember = itemView.findViewById<TextView>(R.id.atMember)
        private val atbad = itemView.findViewById<TextView>(R.id.atBad)

        fun bind(dto: WorkBbsDto, context: Context){
//            ltitle.text = dto.title
//            lperson.text = dto.nickname
//            ldate.text = dto.wdate
//            ldate.text = dto.wdate
//            ldate.text = dto.wdate
//            ldate.text = dto.wdate
//            ldate.text = dto.wdate
//            ldate.text = dto.wdate
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTrainer.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admin_trainer_layout, parent, false)
        return AdapterTrainer.ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterTrainer.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}