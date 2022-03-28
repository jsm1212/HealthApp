package com.example.healthapp.workbbs

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.healthapp.R
import com.example.healthapp.databinding.ActivityWorkBbsDetailBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

// 슬라이드 될 페이지의 글로벌변수(전역변수)
private var pagesNumber: Int = 0
var imgArr : List<String> = arrayListOf()

class WorkBbsDetailActivity : AppCompatActivity() {

    val b by lazy { ActivityWorkBbsDetailBinding.inflate(layoutInflater) }

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        // adapter에서 가져온 데이터 세팅
        val data = intent.getParcelableExtra<WorkBbsDto>("WorkBbsData")

        // 가져온 게시글정보에서 img가 존재하면 꺼내와서 배열로 저장
        if(data?.bbsImage != null){
            val str = data?.bbsImage
            imgArr = str?.split(",")
            println(imgArr?.get(0))
            pagesNumber = imgArr.size
        }
        // 테스트
        b.textView.text = "가져온데이터 : ${data?.title}"
        // 테스트
        b.getImgBtn.setOnClickListener {
            getImages(imgArr?.get(0))
        }

        viewPager = b.bbsDetailViewPager

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter

    }
    // 파이어베이스 저장공간 위치
    val storage = Firebase.storage("gs://healthapp-client.appspot.com")
    // DB에서 꺼내온 imgUri(String)을 이용해 이미지 불러오는 함수
    fun getImages(path: String){
        storage.getReference(path).downloadUrl.addOnSuccessListener { uri ->
            Glide.with(this).load(uri).into(b.imgView)
        }.addOnFailureListener{
            println("스토리지 다운로드 에러 => ${it.message}")
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = pagesNumber

        override fun createFragment(position: Int): Fragment = SildeImageFragment()
    }

}