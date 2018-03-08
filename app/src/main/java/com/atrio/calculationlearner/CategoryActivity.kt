package com.atrio.calculationlearner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_category.*



class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        btn_adds.setOnClickListener(View.OnClickListener {
            intent=Intent(this@CategoryActivity,TestSeriesActivity::class.java)
            intent.putExtra("categoryvalue","+")
            startActivity(intent)
        })
        btn_subs.setOnClickListener(View.OnClickListener {
            intent=Intent(this@CategoryActivity,TestSeriesActivity::class.java)
            intent.putExtra("categoryvalue","-")
            startActivity(intent)
        })
        btn_muls.setOnClickListener(View.OnClickListener {
            intent=Intent(this@CategoryActivity,TestSeriesActivity::class.java)
            intent.putExtra("categoryvalue","*")
            startActivity(intent)
        })
        btn_divs.setOnClickListener(View.OnClickListener {
            intent=Intent(this@CategoryActivity,TestSeriesActivity::class.java)
            intent.putExtra("categoryvalue","/")
            startActivity(intent)
        })
    }
}
