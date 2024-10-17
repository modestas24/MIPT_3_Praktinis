package com.example.mipt_3_praktinis

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mipt_3_praktinis.ui.components.CalculatorInput
import com.example.mipt_3_praktinis.ui.components.CalculatorOutput
import com.example.mipt_3_praktinis.ui.theme.CustomTheme
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        enableEdgeToEdge()
        setContent {
            CustomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        var text by remember { mutableStateOf("") }

                        fun onClick(value: String) {
                            if (text == "NaN") text = ""
                            when (value) {
                                "<-" -> text = text.dropLast(1)
                                "CE" -> text = ""
                                "C" -> text = ""
                                "MC" -> text = ""
                                "MR" -> text = ""
                                "MS" -> text = ""
                                "M+" -> text = ""
                                "M-" -> text = ""
                                "±" -> text = changeSign(text)
                                "=" -> text = Expression(text).calculate().toString()
                                else -> text += value
                            }
                        }

                        CalculatorOutput(
                            text = text, modifier = Modifier.weight(1f)
                        )
                        CalculatorInput(
                            onClick = ::onClick, modifier = Modifier.weight(3f)
                        )
                    }
                }
            }
        }
    }
}

fun changeSign(text: String): String {
    if (text.isEmpty()) return text

//    Finding last digit's symbol in text.
    var lastSymbolIndex: Int? = null
    var lastDigitIndex: Int? = null
    for (i in text.length-1 downTo 0) {
        if (text[i].isDigit()) {
            lastDigitIndex = i
        }
        if (!text[i].isDigit() && text[i] != '.') {
            lastSymbolIndex = i
            break
        }
    }

//    Processing digit's symbol.
    if (lastDigitIndex == null) return text
    if (lastSymbolIndex == null) return text.padStart(text.length + 1, '-')
    if (lastSymbolIndex + 1 == lastDigitIndex) {
        return when (text[lastSymbolIndex]) {
            '-' -> text.replaceRange(
                lastSymbolIndex,
                lastSymbolIndex+1,
                "+"
            )

            '+' -> text.replaceRange(
                lastSymbolIndex,
                lastSymbolIndex+1,
                "-"
            )

            else -> text.replaceRange(
                lastSymbolIndex,
                lastSymbolIndex+1,
                text[lastSymbolIndex] + "-"
            )
        }
    }

    return text
}


@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CustomTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                CalculatorOutput(
                    text = "0", modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
                CalculatorInput(
                    onClick = { }, modifier = Modifier.weight(3f)
                )
            }
        }
    }
}