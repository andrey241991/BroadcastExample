package com.example.home_pc.broadcastexample.systeminfo


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.home_pc.broadcastexample.base.BaseFragment
import com.example.home_pc.broadcastexample.R
import java.text.SimpleDateFormat
import java.util.*


class SystemInfoFragment : BaseFragment() {

    lateinit var txtHeadPhenes: TextView
    lateinit var txtButteryLevel: TextView
    lateinit var txtInternet: TextView
    lateinit var txtDate: TextView
    lateinit var txtTImeZone: TextView
    lateinit var myBroadCast:MyBroadcast

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_system_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(view)
        startBroadcast()
    }

    override fun getFragmentNme(): String {
        return "System Info"
    }

    private fun setViews(view: View) {
        txtHeadPhenes = view.findViewById(R.id.txt_head_phenes)
        txtButteryLevel = view.findViewById(R.id.txt_buttery_level)
        txtInternet = view.findViewById(R.id.txt_internet)
        txtDate = view.findViewById(R.id.txt_date)
        txtTImeZone = view.findViewById(R.id.txt_time_zone)
    }

    private fun startBroadcast() {
        var myIntentFilter = IntentFilter()
        myIntentFilter.addAction(Intent.ACTION_HEADSET_PLUG)
        myIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        myIntentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        myIntentFilter.addAction(Intent.ACTION_TIME_CHANGED)
        myIntentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        myBroadCast = MyBroadcast()
        requireActivity().registerReceiver(myBroadCast, myIntentFilter)
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(myBroadCast)
    }

    fun newInstance(): SystemInfoFragment {
        val args: Bundle = Bundle()
        val fragment = SystemInfoFragment()
        fragment.arguments = args
        return fragment
    }

    private fun setInternetConnection(extraWifiState: Int) {
        when (extraWifiState) {
            WifiManager.WIFI_STATE_DISABLED -> txtInternet.text = "Internet disable"
            WifiManager.WIFI_STATE_ENABLED -> txtInternet.text = "Internet enable"
        }
    }

    fun setHeadSet() {
        val audioManager = requireActivity().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var headSet = audioManager!!.isWiredHeadsetOn
        when (headSet) {
            true -> txtHeadPhenes.text = "HeadPhene is switched On"
            false -> txtHeadPhenes.text = "HeadPhene is switched Off"
        }
    }

    private fun setButtery(level: Int) {
        txtButteryLevel.text = level.toString()
    }

    private fun setDate() {
        var sdf = SimpleDateFormat("EEE, d MMM yyyy HH:mm")
        var newD: String = sdf.format(Date())
        txtDate.text = newD
    }

    private fun setTimeZone() {
        val tz = TimeZone.getDefault()
        var name = tz.getDisplayName(false, TimeZone.SHORT)
        var id = tz.id
        txtTImeZone.text = "TimeZone ${name} Timezon id :: ${id}"
    }


    inner class MyBroadcast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent!!.action) {
                WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                    var extraWifiState: Int = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                            WifiManager.WIFI_STATE_UNKNOWN)
                    setInternetConnection(extraWifiState)
                }
                Intent.ACTION_HEADSET_PLUG -> setHeadSet()
                Intent.ACTION_BATTERY_CHANGED -> {
                    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                    setButtery(level)
                }
                Intent.ACTION_TIMEZONE_CHANGED -> setTimeZone()
                Intent.ACTION_TIME_CHANGED -> setDate()
            }
        }
    }
}

