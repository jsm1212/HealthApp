package com.example.healthapp.work

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.healthapp.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class WorkAdapter(private val context: Context, private val dataList: ArrayList<WorkDto>) :
    RecyclerView.Adapter<WorkAdapter.ItemViewHolder>(), Filterable {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout, parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(filterData!![position], context)
    }

    override fun getItemCount(): Int {
        return filterData!!.size
    }


    private var filterData: ArrayList<WorkDto>? = dataList

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterNum: Int = constraint.toString().toInt()
                filterData = if (filterNum == 0) {
                    dataList
                } else {
                    val filteringList = ArrayList<WorkDto>()
                    for (item in dataList) {
                        if (item.part == filterNum - 1) filteringList.add(item)
                    }
                    filteringList
                }
                val filterResult = FilterResults()
                filterResult.values = filterData
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            }

        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val workimg = itemView.findViewById<ImageView>(R.id.workimg)
        private val workName = itemView.findViewById<TextView>(R.id.workname)
        private val workexplanation = itemView.findViewById<TextView>(R.id.workexplanation)
        private val workCategory = itemView.findViewById<TextView>(R.id.workcategory)
        private val storage = Firebase.storage("gs://healthapp-client.appspot.com")

        fun bind(WorkDto: WorkDto, context: Context) {
            workName.text = WorkDto.workname
            workexplanation.text = WorkDto.workcontent
            workimg.setImageResource(WorkDto.workimage)
            println("크런치 : " + R.drawable.core)
            println("마운틴 클레이머 : " + R.drawable.mountain)
            println("플랭크 : " + R.drawable.plank)
            println("숄더프레스 : " + R.drawable.press1)
            println("덤벨 프레스 : " + R.drawable.press2)
            println("덤벨로우 : " + R.drawable.row)
            println("덤벨 스쿼트 : " + R.drawable.squat)
            println("맨몸스쿼트 : " + R.drawable.squat2)
//            getImages("workimg/"+(WorkDto?.workimage).toString(), workimg, context)
            when (WorkDto.part) {
                0 -> workCategory.text = "어깨"
                1 -> workCategory.text = "가슴"
                2 -> workCategory.text = "복부"
                3 -> workCategory.text = "하체"
                4 -> workCategory.text = "팔"
                5 -> workCategory.text = "등"
                6 -> workCategory.text = "전신"
            }

            //itemView 클릭시
            itemView.setOnClickListener {
                Intent(context, WorkListDetail::class.java).apply {
//                    val gotodetailDto = WorkDao.getInstance().getWorkDetail(WorkDto.workseq)
                    putExtra("worklistdata", WorkDto)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
        private fun getImages(path: String, view:ImageView, context:Context){
            storage.getReference(path).downloadUrl.addOnSuccessListener { uri ->
                /*
                if(activity == null)
                    return@addOnSuccessListener
                */
                val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                Glide.with(context).load(uri).apply(requestOptions).into(view)
                println(uri)
            }.addOnFailureListener{
                println("스토리지 다운로드 에러 => ${it.message}")
            }
        }


    }
}


