package com.example.healthapp.bbs

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.healthapp.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SlideImageFragment(val path: String) : Fragment() {

    // 파이어베이스 저장공간 위치
    val storage = Firebase.storage("gs://healthapp-client.appspot.com")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_slide_image, container, false)

        val imgView = view.findViewById<ImageView>(R.id.imageView)

        getImages(path, imgView)

        return view
    }

    // DB에서 꺼내온 imgUri(String)을 이용해 이미지 불러오는 함수
    fun getImages(path: String, view:ImageView){
        storage.getReference(path).downloadUrl.addOnSuccessListener { uri ->
            Glide.with(this).load(uri).into(view)
        }.addOnFailureListener{
            println("스토리지 다운로드 에러 => ${it.message}")
        }
    }

}
