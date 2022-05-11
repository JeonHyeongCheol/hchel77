package com.hchel.exkotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hchel.exkotlin.R
import com.hchel.exkotlin.databinding.ActivityDetailBinding

class  DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        // fab 버튼 클릭 시 DetailActivity 로 이동하는 코드
        binding.fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, ListActivity::class.java)
            startActivity(intent)
        }
    }
}