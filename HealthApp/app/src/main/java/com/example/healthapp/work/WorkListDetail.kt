package com.example.healthapp.work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDao
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class WorkListDetail : AppCompatActivity() {
    private val storage = Firebase.storage("gs://healthapp-client.appspot.com")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_list_detail)

        getSupportActionBar()!!.setIcon(R.drawable.appbar)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setElevation(0F)

        val data = intent.getParcelableExtra<WorkDto>("worklistdata")

        val backbtn = findViewById<Button>(R.id.backbtn)
        val workdetitle = findViewById<TextView>(R.id.workdetitle)
        val workexplanation = findViewById<TextView>(R.id.workcontent)
        val workimg = findViewById<ImageView>(R.id.workdeimg)
        val heart =findViewById<ImageView>(R.id.hearticon)

        workdetitle.text=data?.workname
        workexplanation.text=data?.workdetail
        workexplanation.setMovementMethod(ScrollingMovementMethod())
        println("+++++++++++++++++++++++"+data?.workimage)
//        getImages("workimg/"+(data?.workimage).toString(), workimg)
        workimg.setImageResource(data!!.workimage)

        // 좋아요 이미지 보여주기
        var likeOK = WorkDao.getInstance().likeCountWork_M(LikeWorkDto(data!!.workseq, LoginMemberDao.user?.id!!))
        if(likeOK == "notCount"){
            heart.setImageResource(R.drawable.fillheart)
        }else{
            heart.setImageResource(R.drawable.emtyheart)
        }

        // 좋아요 클릭
        heart.setOnClickListener {
            val like = WorkDao.getInstance().likeCountWork_M(LikeWorkDto(data!!.workseq, LoginMemberDao.user?.id!!))
            if(like == "notCount"){
                heart.setImageResource(R.drawable.emtyheart)
                WorkDao.getInstance().likeCountCancelWork_M(LikeWorkDto(data!!.workseq, LoginMemberDao.user?.id!!))
            }else{
                heart.setImageResource(R.drawable.fillheart)
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