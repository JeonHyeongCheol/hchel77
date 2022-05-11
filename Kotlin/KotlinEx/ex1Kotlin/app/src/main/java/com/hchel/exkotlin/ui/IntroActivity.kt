package com.hchel.exkotlin.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import com.hchel.exkotlin.R

class IntroActivity : AppCompatActivity() {

    var handler: Handler? = null // Runnable 을 실행하는 클래스
    private var runnable: Runnable = ListRunnable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        setDecor()
    }

    override fun onResume() {
        super.onResume()

        startHandler()
    }

    override fun onPause() {
        super.onPause()

        // Activity Pause 상태일 때는 runnable 도 중단하도록 함
        stopRunnable()
    }


    private fun setDecor() {
        // 안드로이드 앱을 띄우는 Window 의 속성을 변경하여 시스템 UI를 숨기고 전체화면으로 표시하는 코드
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)

            val controller = window.insetsController
            if(controller != null) {
                // 상태바와 네비게이션 사라짐
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                // 특정행동(화면 끝을 스와이프하는 등)을 했을 때에만 시스템 바가 나타나도록 설정
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
        else
            window.decorView.systemUiVisibility =
                    // 콘텐츠를 시스템 바 밑에 보이도록 설정
                    // 시스템 바가 숨겨지거나 보여질 때 콘텐츠 부분이 리사이징 되는 것을 막기 위함
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        // 네비게이션과 상태바 사라짐
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private fun startHandler() {
        // Handler 를 생성하고 2000ms(2초)후 runnable 을 실행
        handler = Handler(Looper.getMainLooper())
        handler?.run {
            // 병렬 실행이 가능한 Thread 를 만들어주는 클래스
            postDelayed(runnable, 2000)
        }
    }

    private fun stopRunnable() {
        handler?.removeCallbacks(runnable)
    }

    class ListRunnable(activity: Activity) : Runnable {

        private var activity: Activity? = null

        init {
            this.activity = activity
        }

        override fun run() {
            val intent = Intent(activity, ListActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}