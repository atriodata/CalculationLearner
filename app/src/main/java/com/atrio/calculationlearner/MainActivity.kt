package com.atrio.calculationlearner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_learn.setOnClickListener(View.OnClickListener {
            intent= Intent(this,CategoryActivity::class.java)
            intent.putExtra("sub","learn")
            startActivity(intent)
        })
        btn_test.setOnClickListener(View.OnClickListener {
            intent= Intent(this,CategoryActivity::class.java)
            intent.putExtra("sub","test")
            startActivity(intent)
        })
    }
}
