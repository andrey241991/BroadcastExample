package com.example.home_pc.broadcastexample.blue


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ToggleButton

import com.example.home_pc.broadcastexample.R
import com.example.home_pc.broadcastexample.green.GreenFragment.Companion.ACTION_TOGGLE_BTN
import com.example.home_pc.broadcastexample.green.GreenFragment.Companion.TOGGLE_BTN_KEY
import android.view.MotionEvent
import com.example.home_pc.broadcastexample.ButtonPressListener
import com.example.home_pc.broadcastexample.eventbus.EditTextEvent
import org.greenrobot.eventbus.EventBus


/**
 * A simple [Fragment] subclass.
 */
open class BlueFragment : Fragment() {

    lateinit var edit: EditText
    lateinit var toggle: ToggleButton
    lateinit var btn: Button
    lateinit var buttonPressListener: ButtonPressListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blue, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit = view.findViewById(R.id.edit)
        toggle = view.findViewById(R.id.toggle)
        btn = view.findViewById(R.id.btn)


        toggle.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(toggleButton: CompoundButton, isChecked: Boolean) {
                var intent = Intent()
                intent.action = ACTION_TOGGLE_BTN
                intent.putExtra(TOGGLE_BTN_KEY, isChecked)
                LocalBroadcastManager.getInstance(requireActivity()).sendBroadcast(intent);
            }
        })

        btn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->  buttonPressListener.buttonPressed(true)
                MotionEvent.ACTION_UP ->     buttonPressListener.buttonPressed(false)
            }
            false
        }

        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(txt: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
               EventBus.getDefault().post(EditTextEvent(text = text.toString()))
            }
        });

    }

    fun newInstance(): BlueFragment {
        val args: Bundle = Bundle()
        val fragment = BlueFragment()
        fragment.arguments = args
        return fragment
    }
}
