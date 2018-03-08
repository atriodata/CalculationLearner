package com.atrio.calculationlearner.adapter

import android.app.Activity
import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.atrio.calculationlearner.R
import com.atrio.calculationlearner.model.NumData
import java.util.*


/**
 * Created by Arpita Patel on 06-03-2018.
 */
class TestAdapter(var activity: Activity, var items: ArrayList<NumData>): BaseAdapter(), TextToSpeech.OnInitListener {
    var tts: TextToSpeech? = null
    var speak:String?=null
    private class ViewHolder(row: View?){


        var tv_1st: TextView? = null
        var tv_symbol: TextView? = null
        var tv_2nd: TextView? = null
        var tv_equal: TextView? = null
        var tv_result: TextView? = null
        var btn_speak:Button?=null


        init {
            this.tv_1st = row?.findViewById<TextView>(R.id.tv_1st)
            this.tv_symbol = row?.findViewById<TextView>(R.id.tv_symbol)
            this.tv_2nd = row?.findViewById<TextView>(R.id.tv_2st)
            this.tv_equal = row?.findViewById<TextView>(R.id.tv_equals)
            this.tv_result = row?.findViewById<TextView>(R.id.tv_result)
            this.btn_speak=row?.findViewById<Button>(R.id.btn_speak)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.custom_listview, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var userDto = items[position]
        viewHolder.tv_1st?.text = userDto.param1
        viewHolder.tv_2nd?.text = userDto.param2
        viewHolder.tv_symbol?.text = userDto.symbol
        viewHolder.tv_equal?.text = userDto.equal
        this.tts = TextToSpeech(activity, this)
        val value1:Int=viewHolder.tv_1st?.text.toString().toInt()
        val value2:Int=viewHolder.tv_2nd?.text.toString().toInt()
        var result:Int = 0
        if (viewHolder.tv_symbol?.text!!.equals("+")){
            result = value1 + value2
            speak="Pluse"

        }else  if (viewHolder.tv_symbol?.text!!.equals("-")){
            viewHolder.tv_1st?.text = userDto.param2
            viewHolder.tv_2nd?.text = userDto.param1
            result = value2 - value1
            speak="Minus"

        }else  if (viewHolder.tv_symbol?.text!!.equals("*")){
            speak="into"
            result = value1 * value2
        }else  if (viewHolder.tv_symbol?.text!!.equals("/")){
            viewHolder.tv_1st?.text = userDto.param2
            viewHolder.tv_2nd?.text = userDto.param1
            result = value2 / value1
            speak="Divided By"

        }
        userDto.numresult=result.toString()
        viewHolder.tv_result?.text = userDto.numresult

        viewHolder.btn_speak?.setOnClickListener(View.OnClickListener {
            val text = viewHolder.tv_1st!!.text.toString()+speak+viewHolder.tv_2nd!!.text.toString()+viewHolder.tv_equal!!.text.toString()+viewHolder.tv_result!!.text.toString()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
                this.tts!!.setSpeechRate(0.7f)
            }
        })

        return view as View
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = this.tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
//                this.btn_speak!!.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    override fun getItem(i: Int): NumData {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }


}
