package com.atrio.calculationlearner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.atrio.calculationlearner.adapter.TestAdapter
import com.atrio.calculationlearner.model.NumData
import kotlinx.android.synthetic.main.activity_test_series.*

class TestSeriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_series)

        val whtsoptionvalue=intent.getStringExtra("categoryvalue")
        listview_series?.adapter = TestAdapter(this,generateData(whtsoptionvalue))

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
