package com.dhabasoft.navigationexample.ui.deeplink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.databinding.ActivityDeeplinkBinding
import com.dhabasoft.navigationexample.databinding.ActivityMainBinding

class DeeplinkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeeplinkBinding
    private lateinit var navControllerDeeplink: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeeplinkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.navHostDeeplink) as NavHostFragment? ?: return
        navControllerDeeplink = host.navController
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navControllerDeeplink.handleDeepLink(intent)
    }
}