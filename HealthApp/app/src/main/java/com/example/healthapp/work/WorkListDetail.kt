package com.example.healthapp.work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.healthapp.R
import com.example.healthapp.fragment.WorklistFragment
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class WorkListDetail : AppCompatActivity() {
    private val storage = Firebase.storage("gs://healthapp-client.appspot.com")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_list_detail)
        val data = intent.getParcelableExtra<WorkDto>("worklistdata")

        val backbtn = findViewById<Button>(R.id.backbtn)
        val workdetitle = findViewById<TextView>(R.id.workdetitle)
        val workexplanation = findViewById<TextView>(R.id.workcontent)
        val workimg = findViewById<ImageView>(R.id.workdeimg)
        val heart =findViewById<ImageView>(R.id.hearticon)

        workdetitle.text=data?.workname
        workexplanation.text=data?.workcontent
        println("+++++++++++++++++++++++"+data?.workimage)
        getImages("workimg/"+(data?.workimage).toString(), workimg)
        var abc:Int=0
        heart.setOnClickListener {
            if (abc==0) {
                heart.setImageResource(R.drawable.fillheart)
                abc=1
            }
            else{
                heart.setImageResource(R.drawable.emtyheart)
                abc=0
            }
        }
        backbtn?.setOnClickListener {
            super.onBackPressed()
        }
    }
     private fun getImages(path: String, view:ImageView){
        storage.getReference(path).downloadUrl.addOnSuccessListener { uri ->
            /*
            if(activity == null)
                return@addOnSuccessListener
            */
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this).load(uri).apply(requestOptions).into(view)
            println(uri)
        }.addOnFailureListener{
            println("스토리지 다운로드 에러 => ${it.message}")
        }
    }
}