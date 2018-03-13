package com.atrio.calculationlearner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_category.*



class CategoryActivity : AppCompatActivity() {
var sub :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        sub =intent.getStringExtra("sub")
        btn_adds.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")){
                intent=Intent(this@CategoryActivity,ViewPager::class.java)
                intent.putExtra("categoryvalue","+")
                startActivity(intent)
            }else{
                intent=Intent(this@CategoryActivity,PractiseActivity::class.java)
                intent.putExtra("categoryvalue","Addition")
                startActivity(intent)

            }

        })
        btn_subs.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")){
                intent=Intent(this@CategoryActivity,ViewPager::class.java)
                intent.putExtra("categoryvalue","-")
                startActivity(intent)
            }else{
                intent=Intent(this@CategoryActivity,PractiseActivity::class.java)
                intent.putExtra("categoryvalue","substraction")
                startActivity(intent)

            }

        })
        btn_muls.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")) {
                intent = Intent(this@CategoryActivity, ViewPager::class.java)
                intent.putExtra("categoryvalue", "*")
                startActivity(intent)
            }else{
                intent = Intent(this@CategoryActivity, PractiseActivity::class.java)
                intent.putExtra("categoryvalue", "Multipllication ")
                startActivity(intent)
            }
        })
        btn_divs.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")) {
                intent = Intent(this@CategoryActivity, ViewPager::class.java)
                intent.putExtra("categoryvalue", "/")
                startActivity(intent)
            }else{
                intent = Intent(this@CategoryActivity, PractiseActivity::class.java)
                intent.putExtra("categoryvalue", "Division")
                startActivity(intent)
            }
        })

        btn_addr.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")){
                intent=Intent(this@CategoryActivity,ViewPager::class.java)
                intent.putExtra("categoryvalue","+")
                startActivity(intent)
            }else{
                intent=Intent(this@CategoryActivity,PractiseActivity::class.java)
                intent.putExtra("categoryvalue","RandomAddition")
                startActivity(intent)
            }

        })
        btn_subr.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")){
                intent=Intent(this@CategoryActivity,ViewPager::class.java)
                intent.putExtra("categoryvalue","-")
                startActivity(intent)
            }else{
                intent=Intent(this@CategoryActivity,PractiseActivity::class.java)
                intent.putExtra("categoryvalue","RANDDOMSUBTR")
                startActivity(intent)
            }

        })
        btn_mulr.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")){
                intent=Intent(this@CategoryActivity,ViewPager::class.java)
                intent.putExtra("categoryvalue","*")
                startActivity(intent)
            }else{
                intent=Intent(this@CategoryActivity,PractiseActivity::class.java)
                intent.putExtra("categoryvalue","RANDOMMULTI")
                startActivity(intent)
            }


        })
        btn_divr.setOnClickListener(View.OnClickListener {
            if (sub.equals("learn")){
                intent=Intent(this@CategoryActivity,ViewPager::class.java)
                intent.putExtra("categoryvalue","/")
                startActivity(intent)
            }else{
                intent=Intent(this@CategoryActivity,PractiseActivity::class.java)
                intent.putExtra("categoryvalue","Randomdivision")
                startActivity(intent)
            }

        })
    }
}
