package com.atrio.calculationlearner.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import com.atrio.calculationlearner.R
import com.atrio.calculationlearner.model.NumData
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.speech.tts.TextToSpeech
import java.util.*
import kotlin.collections.ArrayList
import android.annotation.TargetApi
import android.os.Build
import android.speech.tts.UtteranceProgressListener


/**
 * Created by Arpita Patel on 09-03-2018.
 */

class NumberPracticeAdapter(var context: Context, var items: ArrayList<NumData>) : PagerAdapter(), Animation.AnimationListener, TextToSpeech.OnInitListener {

    var tts: TextToSpeech? = null
    var speak: String? = null
    var text: String? = null
    var onetext:String?=null
    private var initialized: Boolean = false
    private var queuedText: String? = null

    override fun isViewFromObject(view: View?, myobject: Any?): Boolean {
        return view == myobject
    }

    override fun getCount(): Int {
        return 20
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val tv_1st: TextView?
        val tv_symbol: TextView?
        val tv_2nd: TextView?
        val tv_equal: TextView?
        val tv_result: TextView?
        val btn_speak: Button?

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.practice_one_view, container, false)

        tv_1st = row?.findViewById(R.id.tv_1st)
        tv_symbol = row?.findViewById<TextView>(R.id.tv_symbol)
        tv_2nd = row?.findViewById<TextView>(R.id.tv_2st)
        tv_equal = row?.findViewById<TextView>(R.id.tv_equals)
        tv_result = row?.findViewById<TextView>(R.id.tv_result)
        btn_speak = row?.findViewById<Button>(R.id.btn_speak)

        tts = TextToSpeech(context , this )
        tts!!.setOnUtteranceProgressListener(mProgressListener)

        val userDto = items[position]
        tv_1st?.text = userDto.param1
        tv_2nd?.text = userDto.param2
        tv_symbol?.text = userDto.symbol
        tv_equal?.text = userDto.equal

        val value1: Int = tv_1st?.text.toString().toInt()
        val value2: Int = tv_2nd?.text.toString().toInt()
        var result = 0
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
        textspeak("hello")

        val textViewIds = intArrayOf(R.id.tv_1st, R.id.tv_symbol, R.id.tv_2st, R.id.tv_equals, R.id.tv_result)
        var i = 1

        for (viewId in textViewIds) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.slide)
            animation.startOffset = (i * 1000).toLong()
            val textViewId = textViewIds[i - 1]
            val textView = row?.findViewById(textViewId) as TextView
            onetext=textView.text.toString()
            Log.i("playsound5444",onetext)
            textView.startAnimation(animation)
            animation.setAnimationListener(this)
            i++
        }
    /*    val textSound = arrayOf(userDto.param1,userDto.symbol,userDto.param2,userDto.equal,userDto.numresult)
        var s = 1

        for (viewId in textSound) {
            val textViewId = textSound[s - 1]
            onetext=textViewId
            textspeak(onetext)
            Log.i("playsound5444",onetext)
            s++
        }*/


       /*
        tv_1st!!.startAnimation(animation)
        onetext=tv_1st.text.toString()
//        tv_2nd!!.startAnimation(animation)
        animation.setAnimationListener(this)*/





       /* val textViewIds = intArrayOf(R.id.tv_1st, R.id.tv_symbol, R.id.tv_2st, R.id.tv_equals, R.id.tv_result)
        var i = 1

        for (viewId in textViewIds) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.slide)

            animation.startOffset = (i * 1000).toLong()
            val textViewId = textViewIds[i - 1]
            val textView = row?.findViewById(textViewId) as TextView

            onetext=textView.text.toString()


            Log.i("playsound5444",onetext)
            textView.startAnimation(animation)
            textspeak(onetext)
            i++
        }*/



        btn_speak?.setOnClickListener(View.OnClickListener {
            text = tv_1st!!.text.toString() + speak + tv_2nd!!.text.toString() + tv_equal!!.text.toString() + tv_result!!.text.toString()
           textspeak(text!!)
        })




        (container as ViewPager).addView(row)

        return row

    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        Log.i("playsound5444e",onetext)
//        textspeak("end")
     /*   Thread(Runnable {

            (context as Activity).runOnUiThread(java.lang.Runnable {
                                textspeak(onetext)
                Toast.makeText(context, onetext, Toast.LENGTH_SHORT).show();
            })

        }).start()*/
    }

    override fun onAnimationStart(animation: Animation?) {
        Log.i("playsound5444s",onetext)
//       textspeak("hello")

    }

/*
    fun textspeak(text: String) {

        if (!initialized) {
            queuedText = text
            return
        }
        queuedText = null

        setTtsListener() // no longer creates a new UtteranceProgressListener each time
        val map = HashMap<String, String>()
        map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "MessageId"
        tts!!.speak(text, TextToSpeech.QUEUE_ADD, map)
    }
*/

    private fun setTtsListener() {

    }
    fun textspeak(text: String?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.tts!!.speak(text, TextToSpeech.QUEUE_ADD, null)
            this.tts!!.setSpeechRate(0.7f)
        }else{

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
        if (status == TextToSpeech.SUCCESS) {
            initialized = true
            tts!!.setLanguage(Locale.ENGLISH)

            if (queuedText != null) {
                textspeak(queuedText!!)
            }
        }
    }

/*
    override fun onInit(status: Int) {
        Log.i("TTSstatus", status.toString())

      */
/*  utterParam = HashMap<String, String>()
        utterParam!!.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "utterID")

        if (status === TextToSpeech.SUCCESS) {

            val result = tts!!.setLanguage(Locale.US)

            // tts.setPitch(5); // set pitch level

            // tts.setSpeechRate(2); // set speech speed rate

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported")
            } else {
//                this.btn_speak!!.isEnabled = true
                textspeak("")
            }

        } else {
            Log.e("TTS", "Initilization Failed")
        }*//*

        */
/*if (status !== TextToSpeech.ERROR) {
            tts?.setLanguage(Locale.UK)
            tts!!.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String) {
                  *//*
*/
/*  val animation = AnimationUtils.loadAnimation(context, R.anim.slide)
                    this.tv_1st!!.startAnimation(animation)*//*
*/
/*
                  *//*
*/
/*  while (tts!!.isSpeaking()) {
                        val animation1 = AnimationUtils.loadAnimation(context, R.anim.slide)
                        view.startAnimation(animation1)
                    }*//*
*/
/*

                }

                override fun onDone(utteranceId: String) {
                    if (utteranceId == "finish") {
//                        fini
                    }
                }

                override fun onError(utteranceId: String) {

                }
            })
        }*//*


*/
/*
        if (status == TextToSpeech.SUCCESS) {
            val result = this.tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
               // textspeak("Hello")
                Log.i("TTS11", "The Language specified is not supported!")
            } else {
//                textspeak(onetext)
//                this.btn_speak!!.isEnabled = true
            }

        } else {
            Log.i("TTS", "Initilization Failed!")
        }
*//*

    }
*/

  /*  private val utterListener = object : UtteranceProgressListener() {
      override  fun onDone(utteranceId: String) {
            // TODO Auto-generated method stub
            if (utteranceId == "utterID") {
                // This code will be activate when TextToSpeech is stopped or done.(under API 22)
                // This code will be activate when TextToSpeech is done.(over API 23)
            }
        }

       override fun onError(utteranceId: String) {
            // TODO Auto-generated method stub
            if (utteranceId == "utterID") {

            }
        }

       override fun onStart(utteranceId: String) {
            // TODO Auto-generated method stub
            if (utteranceId == "utterID") {

            }
        }

        @TargetApi(23)
       override fun onStop(utteranceId: String, interrupted: Boolean) {
            if (utteranceId == "utterID") {
                // This code will be activate when TextToSpeech is stopped.(overr API 23)
            }
        }
    }*/
}

object mProgressListener : UtteranceProgressListener() {
    override fun onDone(utteranceId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(utteranceId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart(utteranceId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
