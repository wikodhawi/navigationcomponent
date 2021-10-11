package com.dhabasoft.navigationexample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.dhabasoft.navigationexample.databinding.ActivityMainBinding
import com.dhabasoft.navigationexample.ui.dashboard.DashboardFragment
import com.dhabasoft.navigationexample.ui.home.HomeFragment
import com.dhabasoft.navigationexample.ui.notifications.NotificationsFragment
import com.dhabasoft.navigationexample.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentList = getFragments()
    private lateinit var viewModel: MainViewModel

    companion object {
        const val POSITION_HOME = 0
        const val POSITION_DASHBOARD = 1
        const val POSITION_NOTIFICATIONS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)
        setContentView(binding.root)
        openFragment(fragmentList[POSITION_HOME])

        binding.navView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> {
                    openFragment(fragmentList[POSITION_HOME])
                    viewModel.position = POSITION_HOME
                }
                R.id.navigation_dashboard -> {
                    openFragment(fragmentList[POSITION_DASHBOARD])
                    viewModel.position = POSITION_DASHBOARD
                }
                R.id.navigation_notifications -> {
                    openFragment(fragmentList[POSITION_NOTIFICATIONS])
                    viewModel.position = POSITION_NOTIFICATIONS
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun getFragments() : List<Fragment> = listOf(HomeFragment(), DashboardFragment(), NotificationsFragment())

    private fun openFragment(fragment: Fragment) {
        Handler().post(Runnable {
            val backStateName = fragment.javaClass.name
            val manager = supportFragmentManager
            val ft = manager.beginTransaction()
            val currentFrag = manager.findFragmentByTag(backStateName)
            if (currentFrag != null && manager.fragments.size > 0) {
                showSelectedFragment(fragment, manager, ft)
            } else {
                ft.add(
                    R.id.container,
                    fragment,
                    backStateName
                ) // add fragment if there re not registered on fragmentManager
                showSelectedFragment(fragment, manager, ft)
            }
            ft.commitNowAllowingStateLoss()
        })
    }

    private fun showSelectedFragment(
        fragment: Fragment,
        manager: FragmentManager,
        ft: FragmentTransaction
    ) {
        for (i in manager.fragments.indices) {
            val frag = manager.fragments[i]
            if (frag.javaClass.name.equals(fragment.javaClass.name, ignoreCase = true)) {
                ft.show(frag) // only show fragment what you want to show
                frag.userVisibleHint = true
            } else {
                ft.hide(frag) // hide all fragment
            }
        }
    }
}