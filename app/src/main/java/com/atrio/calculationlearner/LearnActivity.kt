package com.atrio.calculationlearner

import android.widget.TextView
import android.support.v4.app.NotificationCompat.getExtras
import android.content.Intent.getIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.atrio.calculationlearner.adapter.LearnPagerAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.util.Log
import java.util.*


/**
 * Created by Arpita Patel on 21-03-2018.
 */
class LearnActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var mathsymbol: String?=null
    var datasymbol: String?=null
    var param2:IntArray?=null
    var tts: TextToSpeech? = null
    var onetext:String?=null
    var learnPagerAdapter:LearnPagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val whtsoptionvalue=intent.getStringExtra("categoryvalue")

        mathsymbol = whtsoptionvalue.substring(1)
        datasymbol = whtsoptionvalue.substring(0,1)
        tts = TextToSpeech(this, this)

        btn_back?.setOnClickListener(View.OnClickListener {
            pager.setCurrentItem(getItemofviewpager(-1), true)
        })
        btn_next?.setOnClickListener(View.OnClickListener {
            pager.setCurrentItem(getItemofviewpager(+1), true)
        })

        if (datasymbol.equals("s")){
            param2= intArrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)

        }else{
            param2= intArrayOf(3,5,6,2,10,14,17,4,2,18,7,12,19,1,15,8,20,13,9,11)
        }

       learnPagerAdapter= LearnPagerAdapter(this,"1",mathsymbol!!,param2!!,"=","getresult")
        pager.adapter=learnPagerAdapter
        pager.currentItem
        Log.i("print899",pager.currentItem.toString())
//        pager.offscreenPageLimit=1


        if (pager.currentItem==0){
            onetext = param2!![0].toString()
            learnPagerAdapter?.viewgetting()
/*
            Thread(Runnable {
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
*/


            Log.i("print8944",onetext)
//            tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)

        }
        pager.addOnPageChangeListener(object : OnPageChangeListener {
          override  fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
              Log.i("currentposition1" , param2!![position].toString())
              onetext = param2!![position].toString()
              tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)

             /* if (onetext.equals("1")){
                  tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
              }*/

            }

           override fun onPageSelected(position: Int) {
               Log.i("currentposition8" , param2!![position].toString())
               onetext = param2!![position].toString()
            }

           override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


    private fun getItemofviewpager(i: Int): Int {
        return pager.getCurrentItem() + i
    }
    fun textspeak(text: String?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.tts!!.speak(text, TextToSpeech.QUEUE_ADD, null)
            this.tts!!.setSpeechRate(0.7f)
        }else{

        }
    }
    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            tts!!.language = Locale.UK
            tts!!.setSpeechRate(0.5.toFloat())
            textspeak(onetext)
        }


    }

}