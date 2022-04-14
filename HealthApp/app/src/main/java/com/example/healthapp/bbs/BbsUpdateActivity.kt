package com.example.healthapp.bbs

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.healthapp.R
import com.example.healthapp.databinding.ActivityBbsUpdateBinding
import com.example.healthapp.login.LoginMemberDao
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BbsUpdateActivity : AppCompatActivity() {

    val b by lazy { ActivityBbsUpdateBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        // 게시글 내용에서 가져온 게시글 데이터
        val data = intent.getParcelableExtra<BbsDto>("BbsInfo")
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! : " + data?.bbsImage)



        // 게시글 수정페이지 수정할 View세팅
        b.BbsUpdateTitle.setText(data?.title)           // 제목
        b.BbsUpdateContent.setText(data?.content)       // 내용
        // 이미지
        if(data?.bbsImage != null) {
            b.BbsUpdateSelectImg.text = data.bbsImage.split(",")[0]
        }else{
            b.BbsUpdateSelectImg.visibility = View.GONE
        }

        // 목록으로 버튼 클릭시 이벤트
        b.goToDetailBtn.setOnClickListener {
            val intent = Intent(this, BbsDetailActivity::class.java)
            startActivity(intent)
        }
        // 수정하기 버튼 클릭시 이벤트트
        b.BbsUpdateBtn.setOnClickListener {
            val seq = data?.seq
            val title = b.BbsUpdateTitle.text.toString()
            val content = b.BbsUpdateContent.text.toString()
            val images: String? = data?.bbsImage

            BbsDao.getInstance().updateBbs(
                BbsDto(seq, "", "", title, content,"",
                    0, 0, 0, 0, 0, 0, images)
            )
            Toast.makeText(this,"수정이 완료되었습니다.", Toast.LENGTH_LONG).show()

            val intent = Intent(this, BbsDetailActivity::class.java)
            startActivity(intent)
        }

    }   // 여기까지가 onCreate

    // 뒤로가기 버튼 터치시 이벤트
    override fun onBackPressed() {
        AlertDialog.Builder(this).setTitle("알림") // 제목
            .setMessage("게시글로 돌아가시겠습니까??\n수정된 글은 저장되지 않습니다")   // 메세지
            .setCancelable(false)   // 로그창 밖 터치해도 안꺼짐
            .setPositiveButton("확인"){ _, _ ->   // 확인 누를시
                // 게시글로 이동
                val intent = Intent(this, BbsDetailActivity::class.java)
                startActivity(intent)
            }.setNegativeButton("취소"){_, _ -> } // 취소 누를시 이벤트 없음
            .show()
    }


}