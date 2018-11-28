package com.example.home_pc.broadcastexample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.home_pc.broadcastexample.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(MainFragment().newInstance())
    }

    fun showFragment(fragment: Fragment) {
        var fm = supportFragmentManager
        var frag = fm.findFragmentById(R.id.container)
        when {
            frag == null -> {
                fm.beginTransaction().add(R.id.container, fragment)
                        .addToBackStack("TAG")
                        .commit()
            }
            else -> {
                fm.beginTransaction().replace(R.id.container, fragment)
                        .addToBackStack("TAG")
                        .commit()
            }
        }
    }
}
