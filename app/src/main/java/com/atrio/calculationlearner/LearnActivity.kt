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
import android.widget.Button


/**
 * Created by Arpita Patel on 21-03-2018.
 */
class LearnActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var mathsymbol: String?=null
    var datasymbol: String?=null

    var param2:IntArray?=null
    var tts: TextToSpeech? = null
    var param1onetext:String?=null
    var symbolonetext:String?=null
    var param2onetext:String?=null
    var equalonetext:String?=null
    var resultonetext:String?=null
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
                              param1onetext=param1.text.toString()
//                              onetext=param1onetext
                              tts!!.speak(param1onetext, TextToSpeech.QUEUE_FLUSH, null)
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
                              symbolonetext=symbol.text.toString()
                              if (symbolonetext.equals("\u00D7")) {
                                  symbolonetext = "into"
                              }
                              if (symbolonetext.equals("\u00F7")) {
                                  symbolonetext = "Divided By"
                              }
                              if (symbolonetext.equals("-")) {
                                  symbolonetext = "Minus"
                              }
                              tts!!.speak(symbolonetext, TextToSpeech.QUEUE_FLUSH, null)
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

                              param2onetext = param2a.text.toString()
                              tts!!.speak(param2onetext, TextToSpeech.QUEUE_FLUSH, null)
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

                              equalonetext = equal.text.toString()
                              tts!!.speak(equalonetext, TextToSpeech.QUEUE_FLUSH, null)
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

                              resultonetext = result.text.toString()
                              tts!!.speak(resultonetext, TextToSpeech.QUEUE_FLUSH, null)
                          }

                          override fun onAnimationStart(animation: Animation?) {
                          }

                      })

                  }
                  var btn_speak: Button = page.findViewById<Button>(R.id.btn_speak)
        btn_speak?.setOnClickListener(View.OnClickListener {
            var btntext = param1onetext + symbolonetext + param2onetext + equalonetext + resultonetext
            tts!!.speak(btntext, TextToSpeech.QUEUE_FLUSH, null)
        })




              }
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
//            textspeak(onetext)
        }


    }

}