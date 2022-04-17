package com.example.healthapp.work

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.healthapp.R
import com.example.healthapp.fragment.WorklistFragment

class WorkDetail : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        //val data = intent.getParcelableExtra<WorkDto>("worklistdata")

        val backbtn = view?.findViewById<Button>(R.id.backbtn)
        val workdetitle = view?.findViewById<TextView>(R.id.workdetitle)
        val workcontent = view?.findViewById<TextView>(R.id.workcontent)
        val workimg = view?.findViewById<ImageView>(R.id.workdeimg)
        val heart = view?.findViewById<ImageView>(R.id.hearticon)









        backbtn?.setOnClickListener {
            val a = Intent(activity, WorklistFragment::class.java)
            startActivity(a)
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        return view
    }
}