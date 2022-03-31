package com.example.healthapp.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDto

class AdapterTrainer(private val context: Context, private val dataList: ArrayList<BbsDto>)
    : RecyclerView.Adapter<AdapterTrainer.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val atid = itemView.findViewById<TextView>(R.id.atId)
        private val atnick = itemView.findViewById<TextView>(R.id.atNick)
        private val atname = itemView.findViewById<TextView>(R.id.atName)
        private val atemail = itemView.findViewById<TextView>(R.id.atEmail)
        private val attel = itemView.findViewById<TextView>(R.id.atTel)
        private val atregi = itemView.findViewById<TextView>(R.id.atRegiDate)
        private val atmember = itemView.findViewById<TextView>(R.id.atMember)

        fun bind(dto: BbsDto, context: Context){
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admin_trainer_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}