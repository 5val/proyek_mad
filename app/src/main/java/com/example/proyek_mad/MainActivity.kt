package com.example.proyek_mad

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.proyek_mad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
//        val bottomNav = binding.bottomNavigationView
//        bottomNav.setOnNavigationItemSelectedListener { item->
//            when (item.itemId){
//                R.id.login_mi->{
//                    navController.navigate(R.id.action_global_loginFragment)
//                    true
//                }
//                R.id.register_mi->{
//                    navController.navigate(R.id.action_global_registerFragment)
//                    true
//                }
//                else->false
//            }
//
//
//        }
    }
}