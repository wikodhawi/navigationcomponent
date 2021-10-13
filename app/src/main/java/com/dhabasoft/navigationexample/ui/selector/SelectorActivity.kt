package com.dhabasoft.navigationexample.ui.selector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhabasoft.navigationexample.databinding.ActivitySelectorBinding
import com.dhabasoft.navigationexample.ui.deeplink.DeeplinkActivity
import com.dhabasoft.navigationexample.ui.main.MainActivity

class SelectorActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectorBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonDeeplink.setOnClickListener { startActivity(Intent(applicationContext, DeeplinkActivity::class.java)) }
        binding.buttonNavigation.setOnClickListener { startActivity(Intent(applicationContext, MainActivity::class.java)) }
    }
}