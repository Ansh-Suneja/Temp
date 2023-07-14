package com.example.tempconverter

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var  selectedUnitLayout:LinearLayout
    private lateinit var  selectedUnitText:TextView
    private lateinit var  editInput:EditText
    private lateinit var  textResult:TextView
    private lateinit var  resultTypeText:TextView

    private lateinit var  selectedUnit:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedUnitLayout= findViewById(R.id.selectType)
        selectedUnitText=findViewById(R.id.textSelact)
        editInput = findViewById(R.id.editInput)
        textResult= findViewById(R.id.textResult)
        resultTypeText= findViewById(R.id.textResultType)

        selectedUnit="Fahrenheit"

        selectedUnitLayout.setOnClickListener(){
            showAlertDialog()
        }

        editInput.addTextChangedListener(){
            var resultText:String=""
            var inputVal=editInput.text.toString()

            var df= DecimalFormat("#.##")

            if(inputVal.isNotEmpty()){
                val doubleInput=inputVal.toDouble()

                if(selectedUnit=="Fahrenheit"){
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    resultTypeText.text="Celsius"
                }
                else{
                    resultText = df.format((doubleInput *9/5)+32)
                    resultTypeText.text="Fahrenheit"

                }
                textResult.text=resultText

            }

        }





    }

    private fun showAlertDialog() {
        var alertDialog:AlertDialog.Builder=AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("select input unit")
        val items= arrayOf("Fahrenheit","Celsius")
        val checkedItem= -1

        alertDialog.setSingleChoiceItems(items,checkedItem,
            DialogInterface.OnClickListener(){dialog,which ->
            selectedUnit=items[which]
            selectedUnitText.setText(selectedUnit)

        })
        alertDialog.setPositiveButton(android.R.string.ok,
            DialogInterface.OnClickListener(){dialog,which ->
            dialog.dismiss()
        })
        var alert:AlertDialog=alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        
    }
}