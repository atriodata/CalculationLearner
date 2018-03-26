package com.atrio.calculationlearner.adapter

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import com.atrio.calculationlearner.R
import java.util.*


/**
 * Created by Arpita Patel on 21-03-2018.
 */

class LearnPagerAdapter(var context: Context, var param1: String, var symbol: String, var param2: IntArray, val equal: String, var numresult: String) : PagerAdapter(), TextToSpeech.OnInitListener {
    var tts: TextToSpeech? = null
    var speak: String? = null
//    var onetext: String? = null
    var param1onetext:String?=null
    var symbolonetext:String?=null
    var param2onetext:String?=null
    var equalonetext:String?=null
    var resultonetext:String?=null
    var tv_1st: TextView? = null
    var tv_symbol: TextView? = null
    var tv_2nd: TextView? = null
    var tv_equal: TextView? = null
    var tv_result: TextView? = null
    var btn_speak: Button? = null


    override fun getCount(): Int {
        return param2.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.practice_one_view, container, false)

        tv_1st = row?.findViewById(R.id.tv_1st)
        tv_symbol = row?.findViewById<TextView>(R.id.tv_symbol)
        tv_2nd = row?.findViewById<TextView>(R.id.tv_2st)
        tv_equal = row?.findViewById<TextView>(R.id.tv_equals)
        tv_result = row?.findViewById<TextView>(R.id.tv_result)
        btn_speak = row?.findViewById<Button>(R.id.btn_speak)

        tts = TextToSpeech(context, this)

        val animationparam1 = AnimationUtils.loadAnimation(context, R.anim.slide)
        val animationsymbol = AnimationUtils.loadAnimation(context, R.anim.slide)
        val animationparam2 = AnimationUtils.loadAnimation(context, R.anim.slide)
        val animationequal = AnimationUtils.loadAnimation(context, R.anim.slide)
        val animationresult = AnimationUtils.loadAnimation(context, R.anim.slide)



        if (position == 0) {
            val value1: Int = param1.toInt()
            val value2: Int = param2[0]
            Log.i("myvalue",value2.toString())
            var result = 0
            if (symbol.equals("+")) {
                result = value1 + value2
                Log.i("myvalue45",result.toString())
                speak = "Pluse"

            } else if (symbol.equals("-")) {
                result = value2 - value1
                speak = "Minus"

            } else if (symbol.equals("\u00D7")) {
                speak = "into"
                result = value1 * value2
            } else if (symbol.equals("\u00F7")) {
                result = value2 / value1
                speak = "Divided By"

            }
            numresult = result.toString()

            tv_1st?.text = param1
            tv_symbol?.text = symbol
            tv_2nd?.text = param2[position].toString()
            tv_equal?.text = equal
            tv_result?.text = numresult

            tv_1st?.startAnimation(animationparam1)
            animationparam1.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    param1onetext = param1
                    speaktextone(param1onetext!!)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })

            tv_symbol?.startAnimation(animationsymbol)
            animationsymbol.startOffset = (3000)
            animationsymbol.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {

                    symbolonetext = speak
                    speaktextone(symbolonetext!!)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
            tv_2nd?.startAnimation(animationparam2)

            animationparam2.startOffset = (6000)
            animationparam2.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {

                    param2onetext = param2[0].toString()
                    speaktextone(param2onetext!!)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
            tv_equal?.startAnimation(animationequal)
            animationequal.startOffset = (9000)
            animationequal.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    equalonetext = equal
                    speaktextone(equalonetext!!)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })

            tv_result?.startAnimation(animationresult)
            notifyDataSetChanged()
            animationresult.startOffset = (12000)
            animationresult.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {

                    resultonetext = result.toString()
                    speaktextone(resultonetext!!)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
        btn_speak?.setOnClickListener(View.OnClickListener {
            var btntext = param1onetext + symbolonetext + param2onetext + equalonetext + resultonetext
            tts!!.speak(btntext, TextToSpeech.QUEUE_FLUSH, null)
        })

        }else{

            val value1: Int = param1.toInt()
            val value2: Int = param2[position]

            var result = 0
            if (symbol.equals("+")) {
                result = value1 + value2
                speak = "Pluse"

            } else if (symbol.equals("-")) {
                tv_1st?.text = param2[position].toString()
                tv_2nd?.text = param1
                result = value2 - value1
                speak = "Minus"

            } else if (symbol.equals("\u00D7")) {
                speak = "into"
                result = value1 * value2
            } else if (symbol.equals("\u00F7")) {
                tv_1st?.text = param2[position].toString()
                tv_2nd?.text = param1
                result = value2 / value1
                speak = "Divided By"

            }
            numresult = result.toString()

            tv_1st?.text = param1
            tv_symbol?.text = symbol
            tv_2nd?.text = param2[position].toString()
            tv_equal?.text = equal
            tv_result?.text = numresult
        }

        (container as ViewPager).addView(row)

        return row

    }

    fun speaktextone(onetext: String) {
        tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        (container as ViewPager).removeView(`object` as View)

        if (tts != null) {
            Log.i("PlaySeries", "In pause play series")
            this.tts?.stop()
            this.tts?.shutdown()
        }
//        super.destroyItem(container, position, `object`)

    }

    override fun getItemPosition(`object`: Any?): Int {

        return super.getItemPosition(`object`)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            tts!!.language = Locale.UK
            tts!!.setSpeechRate(0.5.toFloat())
        }


    }

}