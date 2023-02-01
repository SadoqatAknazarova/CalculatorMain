package uz.itschool.calculator

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly

class MainActivity() : AppCompatActivity(), View.OnClickListener {

    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button

    private lateinit var percent:Button
    private lateinit var bracket:Button
    private lateinit var multiply: Button
    private lateinit var plus: Button
    private lateinit var delete: Button
    private lateinit var minus: Button
    private lateinit var dot: Button
    private lateinit var div: Button
    private lateinit var equal: Button
    private lateinit var clear: Button
    private lateinit var oper: TextView
    private lateinit var result: TextView
    private var ispoint = true

    private var a=""
    private var issymbol = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()


        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)
        percent.setOnClickListener(this)
        bracket.setOnClickListener(this)

        dot.setOnClickListener {
            if (ispoint) {
                if (oper.text.substring(oper.text.length - 1, oper.text.length).isDigitsOnly())

                    oper.text = oper.text.toString() + "."
                ispoint = false
            }
        }

        clear.setOnClickListener {
            oper.text = "0"
            result.text=""
        }

        delete.setOnClickListener {
            oper.text = oper.text.substring(0, oper.text.length - 1)
        }

        multiply.setOnClickListener {
            addSymbol("x")
        }

        div.setOnClickListener {
            addSymbol("/")
        }
        percent.setOnClickListener {
            addSymbol("%")
        }
        plus.setOnClickListener {
            addSymbol(plus.text.toString())
        }
        minus.setOnClickListener {
            addSymbol("-")
        }
        equal.setOnClickListener {
            var list = createArray(oper.text.toString())
            var perc_list=percentage(list)
            var l = step1(perc_list)
            result.text = step2(l)

        }
        percent.setOnClickListener {
            addSymbol("%")
        }

     bracket.setOnClickListener {

    a=oper.text.substring(oper.text.length-1, oper.text.length)
             if(a.isDigitsOnly()) {
                oper.text = oper.text.substring(0, oper.text.length - 1) + "(-" + a+ ")"
             }


      }
    }

    override fun onClick(p0: View?) {
        val btn = findViewById<Button>(p0!!.id)
        if (oper.text != "0")
            oper.text = oper.text.toString() + btn.text
        else
            oper.text = btn.text
        issymbol = true
    }


  private fun calculate(): String {
      var r = "0"
      createArray(oper.text.toString())
      return r
  }


  fun addSymbol(symbol: String) {
      if (issymbol) {
          oper.text = oper.text.toString() + symbol
          issymbol = false
      } else {
          if (oper.text != "0") {
              oper.text = oper.text.substring(0, oper.text.length - 1) + symbol
          }
      }
      ispoint = true

  }

fun createArray(s:String):MutableList<Any>{
   var list = mutableListOf<Any>()
   var temp = ""
   var minus = 1

   for(i in 0.. s.length-1){
       if (s[i]=='('){
           minus = -1
       }
       else if(s[i].isDigit() || s[i]=='.'){
           temp+=s[i]
       }

       else if(s[i-1]!='(' && (s[i]=='+' || s[i]=='-' ||  s[i]=='x' || s[i]=='/')){
           list.add(temp.toFloat())
           list.add(s[i])
           temp=""
       }
   }
   if(temp.isNotEmpty()){
      var t:Float =  temp.toFloat() *minus
     minus =1
     list.add(t)
  }

   return list
}


  var list_solve= mutableListOf<Any>()
  fun step1(problem_list:MutableList<Any>):MutableList<Any>{

      var temp = 0f

      var i = 0
      while (problem_list.contains('x') || problem_list.contains('/')){
          if(problem_list[i]=='x' || problem_list[i]=='/'){
              var next:Float =problem_list[i-1].toString().toFloat()
              var prev:Float = problem_list[i+1].toString().toFloat()
              when(problem_list[i]){
                  'x'->{
                      temp = prev  *next

                  }

                  '/'->{
                      temp = next/prev
                  }
              }
              problem_list[i-1] = temp
              problem_list.removeAt(i)
              problem_list.removeAt(i)
              i = i-2
          }
          i++
      }
      Log.d("TAG", problem_list.toString())
      list_solve=problem_list
      return problem_list
  }

  fun step2(problem_list:MutableList<Any>):String{

      var i = 0
      var temp = 0f
      while (problem_list.contains('+') || problem_list.contains('-')){
          if(problem_list[i]=='+' || problem_list[i]=='-'){
              var next:Float = problem_list[i-1].toString().toFloat()
              var prev:Float = problem_list[i+1].toString().toFloat()
              when(problem_list[i]){
                  '+'->{
                      temp = next+prev

                  }
                  '-'->{
                      temp = next-prev
                  }
              }
              problem_list[i-1] = temp
              problem_list.removeAt(i)
              problem_list.removeAt(i)
              i = i-2
          }
          i++
      }
      var res:String=problem_list[0].toString()
      return res
  }

  fun percentage(problem_list:MutableList<Any>):MutableList<Any>{
      var i = 0
      var temp = 0f
      while (problem_list.contains('%')){
          Log.d("TAG", temp.toString())
          if(problem_list[i]=='%'){
              var next:Float = problem_list[i-1].toString().toFloat()
              var prev:Float = problem_list[i+1].toString().toFloat()
              when (problem_list[i]){
                  '%'->{

                 temp = (next*prev/100).toFloat()
                     problem_list[i]=temp
             }
             }
             problem_list[i-1] = temp
             problem_list.removeAt(i)
             problem_list.removeAt(i)
             i = i-2

         }
         i++
     }
     return problem_list
}


    fun initUI() {
        percent=findViewById(R.id.percent)
        div = findViewById(R.id.div)
        bracket = findViewById(R.id.bracket)
        equal = findViewById(R.id.equal)
        multiply = findViewById(R.id.multiply)
        clear = findViewById(R.id.clear)
        oper = findViewById(R.id.oper)
        result = findViewById(R.id.result)
        dot = findViewById(R.id.dot)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        delete = findViewById(R.id.delete)
    }


}