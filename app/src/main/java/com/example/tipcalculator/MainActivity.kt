package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                TipCalculatorApp(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun TipCalculatorApp(modifier: Modifier = Modifier) {
    // State variables
    var billValue by remember { mutableFloatStateOf(0f) }
    var tipPercentage by remember { mutableFloatStateOf(0.15f) }
    var roundOff by remember { mutableStateOf(false) }

    // UI Layout
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.hsl(120f, 0.3f, 0.95f))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bill Value
        BillValueCard(billValue = billValue, onValueChange = { billValue = it })

        // Space
        Spacer(modifier = Modifier.height(16.dp))

        // Tip Percentage
        TipPercentageCard(tipPercentage = tipPercentage, onValueChange = { tipPercentage = it })

        // Space
        Spacer(modifier = Modifier.height(16.dp))

        // Round off
        RoundOffCard(roundOff = roundOff, onValueChange = { roundOff = it })

        // Space
        Spacer(modifier = Modifier.height(16.dp))

        // Tip Value
        TipValueCard(billValue = billValue, tipPercentage = tipPercentage, roundOff = roundOff)

        // Space
        Spacer(modifier = Modifier.height(16.dp))

        // Total Value
        TotalValueCard(billValue = billValue, tipPercentage = tipPercentage, roundOff = roundOff)
    }
}

@Composable
fun BillValueCard(billValue: Float, onValueChange: (Float) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Bill Value", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = if (billValue == 0f) "" else billValue.toString(),
                onValueChange = {
                    onValueChange(it.toFloatOrNull() ?: 0f)
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Enter Bill Value") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Text("$", style = MaterialTheme.typography.bodyMedium)
                }
            )
        }
    }
}

@Composable
fun TipPercentageCard(tipPercentage: Float, onValueChange: (Float) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Tip Percentage", style = MaterialTheme.typography.titleMedium)
            Slider(
                value = tipPercentage,
                onValueChange = onValueChange,
                valueRange = 0f..0.30f,
                steps = 5
            )
            Text(
                text = "${(tipPercentage * 100).toInt()}%",
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun RoundOffCard(roundOff: Boolean, onValueChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Round Off", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = roundOff,
                onCheckedChange = {
                    onValueChange(it)
                }
            )
        }
    }
}

@Composable
fun TipValueCard(billValue: Float, tipPercentage: Float, roundOff: Boolean) {
    // This will contain the tip value
    val tipValue = billValue * tipPercentage
    val roundedTipValue = if (roundOff) {
        tipValue.toInt().toFloat()
    } else {
        tipValue
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Tip Value",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$roundedTipValue",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun TotalValueCard(billValue: Float, tipPercentage: Float, roundOff: Boolean) {
    // This will contain the total value
    val tipValue = billValue * tipPercentage
    val roundedTipValue = if (roundOff) {
        tipValue.toInt().toFloat()
    } else {
        tipValue
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Total Value",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${billValue + roundedTipValue}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    TipCalculatorTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            TipCalculatorApp(modifier = Modifier.padding(it))
        }
    }
}
