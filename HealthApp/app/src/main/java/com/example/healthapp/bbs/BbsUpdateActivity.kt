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

    // 스토리지 버킷 연결코드
    private val storage = Firebase.storage("gs://healthapp-client.appspot.com")
    // 이미지 uri 배열
    private var imgUriArr = arrayListOf<String>()
    private var newImgUriArr = arrayListOf<String>()
    // 이미지 String 타입
    private var images: String? = ""

    val REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        // 게시글 내용에서 가져온 게시글 데이터
        val data = intent.getParcelableExtra<BbsDto>("BbsInfo")
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! : " + data?.bbsImage)

        // 넘어온 String 형태의 image uri 값을 배열로 변환
        if(data?.bbsImage != null) {
            val str = data?.bbsImage
            imgArr = str?.split(",")
            for(i in imgArr){
                imgUriArr.add(i)
            }
            b.BbsUpdateSelectImg.text = imgUriArr[0] + "..."   // 첫번째 이미지
        }

        // 게시글 수정페이지 수정할 View세팅
        b.BbsUpdateTitle.setText(data?.title)           // 제목
        b.BbsUpdateContent.setText(data?.content)       // 내용

        // 사진선택 버튼 터치 시 이벤트
        b.BbsUpdateUploadBtn.setOnClickListener {
            // 사진 다중선택 알림창 띄우기
            AlertDialog.Builder(this).setTitle("알림") // 제목
                .setMessage("사진을 길게 눌러 여러개를 선택할 수 있습니다.(최대 10개)")   // 메세지
                .setCancelable(false)   // 로그창 밖 터치해도 안꺼짐
                .setPositiveButton("사진선택하기"){ _, _ ->
                    getImgFromGallery()
                }.show()
            b.BbsUpdateUploadBtn.visibility = View.INVISIBLE
            //b.reselectUpdateBtn.visibility = View.VISIBLE
        }
        //bbsImage=images/aaa_1648614491834.jpeg,images/aaa_1648614491863.jpeg
        // 목록으로 버튼 클릭시 이벤트
        b.goToDetailBtn.setOnClickListener {
            onBackPressed()
        }
        // 수정하기 버튼 클릭시 이벤트트
        b.BbsUpdateBtn.setOnClickListener {
            val seq = data?.seq
            val title = b.BbsUpdateTitle.text.toString()
            val content = b.BbsUpdateContent.text.toString()
            if(newImgUriArr.isEmpty()){
                images = uriToString(imgUriArr)
            }else{
                images = uriToString(newImgUriArr)
            }

            BbsDao.getInstance().updateBbs(
                BbsDto(seq, "", "", title, content,"",
                    0, 0, 0, 0, 0, 0, images)
            )
            Toast.makeText(this,"수정이 완료되었습니다.", Toast.LENGTH_LONG).show()
            // 새롭게 업로드된 이미지가 있을때 기존 이미지 삭제
            if(newImgUriArr.isNotEmpty() && imgUriArr != null){
                deleteImg(imgUriArr)
            }

//            super.onBackPressed()
//
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
                if (newImgUriArr != null){     // 새로업로드했던 이미지가 있으면 삭제
                    deleteImg(newImgUriArr)
                }
                // 게시글로 이동
                super.onBackPressed()
            }.setNegativeButton("취소"){_, _ -> } // 취소 누를시 이벤트 없음
            .show()
    }

    // 첨부할 사진 선택 시작함수(갤러리이동)
    fun getImgFromGallery() {
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }
    // 갤러리에서 선택한 사진들 파일 별 처리 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            // 다중 이미지 선택시
            if(data?.clipData != null) {
                val count = data.clipData?.itemCount

                if(count!! > 10) {
                    Toast.makeText(applicationContext, "사진은 최대 10장까지 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    return
                }
                for( i in 0 until count!!) {
                    val imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                    //permissionLauncher(imageUri)
                    permissionLauncher(imageUri)
                }
            } else if (data?.data != null) {    // 단일 이미지 선택시
                var imageUri: Uri = data.data!!
                permissionLauncher(imageUri)
            }
        }
    }
    // 로그인한 유저 아이디
    val userId = LoginMemberDao.user?.id

    // 권한 요청 런처
    fun permissionLauncher(uri: Uri){
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(!isGranted) {
                Toast.makeText(baseContext, "외부 저장소 읽기 권한을 승인해야 사용할 수 있습니다", Toast.LENGTH_LONG).show()
            } else {
                uploadImage(uri)
            }
        }
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    // 새로운 이미지 업로드 함수(업로드된 이미지가 없을때)
    fun uploadImage(uri: Uri) {
        // 1. 경로 + 사용자ID + 밀리초로 파일주소 만들기
        val fullPath = makeFilePath("images", userId!!, uri)
        // 2. 스토리지에 저장할 경로 설정
        val imageRef = storage.getReference(fullPath)
        // 3. 업로드 태스크 생성
        val uploadTask = imageRef.putFile(uri)

        // 4. 업로드 실행 및 결과 확인
        uploadTask.addOnFailureListener{
            Log.d("스토리지", "실패=>${it.message}")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d("스토리지", "성공 주소=>${fullPath}")     // 5. 경로를 DB에 저장하고 사용
        }
    }

    // 파일 전체 경로생성 함수
    fun makeFilePath(path:String, userId:String, uri: Uri) : String {
        // 마임타입 예) images/jpeg
        val mimeType = contentResolver.getType(uri)?:"none"
        // 확장자 예) jpeg
        val ext = mimeType.split("/")[1]
        // 시간값 예) 1232131241312
        val timeSuffix = System.currentTimeMillis()
        // 완성
        val filename = "${path}/${userId}_${timeSuffix}.${ext}"  // 예) 경로/사용자ID_1232131241312.jpeg

        newImgUriArr.add(filename)
        b.BbsUpdateSelectImg.text = newImgUriArr[0] + "..."
        return filename
    }

    // uri 주소 String 형식으로 전환 함수
    fun uriToString(uriStr:ArrayList<String>) : String{
        var imgs = ""
        if(uriStr.size == 1){
            imgs = uriStr[0]
        }else if(uriStr.size > 1){
            imgs = uriStr.joinToString(separator = ",", limit = uriStr.size)
        }
        println(imgs)
        return imgs
    }

    private fun deleteImg(uriStr:ArrayList<String>){
        val storageRef = storage.reference
        for(i in uriStr){
            val desertRef = storageRef.child(i)
            desertRef.delete().addOnSuccessListener {
                println("파일삭제 성공")
            }.addOnFailureListener{
                println("파일삭제 실패")
                println(i)
            }
        }
    }
}