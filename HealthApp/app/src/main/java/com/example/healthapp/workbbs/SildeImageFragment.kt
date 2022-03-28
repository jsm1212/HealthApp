package com.example.healthapp.workbbs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.healthapp.R
import com.example.healthapp.databinding.FragmentSlideImageBinding

class SildeImageFragment : Fragment() {

    val b by lazy { FragmentSlideImageBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        b.imageView.setImageResource(R.drawable.home)

        return inflater.inflate(R.layout.fragment_slide_image, container, false)
    }



}
