package com.example.healthapp.bbs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
    private val replyDelete = view.findViewById<TextView>(R.id.bbsReplyDelete)

    fun bindReply(replyDto:BbsReplyDto, context: Context){

        // TextView 데이터 세팅
        replyWriter.text = "${replyDto.nickname}"
        replyContent.text = "${replyDto.content}"

        if(replyDto.id == LoginMemberDao.user?.id || LoginMemberDao.user?.auth != 1){
            replyDelete.visibility = View.VISIBLE
        }

        replyDelete.setOnClickListener {
            BbsReplyDao.getInstance().deleteReply(replyDto.seq)

            AlertDialog.Builder(context, R.style.MyDialogTheme).setTitle("알림") // 제목
                .setMessage("삭제가 완료되었습니다")   // 메세지
                .setCancelable(false)   // 로그창 밖 터치해도 안꺼짐
                .setPositiveButton("확인"){ _, _ ->
                    //화면 새로고침
                    val intent = (context as BbsDetailActivity).intent
                    context.finish() //현재 액티비티 종료 실시
                    context.overridePendingTransition(0, 0) //효과 없애기
                    context.startActivity(intent) //현재 액티비티 재실행 실시
                    context.overridePendingTransition(0, 0) //효과 없애기
                }.show()
        }
    }
}