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

class MainActivity() : AppCompatActivity(), View.OnClickListener {

    private lateinit var one:Button
    private lateinit var two:Button
    private lateinit var three:Button
    private lateinit var four:Button
    private lateinit var five:Button
    private lateinit var six:Button
    private lateinit var seven:Button
    private lateinit var eight:Button
    private lateinit var nine:Button
    private lateinit var zero:Button

    private lateinit var multiply:Button
    private lateinit var plus:Button
    private lateinit var delete:Button
    private lateinit var minus:Button
    private lateinit var dot:Button
    private lateinit var div:Button
    private lateinit var clear:Button
    private lateinit var oper:TextView
    private lateinit var result:TextView
    private var ispoint=true
    private var issymbol=false

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

        dot.setOnClickListener {
            if (ispoint){

                oper.text=oper.text.toString()+"."
                ispoint=false }
          }


        clear.setOnClickListener {
            oper.text="0" }



        multiply.setOnClickListener {
            addSymbol("x")
        }

        div.setOnClickListener {
           addSymbol("/")
        }
        plus.setOnClickListener {
            addSymbol(plus.text.toString())
        }
        minus.setOnClickListener {
            addSymbol(minus.text.toString())
        }

}











    override fun onClick(p0: View?) {
val btn=findViewById<Button>(p0!!.id)
        if (oper.text!="0")
        oper.text=oper.text.toString()+btn.text
        else
            oper.text=btn.text
        issymbol= true
    }


private fun calculate():String{
var r="0"
    createArray(oper.text.toString())
    return r
}


fun addSymbol(symbol:String){
    if (issymbol){
        oper.text=oper.text.toString()+symbol
        issymbol=false
    }
    else{
        if (oper.text!="0"){
            oper.text=oper.text.substring(0,oper.text.length-1)+symbol
    }}
    ispoint=false

}
fun createArray(s:String):MutableList<Any>{
    var list= mutableListOf<Any>()

    var temp=""
    for(i in s)
        if (i.isDigit() || i =='.'){
            temp+=i
        }
            else{
                list.add(temp)
            list.add(i)
            temp=""
        }
    if (temp.isNotEmpty()){
        list.add(temp)

    }

        return list

}



    fun initUI(){
        div=findViewById(R.id.div)
        multiply=findViewById(R.id.multiply)
        clear=findViewById(R.id.clear)
        oper=findViewById(R.id.oper)
        result=findViewById(R.id.result)
        dot=findViewById(R.id.dot)
        one=findViewById(R.id.one)
        two=findViewById(R.id.two)
        three=findViewById(R.id.three)
        four=findViewById(R.id.four)
        five=findViewById(R.id.five)
        six=findViewById(R.id.six)
        seven=findViewById(R.id.seven)
        eight=findViewById(R.id.eight)
        nine=findViewById(R.id.nine)
        zero=findViewById(R.id.zero)
        plus=findViewById(R.id.plus)
        minus=findViewById(R.id.minus)
        delete=findViewById(R.id.delete)
    }



}