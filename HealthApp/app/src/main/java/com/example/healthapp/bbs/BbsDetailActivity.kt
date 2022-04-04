package com.example.healthapp.bbs

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.healthapp.R
import com.example.healthapp.databinding.ActivityBbsDetailBinding
import com.example.healthapp.databinding.ActivityWorkBinding
import com.example.healthapp.fragment.BbsFragment
import com.example.healthapp.fragment.WorklistFragment
import com.example.healthapp.login.LoginMemberDao

// 슬라이드 될 페이지의 글로벌변수(전역변수)
var imgArr : List<String> = arrayListOf()

class BbsDetailActivity : AppCompatActivity() {

    val b by lazy { ActivityBbsDetailBinding.inflate(layoutInflater) }
    // 아직 구현하지 못한 기능
    /*
    1. 뷰페이저기능
    2. 좋아요기능
    4. 댓글 수정, 삭제, 답글기능
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)
        // 서버에서 가져온 데이터 세팅
        val data = BbsDao.getInstance().bbsDetail_non(BbsDao.bbsSeq!!, LoginMemberDao.user?.id!!)
//            intent.getParcelableExtra<BbsDto>("WorkBbsData")

        // -----------------------------------게시글-----------------------------------
        // 작성일 split
        val dateArr = data?.wdate?.split(":")

        // 가져온 데이터로 TextView세팅
        b.bbsDetailTitle.text = data?.title                                  // 게시글 제목
        b.bbsDetailWriter.text = "${data?.nickname}"            // 게시글 작성자
        b.bbsDetailWdate.text = "${dateArr!![0]}:${dateArr!![1]}"            // 게시글 작성일
        b.bbsDetailRcLike.text = "❤${data?.bbsLike} / ${data?.readcount}"   // 게시글 조회수/좋아요
        b.bbsDetailContent.text = data?.content
        if(LoginMemberDao.user?.id == data?.id){
            b.bbsUpdateView.visibility = View.VISIBLE
            b.bbsDeleteView.visibility = View.VISIBLE
        }
        // 이미지 슬라이드
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        b.viewPager.adapter = pagerAdapter

        // 좋아요 터치시 이벤트(좋아요누르기전)
        b.bbsDetailRcLike.setOnClickListener {
            // 코드
            BbsDao.getInstance().likeCount(BbsDao.bbsSeq!!)
        }
        // 목록으로 클릭시 이벤트
        b.goToBbsList.setOnClickListener {
            val intent = Intent(this, WorkActivity::class.java)
            startActivity(intent)
        }
        // 수정 클릭시 이벤트
        b.bbsUpdateView.setOnClickListener{
            //화면 새로고침
            this.startActivity(intent) //현재 액티비티 재실행 실시
            this.overridePendingTransition(0, 0) //효과 없애기

            val intent = Intent(this, BbsUpdateActivity::class.java)
            intent.putExtra("BbsInfo", data)
            startActivity(intent)
        }
        // 삭제 클릭시 이벤트
        b.bbsDeleteView.setOnClickListener {
            AlertDialog.Builder(this).setTitle("게시글 삭제")
                .setMessage("주의!\n게시글을 삭제하시겠습니까?\n삭제한 게시글은 복구할 수 없습니다.").setCancelable(false)
                .setPositiveButton("확인"){ _, _ ->   // 확인 누를시 이벤트
                    BbsDao.getInstance().deleteBbs(data?.seq!!)
                    AlertDialog.Builder(this).setMessage("삭제가 완료되었습니다").setCancelable(false)
                        .setPositiveButton("확인"){ _, _ ->   // 확인 누를시 이벤트
                            val i = Intent(this, WorkActivity::class.java)
                            startActivity(i)
                        }.show()
                }.setNegativeButton("취소"){_, _ -> } // 취소 누를시 이벤트 없음
                .show()
        }

        // -----------------------------------댓글-----------------------------------
        // 댓글목록 가져오기
        val bbsReplyList = BbsReplyDao.getInstance().getReplyList(BbsDao.bbsSeq!!)
        // 작성된 댓글이 없을때 TextView문구 노출
        if(bbsReplyList!!.isEmpty()){
            b.nonReply.visibility = View.VISIBLE
        }

        // 댓글 recyclerView세팅
        val replyAdapter = BbsDetailCustomAdapter(this, bbsReplyList!!)
        b.bbsDetailReplyRv.adapter = replyAdapter
        val layout = LinearLayoutManager(this)
        b.bbsDetailReplyRv.layoutManager = layout
        b.bbsDetailReplyRv.setHasFixedSize(true)

        // 댓글작성(등록하기버튼 클릭시 이벤트)
        b.writeReplyBtn.setOnClickListener {
            // 서버로 가져갈 데이터
            val replyNum:Int? = data?.seq!!
            val id:String? = LoginMemberDao.user?.id
            val nickname:String? = LoginMemberDao.user?.nickname
            val title:String? = "title"
            val content:String? = b.bbsDetailWriteReply.text.toString()
            val replyImg:String? = ""

            // 서버로 데이터 전달 후 Toast 노출
            BbsReplyDao.getInstance().writeReply(
                BbsReplyDto(
                0, replyNum!!, id!!, nickname!!, title!!, content!!, "",
                    0, 0, 0, 0, 0, 0, replyImg!!)
            )
            Toast.makeText(this,"작성이 완료되었습니다.", Toast.LENGTH_LONG).show()

            reLoadView()    // 화면 새로고침
        }

        // 키보드 나올때 화면 위로 밀어올리기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)





        // 가져온 게시글정보에서 img가 존재하면 꺼내와서 배열로 저장
        if(data?.bbsImage != null){
            val str = data?.bbsImage
            imgArr = str?.split(",")
        }

    }

    fun reLoadView(){
        //화면 새로고침
        val intent = (this as Activity).intent
        this.finish() //현재 액티비티 종료 실시
        this.overridePendingTransition(0, 0) //효과 없애기
        this.startActivity(intent) //현재 액티비티 재실행 실시
        this.overridePendingTransition(0, 0) //효과 없애기
    }
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = imgArr.size

        override fun createFragment(position: Int): Fragment {

            return when(position) {
                position -> SlideImageFragment(imgArr[position])
                else -> SlideImageFragment(imgArr[imgArr.size])
            }
        }
    }

}