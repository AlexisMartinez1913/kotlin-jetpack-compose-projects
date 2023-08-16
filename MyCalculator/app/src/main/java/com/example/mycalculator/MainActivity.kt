package com.example.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycalculator.ui.theme.MyCalculatorTheme
import java.lang.Exception

import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorApp()

                }
            }
        }
    }
}
@Composable
fun CalculatorButton(text:String,  onClick: () ->Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(64.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }

}
@Composable
fun CalculatorApp(){
    var inputText by rememberSaveable{ mutableStateOf("") }
    MyCalculatorTheme {
        Surface(color = Color.LightGray, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = inputText,
                    onValueChange = {inputText=it},
                    modifier = Modifier.padding(28.dp))
                
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton("7") {inputText += "7"}
                    CalculatorButton("8") {inputText += "8"}
                    CalculatorButton("9") {inputText += "9"}
                    CalculatorButton("÷") {inputText += "/"}

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton("4") {inputText += "4"}
                    CalculatorButton("5") {inputText += "5"}
                    CalculatorButton("6") {inputText += "6"}
                    CalculatorButton("×") {inputText += "*"}

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton("1") {inputText += "1"}
                    CalculatorButton("2") {inputText += "2"}
                    CalculatorButton("3") {inputText += "3"}
                    CalculatorButton("+") {inputText += "+"}
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton("0") {inputText += "0"}
                    CalculatorButton("⌫") {
                        if (inputText.isNotEmpty()){
                            inputText = inputText.dropLast(1)
                        }
                    }
                    CalculatorButton("="){
                        try {
                            inputText = evaluateExpression(inputText)
                        }catch (e:Exception){
                            inputText = "Error"
                        }
                    }
                    CalculatorButton("-") {inputText += "-"}
                }

            }

        }

    }
}

//funcion para las operaciones de la calculadora
private fun evaluateExpression(expression:String):String{
    return try {
        val result = ExpressionBuilder(expression).build().evaluate()
        result.toString()

    }catch (e: Exception){
        "Error"
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCalculatorTheme {
        CalculatorApp()
    }
}