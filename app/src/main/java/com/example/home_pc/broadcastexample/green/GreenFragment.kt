package com.example.home_pc.broadcastexample.green


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.home_pc.broadcastexample.R
import org.greenrobot.eventbus.EventBus
import com.example.home_pc.broadcastexample.eventbus.EditTextEvent
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe




/**
 * A simple [Fragment] subclass.
 */
class GreenFragment : Fragment() {


    lateinit var fragBroadcast: FragBroadcast

    lateinit var txtSwitch: TextView
    lateinit var txtEdit: TextView
    lateinit var txtButton: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_green, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtSwitch = view.findViewById(R.id.txt_switch)
        txtEdit = view.findViewById(R.id.txt_edit)
        txtButton = view.findViewById(R.id.txt_btn)

        var intentFilter = IntentFilter()
        fragBroadcast = FragBroadcast()
        intentFilter.addAction(ACTION_TOGGLE_BTN)
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(fragBroadcast, intentFilter)
        EventBus.getDefault().register(this);
    }

    fun newInstance(): GreenFragment {
        val args: Bundle = Bundle()
        val fragment = GreenFragment()
        fragment.arguments = args
        return fragment
    }


    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(fragBroadcast)
        EventBus.getDefault().unregister(this);
    }

    companion object {
        const val ACTION_TOGGLE_BTN = "com.example.home_pc.broadcastexample.green.ACTION_TOGGLE_BTN"
        const val TOGGLE_BTN_KEY = "TOGGLE_BTN_KEY"
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEditTextEvent(event: EditTextEvent) {
        txtEdit.text = event.text
    }

    inner class FragBroadcast : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            var switcher = intent!!.getBooleanExtra(TOGGLE_BTN_KEY, false)
            when (switcher) {
                true -> txtSwitch.text = "Switch: switch on"
                false -> txtSwitch.text = "Switch: switch off"
            }
        }
    }


}
