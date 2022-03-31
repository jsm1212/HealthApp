package com.example.healthapp

import android.os.CountDownTimer
import android.widget.TextView
class Timer(){
    fun countDown(time:String, view:TextView){
        // Timer().countDown("000000", textView) 이렇게 사용
        //                  시간 6글자, 타이머 보여줄 textView변수명
        //        000030 -> 30초 / 003000 -> 30분
        println("확인!!!!!!!!!! 타이머 입장!!!!!")
        var timeView = view

        var conversionTime: Long = 0 // 1초는 1000
        var getHour = time.substring(0, 2)
        var getMin = time.substring(2, 4)
        var getSecond = time.substring(4, 6)

        if (getHour.substring(0, 1) === "0") {
            getHour = getHour.substring(1, 2)
        }
        if (getMin.substring(0, 1) === "0") {
            getMin = getMin.substring(1, 2)
        }
        if (getSecond.substring(0, 1) === "0") {
            getSecond = getSecond.substring(1, 2)
        }

        // 변환시간
        conversionTime = getHour.toLong() * 1000 * 3600 + getMin.toLong() * 60 * 1000 + getSecond.toLong() * 1000

        object : CountDownTimer(conversionTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var hour = (millisUntilFinished / (60 * 60 * 1000)).toString()
                val getMin = millisUntilFinished - millisUntilFinished / (60 * 60 * 1000)
                var min = (getMin / (60 * 1000)).toString()
                var second = (getMin % (60 * 1000) / 1000).toString()
                val millis = (getMin % (60 * 1000) % 1000).toString()

                if (hour.length == 1) {
                    hour = "0$hour"
                }
                if (min.length == 1) {
                    min = "0$min"
                }
                if (second.length == 1) {
                    second = "0$second"
                }
                timeView.setText("$hour:$min:$second")
            }
            override fun onFinish() {
                timeView.setText("시간종료!")
            }
        }.start()
    }
}