package com.example.proyek_mad

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.proyek_mad.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_user)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewUser) as NavHostFragment
        val navController = navHost.navController
        val bottomNav = binding.bottomNavigationViewUser
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_mi -> {
                    navController.navigate(R.id.action_global_homeFragment)
                    true
                }
                R.id.courses_mi -> {
                    navController.navigate(R.id.action_global_coursesFragment)
                    true
                }
                R.id.offline_mi -> {
                    navController.navigate(R.id.action_global_offlineCoursesFragment)
                    true
                }
                R.id.profile_mi -> {
                    navController.navigate(R.id.action_global_profileFragment)
                    true
                }
                else -> false
            }
        }
    }
}