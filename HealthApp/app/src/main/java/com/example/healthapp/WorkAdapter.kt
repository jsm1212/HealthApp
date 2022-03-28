package com.example.healthapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WorkAdapter(private val context: Context, private val dataList: ArrayList<WorkVo>):
    RecyclerView.Adapter<WorkAdapter.ItemViewHolder>(),Filterable {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.view_item_layout, parent,false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(filterData!![position], context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    private var filterData:ArrayList<WorkVo>?=dataList

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                println("~~~~~~~~~~~~$constraint")
                val filterNum:Int=constraint.toString().toInt()
                filterData = if (filterNum==0) {
                    dataList
                } else {
                    println("~~~~~~~~~~~~else~~~~")
                    val filteringList = ArrayList<WorkVo>()
                    println("~~~~~~~~~~~~filteringList~~~~")
                     for (item in dataList) {
                        println("~~~~~~~~~~~~${item.workcategory}")
                        if (item.workcategory == filterNum-1) filteringList.add(item)

                    }
                    println("~~~~~~~~~~~~forend~~~~")
                    for(item in filteringList) {
                        println("${item.name}, ")
                        println("${item.explanation}, ")
                        println("${item.workcategory}, ")
                    }
                    filteringList
                }
                println("~~~~~~~~~~~~if end~~~~")
                val filterResult=FilterResults()
                filterResult.values=filterData
                println("~~~~~~~~~~~~filterResult end~~~~")
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            }

        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //private val workimg = itemView.findViewById<ImageView>(R.id.workimg)
        private val userName = itemView.findViewById<TextView>(R.id.workname)
        private val workexplanation = itemView.findViewById<TextView>(R.id.workexplanation)
        private val workCategory= itemView.findViewById<TextView>(R.id.workcategory)







        fun bind(WorkVo: WorkVo, context: Context) {
            /*
            if (WorkVo.photo != "") {
                val resourceId = context.resources.getIdentifier(WorkVo.photo, "drawable", context.packageName)

                if (resourceId > 0)
                    workimg.setImageResource(resourceId)
                else
                    Glide.with(itemView).load(WorkVo.photo).into(workimg)

            } */
            //workimg.text= "123"
            userName.text = WorkVo.name
            workexplanation.text = WorkVo.explanation
            when(WorkVo.workcategory){
                0->{workCategory.text="어깨"}
                1->{workCategory.text="가슴"}
                2->{workCategory.text="배"}
                3->{workCategory.text="하체"}
            }

            //itemView 클릭시
            /*itemView.setOnClickListener{
                }.run { context.startActivity(this) }
            }*/
        }
    }
}