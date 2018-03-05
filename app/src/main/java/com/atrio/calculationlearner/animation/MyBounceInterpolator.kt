package com.atrio.calculationlearner.animation

import android.view.animation.Interpolator

/**
 * Created by Arpita Patel on 02-03-2018.
 */
class MyBounceInterpolator(amplitude:Double,frequency:Double):Interpolator{

    private val mAmplitude = 1.0
    private val mFrequency = 10.0

    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1).toFloat()
    }

}