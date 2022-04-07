package com.example.healthapp.bbs

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.healthapp.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SlideImageFragment(private val path: String) : Fragment() {

    // 파이어베이스 저장공간 위치
    private val storage = Firebase.storage("gs://healthapp-client.appspot.com")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_slide_image, container, false)

        val imgView = view.findViewById<ImageView>(R.id.imageView)

//        //테스트
//        var st:StorageReference
//        st = FirebaseStorage.getInstance().getReference()
//        var banner: StorageReference = st.child(path).get

        getImages(path, imgView)
        return view
    }

    // DB에서 꺼내온 imgUri(String)을 이용해 이미지 불러오는 함수
    private fun getImages(path: String, view:ImageView){
        storage.getReference(path).downloadUrl.addOnSuccessListener { uri ->
            if(activity == null){
                return@addOnSuccessListener
            }
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this).load(uri).apply(requestOptions).into(view)
            println(uri)
        }.addOnFailureListener{
            println("스토리지 다운로드 에러 => ${it.message}")
        }
    }
}
