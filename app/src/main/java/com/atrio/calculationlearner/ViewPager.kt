package com.atrio.calculationlearner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.atrio.calculationlearner.adapter.NumberPracticeAdapter
import com.atrio.calculationlearner.model.NumData
import kotlinx.android.synthetic.main.activity_view_pager.*
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v4.view.ViewPager.PageTransformer





/**
 * Created by Arpita Patel on 09-03-2018.
 */
class ViewPager: AppCompatActivity() {
    var mathsymbol: String?=null
    var datasymbol: String?=null
    var pagerAdapter: NumberPracticeAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        pager.offscreenPageLimit=2
        val whtsoptionvalue=intent.getStringExtra("categoryvalue")

        mathsymbol = whtsoptionvalue.substring(1)
        datasymbol = whtsoptionvalue.substring(0,1)


        pagerAdapter = NumberPracticeAdapter(this,generateData(whtsoptionvalue))
        pager.adapter=pagerAdapter
        pager.offscreenPageLimit = 1

        pager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


            }

            override fun onPageSelected(position: Int) {

                // Check if this is the page you want.

            }
        })

        pager.setPageTransformer(false, object : PageTransformer {
            override
            fun transformPage(page: View, position: Float) {

                val normalizedposition = Math.abs(Math.abs(position) - 1)
                page.alpha = normalizedposition
            }
        })



        btn_back?.setOnClickListener(View.OnClickListener {

            pager.setCurrentItem(getItemofviewpager(-1), true)
        })
        btn_next?.setOnClickListener(View.OnClickListener {
            pager.setCurrentItem(getItemofviewpager(+1), true)
        })


    }



    private fun getItemofviewpager(i: Int): Int {
        return pager.getCurrentItem() + i
    }



    private fun generateData(whtsoptionvalue: String): ArrayList<NumData> {
        var listresult = ArrayList<NumData>()

        if (datasymbol.equals("s")){
            for (a in 1..20) {
                var user: NumData = NumData("1", mathsymbol!!,a.toString(),"=","res")
                listresult.add(user)
            }

        }else{
            val numbers: IntArray = intArrayOf(3,5,6,2,10,14,17,4,2,18,7,12,19,1,15,8,20,13,9,11)
            for (a in numbers) {
                var user: NumData = NumData("1", mathsymbol!!, a.toString(), "=", "res")
                listresult.add(user)
            }
        }

        return listresult

    }

}
