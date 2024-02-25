package com.example.jpogrenme

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jpogrenme.ui.theme.JPOgrenmeTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JPOgrenmeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                   // UnitConverter()
                    UnitConverter()
                }
            }
        }
    }
}



@SuppressLint("SuspiciousIndentation")
@Composable
fun UnitConverter(){
    var inputvalue by remember{ mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("Meters")}
    var outpututUnit by remember{ mutableStateOf("Meters")}
    var iExpanded by remember{ mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val conventionFactor = remember { mutableStateOf(1.0)}
    val oconventionFactor = remember { mutableStateOf(1.0)}

    fun conertUnits(){
        val inputValueDubble = inputvalue.toDoubleOrNull() ?: 0.0
        val result = ((inputValueDubble * conventionFactor.value * 100) / oconventionFactor.value ).roundToInt() / 100.0 /*
            Burada istenilen birimi metre cinsinden istenilene çevirdik
        */
        outputValue = result.toString()
    }


    Column(

        modifier = Modifier.fillMaxSize(), // Gidebildiği yere kadar alan kaplasın
        verticalArrangement = Arrangement.Center, // İçindeki bütün ögeler yatayda dikey Hizzalansın
        horizontalAlignment = Alignment.CenterHorizontally //Yatayda hizzalansın
    ) {
        var deger by remember{ mutableStateOf("string deger")}
        Text(text = "Unit Converter" , style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue /* Burada değerleri
            var sayılan olarak 'metre cinsinden' alıyoruz */
            , onValueChange = {//Nothing happend for now
            inputvalue = it
                conertUnits()
        },
            label = { Text(text = "Enter Value")})
        Row {
            Box{
                //Input button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conventionFactor.value = 0.01
                        conertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conventionFactor.value = 1.0
                        conertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conventionFactor.value = 0.3048
                        conertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Milimeters"
                        conventionFactor.value = 0.001
                        conertUnits()
                    })

                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Box{
                // Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outpututUnit)
                    Icon(Icons.Default.ArrowDropDown, "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded = false
                        outpututUnit = "Centimeters"
                        oconventionFactor.value = 0.01
                        conertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded = false
                        outpututUnit = "Meters"
                        oconventionFactor.value = 1.0
                        conertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        outpututUnit = "Feet"
                        oconventionFactor.value = 0.3048
                        conertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        oExpanded = false
                        outpututUnit = "Milimeters"
                        oconventionFactor.value = 0.001
                        conertUnits()
                    })

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Result $outputValue $outpututUnit" ,style = MaterialTheme.typography.headlineLarge)
}}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JPOgrenmeTheme {
        UnitConverter()

    }
}