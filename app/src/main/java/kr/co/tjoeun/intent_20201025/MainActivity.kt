package kr.co.tjoeun.intent_20201025

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    클래스 정의부분 바로 밑에 적는 (만드는) 변수 : 멤버변수
//    fun 안에서 적는 (만드는) 변수 : 지역변수 - Local변수

//    1000이라는 숫자만 보고서는 의미파악 불가. => 1000을 변수에 담아서, 의미를 이해할 수 있게 도와주자.
    val REQ_FOR_DATA = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveToOtherActivityBtn.setOnClickListener {

//            다른 화면 (MyFirstActivity) 으로 이동

            val myIntent = Intent(this, MyFirstActivity::class.java)
            startActivity(myIntent)

        }

        sendDataBtn.setOnClickListener {

//            다른 화면 (MySecondActivity) 으로 이동
//            가는길에 데이터 (입력한 내용)를 들고 이동.

            val inputContent = contentEdt.text.toString()

            val myIntent = Intent(this, MySecondActivity::class.java) // 비행기 티켓
            myIntent.putExtra("content", inputContent)
            startActivity(myIntent) // 실제 탑승 행위

        }

        dialBtn.setOnClickListener {

//            전화를 어디에(입력값추출) 연결할지, 연결 정보(Uri)를 변수에 저장.
            val inputPhoneNum = phoneEdt.text.toString()
//            tel: + 입력한폰번 = "tel:${입력한폰번}"
            val myUri = Uri.parse("tel:${inputPhoneNum}")

//            어느 화면으로 이동할지? Intent
            val myIntent = Intent(Intent.ACTION_DIAL, myUri)

            startActivity(myIntent)

        }

        callBtn.setOnClickListener {

            val inputPhoneNum = phoneEdt.text.toString()
            val myUri = Uri.parse("tel:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_CALL, myUri)
            startActivity(myIntent)

        }

        changeTextBtn.setOnClickListener {
            val myIntent = Intent(this, EditDataActivity::class.java)
            startActivityForResult(myIntent, REQ_FOR_DATA)
        }
        smsBtn.setOnClickListener {
            val inputPhoneNum = phoneEdt.text.toString()
            val myUri = Uri.parse("smsto:${inputPhoneNum}")
            val myIntent = Intent(Intent.ACTION_SENDTO, myUri)
            myIntent.putExtra("sms_body", "[홍보문자] 미리 작성한 문구")
            startActivity(myIntent)
        }

        naverBtn.setOnClickListener {

            val myUri = Uri.parse("https://naver.com")
            val myIntent = Intent(Intent.ACTION_VIEW, myUri)
            startActivity(myIntent)
            //광고, 이용약관, 스토어
        }

        kakaoStoreBtn.setOnClickListener {
            //업데이트용
            val myUri = Uri.parse("market://details?id=com.kakao.talk")
            val myIntent = Intent(Intent.ACTION_VIEW, myUri)
            startActivity(myIntent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_FOR_DATA) {

            if (resultCode == Activity.RESULT_OK) {

                val newInputData = data?.getStringExtra("newData")
                dataTxt.text = newInputData

            }

        }

    }

}