package com.example.home_pc.broadcastexample.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.home_pc.broadcastexample.base.BaseFragment
import com.example.home_pc.broadcastexample.MainActivity

import com.example.home_pc.broadcastexample.R
import com.example.home_pc.broadcastexample.communication.ComunicationFragment
import com.example.home_pc.broadcastexample.systeminfo.SystemInfoFragment

class MainFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var btnSystem = requireActivity().findViewById<Button>(R.id.btn_system_info)
        btnSystem.setOnClickListener { (activity as MainActivity).showFragment(SystemInfoFragment().newInstance()) }

        var btnComunication = requireActivity().findViewById<Button>(R.id.btn_frag_communication)
        btnComunication.setOnClickListener { (activity as MainActivity).showFragment(ComunicationFragment().newInstance()) }
    }

    fun newInstance(): MainFragment {
        val args: Bundle = Bundle()
        val fragment = MainFragment()
        fragment.arguments = args
        return fragment
    }

    override fun getFragmentNme(): String {
        return "Main Fragment"
    }
}
