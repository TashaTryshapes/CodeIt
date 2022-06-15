package com.rr.testproject.mainUI

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rr.testproject.R
import com.rr.testproject.base.BaseActivity
import com.rr.testproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_layout.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    var from = ""
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navController: NavController

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getVmClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.data = viewModel
        binding.lifecycleOwner = this

        init()
    }

    fun init() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        from = "HOME"
        navController = Navigation.findNavController(this, R.id.fragment)

        img_openSideDrawer.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.END)
        }

        imgCancel.setOnClickListener {
            closeDrawer()
        }
    }

    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragment)
        closeDrawer()
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                if (fragment is HomeFragment) {
                    if (from == "HOME") {
                        finishAffinity()
                    } else {
                        finish()
                    }
                } else {
                    super.onBackPressed()
                }
            }
        }
    }

    private fun closeDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
            binding.drawerLayout.closeDrawer(GravityCompat.END)
        }
    }
}