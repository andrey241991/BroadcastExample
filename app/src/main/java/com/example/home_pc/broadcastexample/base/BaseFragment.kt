package com.example.home_pc.broadcastexample.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.home_pc.broadcastexample.R
import com.example.home_pc.broadcastexample.main.MainFragment

open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }

    open fun setToolbar() {
        var toolbar: Toolbar
        toolbar = (activity as AppCompatActivity).findViewById(R.id.toolbar)
        var txt = toolbar.findViewById<TextView>(R.id.toolbar_title)
        txt.text = getFragmentNme()
        var btnBack = toolbar.findViewById<ImageView>(R.id.btn_back)
        btnBack.visibility = View.VISIBLE
        btnBack.setOnClickListener { requireActivity().onBackPressed() }

        var currentFrag = requireActivity().supportFragmentManager.findFragmentById(R.id.container)
        if (currentFrag is MainFragment) {
            btnBack.visibility = View.INVISIBLE
        }
    }

    open fun getFragmentNme(): String {
        return requireActivity().getString(R.string.app_name)
    }

}