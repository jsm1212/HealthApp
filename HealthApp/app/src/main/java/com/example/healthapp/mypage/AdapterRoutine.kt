package com.example.healthapp.mypage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.healthapp.R
import com.example.healthapp.work.WorkDao
import com.example.healthapp.work.WorkDto
import com.example.healthapp.work.WorkListDetail
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdapterRoutine(private val context: Context, private val dataList: ArrayList<WorkDto>)
: RecyclerView.Adapter<AdapterRoutine.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val rtImage = itemView.findViewById<ImageView>(R.id.myrtImage)
        private val rtName = itemView.findViewById<TextView>(R.id.myrtName)
        private val rtPart = itemView.findViewById<TextView>(R.id.myrtPart)
        private val storage = Firebase.storage("gs://healthapp-client.appspot.com")

        fun bind(dto: WorkDto, context: Context){
            getImages("workimg/"+(dto.workimage).toString(), rtImage, context)
            rtName.text = dto.workname
            when (dto.part) {
                0 -> rtPart.text = "어깨"
                1 -> rtPart.text = "가슴"
                2 -> rtPart.text = "복부"
                3 -> rtPart.text = "하체"
                4 -> rtPart.text = "팔"
                5 -> rtPart.text = "등"
                6 -> rtPart.text = "전신"
            }
            //itemView 클릭시
            itemView.setOnClickListener {
                Intent(context, WorkListDetail::class.java).apply {
                    val gotodetailDto = WorkDao.getInstance().getWorkDetail(dto.workseq)
                    putExtra("worklistdata", gotodetailDto)
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_routine_recycle, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}