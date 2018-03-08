package com.atrio.calculationlearner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.atrio.calculationlearner.adapter.TestAdapter
import com.atrio.calculationlearner.model.NumData
import kotlinx.android.synthetic.main.activity_test_random.*

class TestRandomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_random)

        val whtsoptionvalue = intent.getStringExtra("categoryvalue")

        listview_random?.adapter = TestAdapter(this, generateData(whtsoptionvalue))

    }

    private fun generateData(whtsoptionvalue: String): ArrayList<NumData> {
        var listresult = ArrayList<NumData>()
        val numbers: IntArray = intArrayOf(3,5,6,2,10,14,17,4,2,18,7,12,19,1,15,8,20,13,9,11)
        for (a in numbers) {
            var user: NumData = NumData("1", whtsoptionvalue, a.toString(), "=", "res")
            listresult.add(user)
        }

        return listresult

    }
}