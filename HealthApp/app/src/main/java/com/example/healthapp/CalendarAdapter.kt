package com.example.healthapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(private val context: Context, private val dto: ArrayList<CalendarDto>)
    : RecyclerView.Adapter<ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder {
        println("~~~onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.calendar_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        println(dto[position].calendardate +"+++++++++++++"+ dto[position].content)
        holder.bind(dto[position], context)
    }

    override fun getItemCount(): Int {
        return dto.size
    }

}

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val dayTxt = view.findViewById<TextView>(R.id.dayTxt)
    private val contentTxt = view.findViewById<TextView>(R.id.contentTxt)

    // 데이터 바인딩
    fun bind(dto: CalendarDto, context: Context){
        println("+++++++++++++${dto.toString()}")
        dayTxt.text = dto.calendardate
        contentTxt.text = dto.content

    }
}