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
import android.support.v4.view.ViewPager.PageTransformer
import android.opengl.ETC1.getWidth
import android.support.v4.view.ViewPager
import android.view.animation.Animation
import android.view.animation.AnimationUtils


/**
 * Created by Arpita Patel on 21-03-2018.
 */
class LearnActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var mathsymbol: String?=null
    var datasymbol: String?=null
/*    var param1: String?=null
    val symbol:String?=null
    val equal:String?=null
    var numresult: String?=null*/

    var param2:IntArray?=null
    var tts: TextToSpeech? = null
    var onetext:String?=null
    var learnPagerAdapter:LearnPagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val whtsoptionvalue=intent.getStringExtra("categoryvalue")

        val animationparam1 = AnimationUtils.loadAnimation(this, R.anim.slide)
        val animationsymbol = AnimationUtils.loadAnimation(this, R.anim.slide)
        val animationparam2 = AnimationUtils.loadAnimation(this, R.anim.slide)
        val animationequal = AnimationUtils.loadAnimation(this, R.anim.slide)
        val animationresult = AnimationUtils.loadAnimation(this, R.anim.slide)


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
        pager.setPageTransformer(false, object : PageTransformer {
          override  fun transformPage(page: View, position: Float) {
              if (position <= -1.0f || position >= 1.0f) {

              } else if (position == 0.0f) {

              } else {

                  var param1: TextView = page.findViewById<TextView>(R.id.tv_1st)
                  if (param1 != null){
                     /*
                      param1.setAlpha(1.0f - absPosition * 2)*/
                      param1.startAnimation(animationparam1)
                      animationparam1.setAnimationListener(object : Animation.AnimationListener {
                          override fun onAnimationRepeat(animation: Animation?) {
                          }

                          override fun onAnimationEnd(animation: Animation?) {
                              onetext=param1.text.toString()
                              tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                          }

                          override fun onAnimationStart(animation: Animation?) {
                          }

                      })
                  }

                  var symbol: TextView = page.findViewById<TextView>(R.id.tv_symbol)
                  if (symbol != null) {
                      symbol?.startAnimation(animationsymbol)
                      animationsymbol.startOffset = (3000)
                      animationsymbol.setAnimationListener(object : Animation.AnimationListener {
                          override fun onAnimationRepeat(animation: Animation?) {
                          }

                          override fun onAnimationEnd(animation: Animation?) {

                              onetext=symbol.text.toString()
                              tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                          }

                          override fun onAnimationStart(animation: Animation?) {
                          }

                      })
                  }

                  var param2a: TextView = page.findViewById<TextView>(R.id.tv_2st)
                  if (param2a != null) {
                      param2a?.startAnimation(animationparam2)

                      animationparam2.startOffset = (6000)
                      animationparam2.setAnimationListener(object : Animation.AnimationListener {
                          override fun onAnimationRepeat(animation: Animation?) {
                          }

                          override fun onAnimationEnd(animation: Animation?) {

                              onetext = param2a.text.toString()
//                              Log.i("getpredata",param2[position].toString())
//                Log.i("getpredata1",(param2[position]-1).toString())
                              Log.i("getpredata2",onetext)
                              tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                          }

                          override fun onAnimationStart(animation: Animation?) {
                          }

                      })

                  }
                  var equal: TextView = page.findViewById<TextView>(R.id.tv_equals)

                  if (equal != null) {
                      equal?.startAnimation(animationequal)

                      animationequal.startOffset = (9000)
                      animationequal.setAnimationListener(object : Animation.AnimationListener {
                          override fun onAnimationRepeat(animation: Animation?) {
                          }

                          override fun onAnimationEnd(animation: Animation?) {

                              onetext = equal.text.toString()
//                              Log.i("getpredata",param2[position].toString())
//                Log.i("getpredata1",(param2[position]-1).toString())
                              Log.i("getpredata2",onetext)
                              tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                          }

                          override fun onAnimationStart(animation: Animation?) {
                          }

                      })

                  }
                  var result: TextView = page.findViewById<TextView>(R.id.tv_result)

                  if (result != null) {
                      result?.startAnimation(animationresult)

                      animationresult.startOffset = (12000)
                      animationresult.setAnimationListener(object : Animation.AnimationListener {
                          override fun onAnimationRepeat(animation: Animation?) {
                          }

                          override fun onAnimationEnd(animation: Animation?) {

                              onetext = result.text.toString()
//                              Log.i("getpredata",param2[position].toString())
//                Log.i("getpredata1",(param2[position]-1).toString())
                              Log.i("getpredata2",onetext)
                              tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
                          }

                          override fun onAnimationStart(animation: Animation?) {
                          }

                      })

                  }

              }
          }
          })


//        Log.i("print899",pager.currentItem.toString())
//        pager.offscreenPageLimit=1


/*
        if (pager.currentItem==0){
            Thread(Runnable {
                Thread.sleep(1000)
                onetext = param1
                textspeak(onetext)
            }).start()
            Thread(Runnable {
                Thread.sleep(3000)
                onetext = mathsymbol
                textspeak(onetext)
            }).start()
            Thread(Runnable {
                Thread.sleep(6000)
                onetext = param2!![0].toString()
                textspeak(onetext)
            }).start()
            Thread(Runnable {
                Thread.sleep(9000)
                onetext =equal
                textspeak(onetext)
            }).start()
            Thread(Runnable {
                Thread.sleep(12000)
                onetext = numresult
                textspeak(onetext)
            }).start()


//            learnPagerAdapter?.speaktextone(onetext!!)
        }
*/
/*
        pager.addOnPageChangeListener(object : OnPageChangeListener {
          override  fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
              Log.i("currentposition1" , param2!![position].toString())
//              onetext = param2!![position].toString()
//              tts!!.speak(onetext, TextToSpeech.QUEUE_FLUSH, null)
              */
/*val params = animatedView.getLayoutParams() as ViewPager.LayoutParams
              params.setMargins(((position + positionOffset) * 500) as Int, 0, 0, 0)
              animatedView.setLayoutParams(params)*//*

            }

           override fun onPageSelected(position: Int) {
               Log.i("currentposition8" , param2!![position].toString())
//               onetext = param2!![position].toString()
            }

           override fun onPageScrollStateChanged(state: Int) {

            }
        })
*/
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