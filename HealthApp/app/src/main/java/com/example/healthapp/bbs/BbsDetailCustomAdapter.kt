package com.example.healthapp.bbs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDao

class BbsDetailCustomAdapter(private val context: Context, private val replyData: ArrayList<BbsReplyDto>) : RecyclerView.Adapter<ItemViewHolderReply>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolderReply {
        val view = LayoutInflater.from(context).inflate(R.layout.bbs_detail_layout_item, parent, false)
        return  ItemViewHolderReply(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolderReply, position: Int) {
        holder.bindReply(replyData[position], context)
    }

    override fun getItemCount(): Int {
        return replyData.size
    }

}

class ItemViewHolderReply(view: View) : RecyclerView.ViewHolder(view){
    private val replyWriter = view.findViewById<TextView>(R.id.bbsReplyWriter)
    private val replyContent = view.findViewById<TextView>(R.id.bbsReplyContent)
    private val replyUpdate = view.findViewById<TextView>(R.id.bbsReplyUpdate)
    private val replyDelete = view.findViewById<TextView>(R.id.bbsReplyDelete)
    private val replyReply = view.findViewById<TextView>(R.id.bbsReplyReply)

    fun bindReply(replyDto:BbsReplyDto, context: Context){

        // TextView 데이터 세팅
        replyWriter.text = "${replyDto.nickname}(${replyDto.id})"
        replyContent.text = "${replyDto.content}"

        if(replyDto.id != LoginMemberDao.user?.id){
            replyUpdate.visibility = View.INVISIBLE
            replyDelete.visibility = View.INVISIBLE
        }

        replyReply.setOnClickListener{
            val intent = (context as BbsDetailActivity).intent
            context.overridePendingTransition(0, 0) //효과 없애기
            context.b.bbsDetailWriteReply.setText("@" + replyDto.id+" ")
        }
    }
}