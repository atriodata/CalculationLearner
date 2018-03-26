package com.atrio.calculationlearner.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.os.Build
import android.speech.tts.TextToSpeech
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import com.atrio.calculationlearner.R
import com.atrio.calculationlearner.model.NumData
import java.util.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation


/**
 * Created by Arpita Patel on 09-03-2018.
 */

class NumberPracticeAdapter(var context: Context, var items: ArrayList<NumData>) : PagerAdapter(), TextToSpeech.OnInitListener, Animation.AnimationListener {



    var tts: TextToSpeech? = null
    var speak: String? = null
    var text: String? = null
    var onetext:String?=null


    override fun isViewFromObject(view: View?, myobject: Any?): Boolean {
        return view == myobject
    }

    override fun getCount(): Int {
        return items.size
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

        val userDto = items[position]
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide)
        tts = TextToSpeech(context, this)


        // var parama_data :Int = userDto.param1.toInt()

        //tv_1st?.text = userDto.param1
        //tv_2nd?.text = userDto.param2
        // tv_symbol?.text = userDto.symbol
        // tv_equal?.text = userDto.equal

        /*  val value1: Int = tv_1st?.text.toString().toInt()
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
  */

        /* userDto.numresult = result.toString()
         tv_result?.text = userDto.numresult*/
        /*       Thread(Runnable {
                   // a potentially  time consuming task

       //        tv_2nd!!.startAnimation(animation)
                  // animation.setAnimationListener(this)

                   tv_1st?.post(Runnable {
                       tv_1st.startAnimation(animation)
                       animation.setAnimationListener(this)

                   })
               }).start()*/

        Thread(Runnable {
            Thread.sleep(1000)
            tv_1st?.post(Runnable {
                tv_1st?.text = userDto.param1
                onetext=tv_1st?.text.toString()
                tv_1st?.startAnimation(animation)

                //Log.i("thread11",""+Thread.currentThread().id)
                animation.setAnimationListener(this)
            })
        }).start()
        Thread(Runnable {
            Thread.sleep(4000)
            tv_symbol?.post(Runnable {

                tv_symbol?.text = userDto.symbol
                onetext=tv_symbol?.text.toString()
                tv_symbol?.startAnimation(animation)

                // Log.i("thread11",""+onetext)
                animation.setAnimationListener(this)
            })

        }).start()
        Thread(Runnable {
            Thread.sleep(6000)
            tv_2nd?.post(Runnable {
                if (position == 0){
                    Log.i("printdata1",""+items[position].param2)
                    tv_2nd?.text = items[position].param2
                   var  onetext1= items[0].param2
                    textspeak(onetext1)
                    notifyDataSetChanged()
                }
                if (position == 1){

                    Log.i("printdata2",""+items[position].param2)
                    tv_2nd?.text = items[1].param2
                    onetext= "1"
                    textspeak(onetext)
                    notifyDataSetChanged()
                }

                if (position!=0 && position!=1){
                    Log.i("printdata3",""+items[position].param2)
                    tv_2nd?.text = items[position].param2
                    onetext= items[position].param2
                    textspeak(onetext)
                    notifyDataSetChanged()

                }


                tv_2nd?.startAnimation(animation)

               // Log.i("thread11",""+onetext)
               // animation.setAnimationListener(this)
            })

        }).start()



        //val handler = Handler()

        /* val r = Runnable {
             tv_1st?.text = userDto.param1
             onetext=tv_1st?.text.toString()
             tv_1st?.startAnimation(animation)

             //Log.i("thread11",""+Thread.currentThread().id)
             animation.setAnimationListener(this)
         }
          handler.postDelayed(r ,1000)*/
        /*      val r1 = Runnable {
                  tv_symbol?.text = userDto.symbol
                  onetext= tv_symbol?.text.toString()
                  tv_symbol?.startAnimation(animation)
                  //Log.i("thread12",""+Thread.currentThread().id)
                  animation.setAnimationListener(this)
              }

              handler.postDelayed(r1 ,2000)
              val r3 = Runnable {
                  tv_2nd?.text = userDto.param2
                  onetext= tv_2nd?.text.toString()
                  tv_2nd?.startAnimation(animation)
                  Log.i("onetext12",""+userDto.param2)
                  animation.setAnimationListener(this)
              }

              handler.postDelayed(r3 ,3000)*/



        /*val value1: Int = tv_1st?.text.toString().toInt()
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
*/
        /*userDto.numresult = result.toString()
        tv_result?.text = userDto.numresult
              Thread(Runnable {
                  // a potentially  time consuming task

      //        tv_2nd!!.startAnimation(animation)
                 // animation.setAnimationListener(this)

                  tv_1st?.post(Runnable {
                      tv_1st.startAnimation(animation)
                      animation.setAnimationListener(this)

                  })
              }).start()


*/



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
            textspeak(text)
        })




        (container as ViewPager).addView(row)

        return row

    }


    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
//        textspeak("end")
    }

    override fun onAnimationStart(animation: Animation?) {
        textspeak(onetext)


    }
    fun textspeak(text: String?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
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
        Log.i("TTSstatus", status.toString())

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
    }

}