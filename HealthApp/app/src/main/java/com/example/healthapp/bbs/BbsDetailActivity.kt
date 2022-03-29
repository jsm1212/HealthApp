package com.example.healthapp.bbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.healthapp.R
import com.example.healthapp.databinding.ActivityBbsDetailBinding
import com.example.healthapp.login.LoginMemberDao

// 슬라이드 될 페이지의 글로벌변수(전역변수)
private var pagesNumber: Int = 0
var imgArr : List<String> = arrayListOf()

class WorkBbsDetailActivity : AppCompatActivity() {

    val b by lazy { ActivityBbsDetailBinding.inflate(layoutInflater) }

    private lateinit var viewPager: ViewPager2

    // 테스트용 댓글 1개
    var emptyReplyList = arrayListOf<BbsReplyDto>(
        BbsReplyDto(0, 0, "", "", "", "작성된 댓글이 없습니다.", "",
            0, 0, 0, 0, 0, 0, "")
    )

    var replyList : ArrayList<BbsReplyDto>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)
        // adapter에서 가져온 데이터 세팅
        val data = intent.getParcelableExtra<BbsDto>("WorkBbsData")
        println("가져온 detail의 seq번호 : " + data?.seq)
        println("가져온 detail의 title : " + data?.title)
        val bbsReplyList = BbsReplyDao.getInstance().getReplyList(data?.seq!!)

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

        }


//
//        // 가져온 게시글정보에서 img가 존재하면 꺼내와서 배열로 저장
//        if(data?.bbsImage != null){
//            val str = data?.bbsImage
//            imgArr = str?.split(",")
//            println(imgArr?.get(0))
//            pagesNumber = imgArr.size
//        }
//        // 테스트
//        b.textView.text = "가져온데이터 : ${data?.title}"
//        // 테스트
//        b.getImgBtn.setOnClickListener {
//            getImages(imgArr?.get(0))
//        }
//
//        viewPager = b.bbsDetailViewPager
//
//        val pagerAdapter = ScreenSlidePagerAdapter(this)
//        viewPager.adapter = pagerAdapter

    }
//    // 파이어베이스 저장공간 위치
//    val storage = Firebase.storage("gs://healthapp-client.appspot.com")
//    // DB에서 꺼내온 imgUri(String)을 이용해 이미지 불러오는 함수
//    fun getImages(path: String){
//        storage.getReference(path).downloadUrl.addOnSuccessListener { uri ->
//            Glide.with(this).load(uri).into(b.imgView)
//        }.addOnFailureListener{
//            println("스토리지 다운로드 에러 => ${it.message}")
//        }
//    }
//
//    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
//        override fun getItemCount(): Int = pagesNumber
//
//        override fun createFragment(position: Int): Fragment = SildeImageFragment()
//    }

}