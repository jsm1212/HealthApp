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
import com.example.healthapp.databinding.ActivityBbsWriteBinding
import com.example.healthapp.login.LoginMemberDao
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BbsWriteActivity : AppCompatActivity() {

    val b by lazy { ActivityBbsWriteBinding.inflate(layoutInflater) }

    val REQUEST_CODE = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        // 사진선택 버튼 클릭시 이벤트(다중사진 업로드)
        b.imageUploadBtn.setOnClickListener {
            // 사진 다중선택 알림창 띄우기
            AlertDialog.Builder(this).setTitle("알림") // 제목
            .setMessage("사진을 길게 눌러 여러개를 선택할 수 있습니다.(최대 10개)")   // 메세지
            .setCancelable(false)   // 로그창 밖 터치해도 안꺼짐
            .setPositiveButton("사진선택하기"){ _, _ ->
                getImgFromGallery()
            }.show()
            // 사진선택 버튼 비활성화
            b.imageUploadBtn.visibility = View.INVISIBLE
            // 사진 다시선택 버튼 활성화
            b.WorkBbsReSelectImg.visibility = View.VISIBLE
        }
        // 다시선택 버튼 클릭시 이벤트(기존 업로드데이터 삭제 후 다시 다중업로드)
        b.WorkBbsReSelectImg.setOnClickListener {
            // 앞에 업로드 됐던 이미지 삭제
            deleteImg()
            // 이미지 리스트 클리어
            imgUriArr.clear()
            // 이미지 업로드 함수 실행
            getImgFromGallery()
        }
        // 작성하기 버튼 클릭시 이벤트
        b.WorkBbsWriteBtn.setOnClickListener {
            // 서버로 가져갈 데이터
            val id = LoginMemberDao.user?.id
            val nickname = LoginMemberDao.user?.nickname
            val title = b.WorkBbsWriteTitle.text.toString()
            val content = b.WorkBbsWriteContent.text.toString()
            val images = uriToString()

            // 서버로 데이터 전달 후 Toast 노출
            BbsDao.getInstance().writebbs(
                BbsDto(0, id, nickname, title, content,"",
                0, 0, 0, 0, 0, 0, images)
            )
            Toast.makeText(this,"작성이 완료되었습니다.", Toast.LENGTH_LONG).show()

            // 게시글 목록으로 이동
            val i = Intent(this, WorkActivity::class.java)
            startActivity(i)
        }

        // 목록으로 버튼 클릭시 이벤트
        b.goToListBtn.setOnClickListener {
            onBackPressed()
        }
    }

    // 뒤로가기버튼 터치 이벤트
    override fun onBackPressed() {
        AlertDialog.Builder(this).setTitle("알림") // 제목
            .setMessage("게시글 목록으로 돌아가시겠습니까??\n작성된 글은 저장되지 않습니다")   // 메세지
            .setCancelable(false)   // 로그창 밖 터치해도 안꺼짐
            .setPositiveButton("확인"){ _, _ ->   // 확인 누를시
                if (imgUriArr != null){     // 업로드했던 이미지가 있으면 삭제
                    deleteImg()
                }
                // 게시글 목록으로 이동
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
    // 업로드한 이미지들
    var imgUriArr = arrayListOf<String>()
    // 로그인한 유저 아이디
    val userId = LoginMemberDao.user?.id
    // 스토리지 버킷 연결 코드
    val storage = Firebase.storage("gs://healthapp-client.appspot.com")

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

    // 이미지 업로드 함수
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
    fun makeFilePath(path:String, userId:String, uri:Uri) : String {
        // 마임타입 예) images/jpeg
        val mimeType = contentResolver.getType(uri)?:"none"
        // 확장자 예) jpeg
        val ext = mimeType.split("/")[1]
        // 시간값 예) 1232131241312
        val timeSuffix = System.currentTimeMillis()
        // 완성
        val filename = "${path}/${userId}_${timeSuffix}.${ext}"  // 예) 경로/사용자ID_1232131241312.jpeg

        imgUriArr.add(filename)
        b.WorkSelectedImg.text = imgUriArr[0] + "..."
        return filename
    }

    // uri 주소 String 형식으로 전환 함수
    fun uriToString() : String{
        var imgs = ""
        if(imgUriArr.size == 1){
            imgs = imgUriArr[0]
        }else if(imgUriArr.size > 1){
            imgs = imgUriArr.joinToString(separator = ",", limit = imgUriArr.size)
        }
        println(imgs)
        return imgs
    }

    // 업로드된 이미지 삭제 함수
    fun deleteImg(){
        val storageRef = storage.reference
        for(i in imgUriArr){
            val desertRef = storageRef.child(i)
            desertRef.delete().addOnSuccessListener {
                println("파일삭제 성공")
            }.addOnFailureListener{
                println("파일삭제 실패")
            }
        }
    }

}



