package com.atrio.calculationlearner

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.atrio.calculationlearner.adapter.NumberPracticeAdapter
import com.atrio.calculationlearner.adapter.TestAdapter
import com.atrio.calculationlearner.model.NumData
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.practice_one_view.*

/**
 * Created by Arpita Patel on 09-03-2018.
 */
class ViewPager: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val whtsoptionvalue=intent.getStringExtra("categoryvalue")


        pager.adapter=NumberPracticeAdapter(this,generateData(whtsoptionvalue))
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

        for (a in 1..20) {
            var user: NumData = NumData("1", whtsoptionvalue,a.toString(),"=","res")
            listresult.add(user)
        }

        return listresult

    }

}