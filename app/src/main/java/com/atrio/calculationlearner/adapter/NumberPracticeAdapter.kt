package com.atrio.calculationlearner.adapter

import android.app.Activity
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Build
import android.speech.tts.TextToSpeech
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import com.atrio.calculationlearner.R
import com.atrio.calculationlearner.model.NumData
import java.util.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation


/**
 * Created by Arpita Patel on 09-03-2018.
 */

class NumberPracticeAdapter(var activity: Context, var items: ArrayList<NumData>) : PagerAdapter(), TextToSpeech.OnInitListener, Animation.AnimationListener {

    var tts: TextToSpeech? = null
    var speak: String? = null
    var text: String? = null
    var onetext:String?=null


    override fun isViewFromObject(view: View?, myobject: Any?): Boolean {
        return view == myobject
    }

    override fun getCount(): Int {
        return 20
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var tv_1st: TextView? = null
        var tv_symbol: TextView? = null
        var tv_2nd: TextView? = null
        var tv_equal: TextView? = null
        var tv_result: TextView? = null
        var btn_speak: Button? = null
//        var btn_back: ImageButton?=null
//        var btn_forward: ImageButton?=null

        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.practice_one_view, container, false)

        tv_1st = row?.findViewById(R.id.tv_1st)
        tv_symbol = row?.findViewById<TextView>(R.id.tv_symbol)
        tv_2nd = row?.findViewById<TextView>(R.id.tv_2st)
        tv_equal = row?.findViewById<TextView>(R.id.tv_equals)
        tv_result = row?.findViewById<TextView>(R.id.tv_result)
        btn_speak = row?.findViewById<Button>(R.id.btn_speak)
//        btn_back=row?.findViewById<ImageButton>(R.id.btn_back)
//        btn_forward=row?.findViewById<ImageButton>(R.id.btn_next)

        tts = TextToSpeech(activity, this)
        textspeak("Hello")


        var userDto = items[position]
        tv_1st?.text = userDto.param1
        tv_2nd?.text = userDto.param2
        tv_symbol?.text = userDto.symbol
        tv_equal?.text = userDto.equal

        val value1: Int = tv_1st?.text.toString().toInt()
        val value2: Int = tv_2nd?.text.toString().toInt()
        var result: Int = 0
        if (tv_symbol?.text!!.equals("+")) {
            result = value1 + value2
            speak = "Pluse"

        } else if (tv_symbol.text!!.equals("-")) {
            tv_1st?.text = userDto.param2
            tv_2nd?.text = userDto.param1
            result = value2 - value1
            speak = "Minus"

        } else if (tv_symbol.text!!.equals("\u00D7")) {
            speak = "into"
            result = value1 * value2
        } else if (tv_symbol.text!!.equals("\u00F7")) {
            tv_1st?.text = userDto.param2
            tv_2nd?.text = userDto.param1
            result = value2 / value1
            speak = "Divided By"

        }
        userDto.numresult = result.toString()
        tv_result?.text = userDto.numresult



        val textViewIds = intArrayOf(R.id.tv_1st, R.id.tv_symbol, R.id.tv_2st, R.id.tv_equals, R.id.tv_result)
        var i = 1

        for (viewId in textViewIds) {
            val animation = AnimationUtils.loadAnimation(activity, R.anim.slide)

            animation.startOffset = (i * 1000).toLong()
//            Log.i("playsound54",onetext)
            val textViewId = textViewIds[i - 1]
            val textView = row?.findViewById(textViewId) as TextView

            onetext=textView.text.toString()
            Log.i("playsound5444",onetext)
            textView.startAnimation(animation)
            animation.setAnimationListener(this)

/*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null, "")
                this.tts!!.setSpeechRate(0.7f)
            }
*/

            i++
        }
/*



        tv_1st.startAnimation(animation)
        tv_symbol.startAnimation(animation)
        tv_2nd.startAnimation(animation)
        tv_equal.startAnimation(animation)
        tv_result.startAnimation(animation)*/


        btn_speak?.setOnClickListener(View.OnClickListener {
            text = tv_1st!!.text.toString() + speak + tv_2nd!!.text.toString() + tv_equal!!.text.toString() + tv_result!!.text.toString()

            textspeak(text!!)
        })




        (container as ViewPager).addView(row)

        return row

    }

    private fun textspeak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            this.tts!!.setSpeechRate(0.7f)
        }

    }

    override fun onAnimationRepeat(animation: Animation) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnimationEnd(animation: Animation) {
        Log.i("playsound","playing4555")
/*
        if(tts !=null){
            Log.i("PlaySeries", "In pause play series");
            this.tts?.stop()
            this.tts?.shutdown()
        }
*/
    }

    override fun onAnimationStart(animation: Animation) {
//        val text = tv_1st!!.text.toString()+speak+tv_2nd!!.text.toString()+tv_equal!!.text.toString()+tv_result!!.text.toString()
        Log.i("playsound54",onetext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null, "")
            this.tts!!.setSpeechRate(0.7f)
        }
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        (container as ViewPager).removeView(`object` as View)
        if(tts !=null){
            Log.i("PlaySeries", "In pause play series");
            this.tts?.stop()
            this.tts?.shutdown()
        }
//        super.destroyItem(container, position, `object`)

    }

    override fun onInit(status: Int) {
        Log.i("TTSstatus", status.toString())

        if (status == TextToSpeech.SUCCESS) {
            val result = this.tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.i("TTS", "The Language specified is not supported!")
            } else {
//                this.btn_speak!!.isEnabled = true
            }

        } else {
            Log.i("TTS", "Initilization Failed!")
        }
    }

}