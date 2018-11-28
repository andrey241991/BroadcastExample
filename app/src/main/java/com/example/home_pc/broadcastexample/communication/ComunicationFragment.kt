package com.example.home_pc.broadcastexample.communication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.home_pc.broadcastexample.base.BaseFragment
import com.example.home_pc.broadcastexample.ButtonPressListener

import com.example.home_pc.broadcastexample.R
import com.example.home_pc.broadcastexample.blue.BlueFragment

import com.example.home_pc.broadcastexample.green.GreenFragment


/**
 * A simple [Fragment] subclass.
 */
class ComunicationFragment : BaseFragment(), ButtonPressListener {

   lateinit var greenFragment:GreenFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comunication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var blueFragment = childFragmentManager!!.findFragmentById(R.id.blue_frag) as BlueFragment
        blueFragment.buttonPressListener = this
        greenFragment = childFragmentManager!!.findFragmentById(R.id.green_frag) as GreenFragment
    }

    override fun buttonPressed(pressed: Boolean) {
        when(pressed){
            true ->  greenFragment.txtButton.text = "Button is Pressed"
            false ->  greenFragment.txtButton.text = "Button is Not Pressed"
        }
    }

    fun newInstance(): ComunicationFragment {
        val args: Bundle = Bundle()
        val fragment = ComunicationFragment()
        fragment.arguments = args
        return fragment
    }

    override fun getFragmentNme(): String {
        return "Fragments Communication"
    }
}
