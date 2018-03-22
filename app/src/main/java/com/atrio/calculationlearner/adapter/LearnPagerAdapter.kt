package com.atrio.calculationlearner.adapter

import android.content.Context
import android.speech.tts.TextToSpeech
import com.atrio.calculationlearner.R.id.textView
import android.widget.TextView
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import com.atrio.calculationlearner.R
import java.util.*


/**
 * Created by Arpita Patel on 21-03-2018.
 */

class LearnPagerAdapter(var context: Context, var param1: String, var symbol: String, var param2: IntArray, val equal: String, var numresult: String) : PagerAdapter(), TextToSpeech.OnInitListener {
    var tts: TextToSpeech? = null
    var speak: String? = null
    var onetext: String? = null
    var tv_1st: TextView? = null
    var tv_symbol: TextView? = null
    var tv_2nd: TextView? = null
    var tv_equal: TextView? = null
    var tv_result: TextView? = null
    var btn_speak: Button? = null
    val animation = AnimationUtils.loadAnimation(context, R.anim.slide)


    override fun getCount(): Int {
        return param2.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.practice_one_view, container, false)

        tv_1st = row?.findViewById(R.id.tv_1st)
        tv_symbol = row?.findViewById<TextView>(R.id.tv_symbol)
        tv_2nd = row?.findViewById<TextView>(R.id.tv_2st)
        tv_equal = row?.findViewById<TextView>(R.id.tv_equals)
        tv_result = row?.findViewById<TextView>(R.id.tv_result)
        btn_speak = row?.findViewById<Button>(R.id.btn_speak)

        val animationparam1 = AnimationUtils.loadAnimation(context, R.anim.slide)
        val animationsymbol = AnimationUtils.loadAnimation(context, R.anim.slide)
        val animationparam2 = AnimationUtils.loadAnimation(context, R.anim.slide)
        tts = TextToSpeech(context, this)

        viewgetting()
        getparam1()
//        viewparam1()

      /*  tv_1st?.text = param1
        tv_1st?.startAnimation(animationparam1)
        animationparam1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                onetext = tv_1st?.text.toString()
                tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                Log.i("thread11end", "" + onetext)
            }

            override fun onAnimationStart(animation: Animation?) {
                Log.i("thread11start", "" + onetext)
            }

        })
        tv_symbol?.text = symbol
        tv_symbol?.startAnimation(animationsymbol)
        animationsymbol.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                onetext = tv_symbol?.text.toString()
                tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                Log.i("thread11end", "" + onetext)
            }

            override fun onAnimationStart(animation: Animation?) {
                Log.i("thread11start", "" + onetext)
            }

        })*/


/*        tv_2nd?.setText(param2[position].toString())
        Log.i("thread115666", "" + tv_2nd?.text)*/
/*
        tv_2nd?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                onetext = tv_2nd?.text.toString()
                tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                Log.i("thread11end", "" + onetext)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
*/


//        tv_2nd?.startAnimation(animationparam2)
/*
        animationparam2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                onetext = tv_2nd?.text.toString()
                tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                tv_1st?.text=onetext
                Log.i("thread11end", "" + onetext)
            }

            override fun onAnimationStart(animation: Animation?) {
                Log.i("thread11start", "" + onetext)
            }

        })
*/


        /*      tv_2nd?.text = param2[position].toString()
              Log.i("playsound5444",param2.get(position).toString())
      //        tts!!.speak(param2[position].toString(), TextToSpeech.QUEUE_FLUSH, null)
              tv_symbol?.text = symbol
              tv_equal?.text = equal*/

        /* val value1: Int = param1.toInt()// tv_1st?.text.toString().toInt()
         val value2: Int = param2[position]//tv_2nd?.text.toString().toInt()
         Log.i("playsound544445",value2.toString())*/

        /* var result = 0
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
         numresult = result.toString()*/
/*        tv_result?.text = numresult*/

        /*  Thread(Runnable {
              Thread.sleep(1000)
              tv_1st?.post(Runnable {
                  tv_1st?.text = param1
                  onetext=tv_1st?.text.toString()
  //                tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                  tv_1st?.startAnimation(animation)

                  Log.i("thread11",""+onetext)
                  animation.setAnimationListener(this)
              })
          }).start()
          Thread(Runnable {
              Thread.sleep(4000)
              tv_symbol?.post(Runnable {
                  tv_symbol?.text = symbol
                  onetext=tv_symbol?.text.toString()
  //                tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                  tv_symbol?.startAnimation(animation)

  //                 Log.i("thread11",""+onetext)
                  animation.setAnimationListener(this)
              })

          }).start()
          Thread(Runnable {
              Thread.sleep(6000)
              tv_2nd?.post(Runnable {
                  tv_2nd?.text =param2[position].toString()
                  onetext=tv_2nd?.text.toString()
                  tv_2nd?.startAnimation(animation)

  //                Log.i("thread11",""+index)
                  animation.setAnimationListener(this)
              })

          }).start()*/
/*

        val textViewIds = intArrayOf(R.id.tv_1st, R.id.tv_symbol, R.id.tv_2st, R.id.tv_equals, R.id.tv_result)
        var i = 1

        for (viewId in textViewIds) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.slide)
            animation.startOffset = (i * 3000).toLong()
            val textViewId = textViewIds[i - 1]
            val textView = row?.findViewById(textViewId) as TextView
            onetext=textView.text.toString()
            Log.i("playsound5444",onetext)
            textView.startAnimation(animation)
            tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
            i++
        }
*/

        btn_speak?.setOnClickListener(View.OnClickListener {
            var btntext = tv_1st!!.text.toString() + speak + tv_2nd!!.text.toString() + tv_equal!!.text.toString() + tv_result!!.text.toString()
            tts!!.speak(btntext, TextToSpeech.QUEUE_FLUSH, null)
        })

        (container as ViewPager).addView(row)

        return row

    }

    fun getparam1(){
        tv_1st?.text = param1
        onetext=tv_1st?.text.toString()
        tv_1st?.startAnimation(animation)
//        tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)

    }
 fun viewgetting(){

     tv_symbol?.text = symbol
     onetext=tv_symbol?.text.toString()
     tv_symbol?.startAnimation(animation)
//     tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
     Thread(Runnable {
         Thread.sleep(1000)
         tv_symbol?.post(Runnable {
             tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
             Log.i("mythread11",""+onetext)
//             animation.setAnimationListener(this)
         })

     }).start()

 }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        (container as ViewPager).removeView(`object` as View)

        if (tts != null) {
            Log.i("PlaySeries", "In pause play series");
            this.tts?.stop()
            this.tts?.shutdown()
        }
//        super.destroyItem(container, position, `object`)

    }

    /* override fun onAnimationRepeat(animation: Animation?) {

     }

     override fun onAnimationEnd(animation: Animation?) {
         Log.i("thread1145",""+tv_2nd?.text.toString())
   *//*      var index=tv_2nd?.text.toString()
        tts!!.speak(index.toString(), TextToSpeech.QUEUE_FLUSH, null)
                        Log.i("thread1145",""+index)*//*

//        textspeak("end")
    }

    override fun onAnimationStart(animation: Animation?) {
        tts!!.speak(onetext.toString(), TextToSpeech.QUEUE_FLUSH, null)

    }*/
    override fun getItemPosition(`object`: Any?): Int {
//        tts!!.speak(p.toString(), TextToSpeech.QUEUE_FLUSH, null)
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