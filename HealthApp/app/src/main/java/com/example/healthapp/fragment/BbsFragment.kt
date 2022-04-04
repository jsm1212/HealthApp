package com.example.healthapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.*

class BbsFragment(val activity:Context) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_bbs, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val workBbsList = BbsDao.getInstance().getBbsList()

        val workBbsAdapter = WorkBbsCustomAdapter(activity, workBbsList!!)

        recyclerView.adapter = workBbsAdapter

        val layout = LinearLayoutManager(activity)
        recyclerView.layoutManager = layout

        recyclerView.setHasFixedSize(true)

        val goWorkBbsWirte = view.findViewById<Button>(R.id.goWorkBbsWirte)

        goWorkBbsWirte.setOnClickListener {
            val intent = Intent(activity, BbsWriteActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}