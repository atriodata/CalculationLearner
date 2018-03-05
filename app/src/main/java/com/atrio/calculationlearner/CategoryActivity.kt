package com.atrio.calculationlearner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_category.*
import com.atrio.calculationlearner.animation.MyBounceInterpolator



class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

         val myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce) as Animation
        val interpolator = MyBounceInterpolator(0.2, 20.0)
        myAnim.interpolator = interpolator
        btn_add.startAnimation(myAnim)
        btn_sub.startAnimation(myAnim)
        btn_mul.startAnimation(myAnim)
        btn_div.startAnimation(myAnim)
    }
}
