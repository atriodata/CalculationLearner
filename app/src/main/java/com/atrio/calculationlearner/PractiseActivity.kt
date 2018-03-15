package com.atrio.calculationlearner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.atrio.calculationlearner.model.Practice
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_practise.*

class PractiseActivity : AppCompatActivity() {
    lateinit var rootRef: DatabaseReference
    var corrctans : String? = null
    var no : Int = 1
    var qno : String? = null
    var whtsoptionvalue:String? = null
    var data_number : String? = null
    var data_number1 : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practise)
        rootRef = FirebaseDatabase.getInstance().reference

        whtsoptionvalue = intent.getStringExtra("categoryvalue")

        Log.i("value11",""+whtsoptionvalue)
        qno = "Question-${String.format("%03d",no)}"

                Log.i("qno11",""+qno)
                getData(qno,whtsoptionvalue)

        bt_nxt.setOnClickListener(View.OnClickListener {
            radioGroup.clearCheck()
            rg_2.clearCheck()
            rd_opt1.setTextColor(resources.getColor(android.R.color.black))
            rd_opt2.setTextColor(resources.getColor(android.R.color.black))
            rd_opt3.setTextColor(resources.getColor(android.R.color.black))
            rd_opt4.setTextColor(resources.getColor(android.R.color.black))
            rd_opt1.setClickable(true)
            rd_opt2.setClickable(true)
            rd_opt3.setClickable(true)
            rd_opt4.setClickable(true)

            no++
            qno = "Question-${String.format("%03d",no)}"
            getData(qno,whtsoptionvalue)
        })



        radioGroup.setOnCheckedChangeListener { group, checkedId ->


            when(checkedId){
                R.id.rd_opt1 -> if (corrctans!!.equals(rd_opt1.text)){
                    Log.i("radio11",""+rd_opt1.text)
                    rd_opt1.setClickable(false)
                    rd_opt2.setClickable(false)
                    rd_opt3.setClickable(false)
                    rd_opt4.setClickable(false)
                    rd_opt1.setTextColor(resources.getColor(android.R.color.holo_green_light))
                }else{
                    when(corrctans){
                        rd_opt2.text->rd_opt2.setTextColor(resources.getColor(android.R.color.holo_green_light))
                        rd_opt3.text->rd_opt3.setTextColor(resources.getColor(android.R.color.holo_green_light))
                        rd_opt4.text->rd_opt4.setTextColor(resources.getColor(android.R.color.holo_green_light))

                    }
                    rd_opt1.setClickable(false)
                    rd_opt2.setClickable(false)
                    rd_opt3.setClickable(false)
                    rd_opt4.setClickable(false)
                    rd_opt1.setTextColor(resources.getColor(android.R.color.holo_red_light))
                }


                R.id.rd_opt2 -> if (corrctans!!.equals(rd_opt2.text)){

                    rd_opt1.setClickable(false)
                    rd_opt2.setClickable(false)
                    rd_opt3.setClickable(false)
                    rd_opt4.setClickable(false)
                    rd_opt2.setTextColor(resources.getColor(android.R.color.holo_green_light))
                }else{
                    when(corrctans){
                        rd_opt1.text->rd_opt1.setTextColor(resources.getColor(android.R.color.holo_green_light))
                        rd_opt3.text->rd_opt3.setTextColor(resources.getColor(android.R.color.holo_green_light))
                        rd_opt4.text->rd_opt4.setTextColor(resources.getColor(android.R.color.holo_green_light))

                    }
                    rd_opt1.setClickable(false)
                    rd_opt2.setClickable(false)
                    rd_opt3.setClickable(false)
                    rd_opt4.setClickable(false)
                    rd_opt2.setTextColor(resources.getColor(android.R.color.holo_red_light))
                }


            }

        }
       rg_2.setOnCheckedChangeListener { group, checkedId ->

           when(checkedId){
               R.id.rd_opt3 -> if (corrctans!!.equals(rd_opt3.text)){
                   Log.i("radio11",""+rd_opt3.text)
                   rd_opt1.setClickable(false)
                   rd_opt2.setClickable(false)
                   rd_opt3.setClickable(false)
                   rd_opt4.setClickable(false)
                   rd_opt3.setTextColor(resources.getColor(android.R.color.holo_green_light))
               }else{
                   when(corrctans){
                       rd_opt1.text->rd_opt1.setTextColor(resources.getColor(android.R.color.holo_green_light))
                       rd_opt2.text->rd_opt2.setTextColor(resources.getColor(android.R.color.holo_green_light))
                       rd_opt4.text->rd_opt4.setTextColor(resources.getColor(android.R.color.holo_green_light))

                   }
                   rd_opt1.setClickable(false)
                   rd_opt2.setClickable(false)
                   rd_opt3.setClickable(false)
                   rd_opt4.setClickable(false)
                   rd_opt3.setTextColor(resources.getColor(android.R.color.holo_red_light))
               }
               R.id.rd_opt4 -> if (corrctans!!.equals(rd_opt4.text)){
                   Log.i("radio11",""+rd_opt4.text)
                   rd_opt1.setClickable(false)
                   rd_opt2.setClickable(false)
                   rd_opt3.setClickable(false)
                   rd_opt4.setClickable(false)
                   rd_opt4.setTextColor(resources.getColor(android.R.color.holo_green_light))
               }else{
                   when(corrctans){
                       rd_opt1.text->rd_opt1.setTextColor(resources.getColor(android.R.color.holo_green_light))
                       rd_opt2.text->rd_opt2.setTextColor(resources.getColor(android.R.color.holo_green_light))
                       rd_opt3.text->rd_opt3.setTextColor(resources.getColor(android.R.color.holo_green_light))


                   }
                   rd_opt1.setClickable(false)
                   rd_opt2.setClickable(false)
                   rd_opt3.setClickable(false)
                   rd_opt4.setClickable(false)
                   rd_opt4.setTextColor(resources.getColor(android.R.color.holo_red_light))
               }

           }

       }
    }

    private fun getData(qno: String?, value: String?) {
        val query_catlist = rootRef.child("QuesttionList").child(value).child(qno).orderByKey()
        Log.i("query_catlist33",""+query_catlist.ref.toString())
        query_catlist.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

                Log.i("Practice112525",""+p0.toString())

            }

            override fun onDataChange(p0: DataSnapshot?) {
                Log.i("Practice1111",""+p0?.ref.toString())
                //
                // var subkey = p0?.getKey()
                Log.i("Practice11",""+p0.toString())
                var practice =  p0?.getValue(Practice::class.java)
                var question =practice?.Question
                var optionA =practice?.optionA
                var optionB =practice?.optionB
                var optionC =practice?.optionC
                var optionD =practice?.optionD
                corrctans =practice?.correctAnswer


               /* Log.i("Practice11",""+practice?.Question)
                Log.i("Practice11",""+practice?.optionA)
                Log.i("Practice11",""+practice?.optionB)
                Log.i("Practice11",""+practice?.optionD)
                Log.i("Practice11",""+practice?.correctAnswer)*/
                setData(question,optionA,optionB,optionC,optionD,value)

            }

        })
    }

    private fun setData(question: String?, optionA: String?, optionB: String?, optionC: String?, optionD: String?, value: String?) {
      //  tv_ques.setText(question)
        Log.i("question11",""+question)

        when(value){
            "Addition"-> {
                //val regex = "\\s*,\\s*"
                val obj :List<String> = question?.trim()!!.split("+","=").toList()
                Log.i("obj11",""+obj)

                for(i in 0..obj.size-1){
                   Log.i("data11",""+ obj[i])
                 if(i == 0){
                     data_number = obj[i]

                 }
                    if(i== 1){
                     data_number1 = obj[i]
                        Log.i("data11num",""+ data_number1)
                 }

                }
                bt_number.setText(data_number)
                bt_number1.setText(data_number1)
                bt_symbol.setText("+")

            }


        }

       // question.split("+")

        rd_opt1.setText(optionA)
        rd_opt2.setText(optionB)
        rd_opt3.setText(optionC)
        rd_opt4.setText(optionD)


    }
}
