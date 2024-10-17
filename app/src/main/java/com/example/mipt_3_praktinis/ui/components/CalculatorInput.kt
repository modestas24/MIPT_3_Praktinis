package com.example.mipt_3_praktinis.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mipt_3_praktinis.ui.theme.CustomTheme

@Composable
fun CustomButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .border(2.dp, MaterialTheme.colorScheme.inversePrimary, RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()

    ) {
        Text(
            text,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ColumnScope.CustomColumnButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    weight: Float = 1f
) {
    CustomButton(
        text,
        onClick,
        modifier = modifier.weight(weight)
    )
}

@Composable
fun RowScope.CustomRowButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    weight: Float = 1f
) {
    CustomButton(
        text,
        onClick,
        modifier = modifier.weight(weight)
    )
}

@Composable
fun CalculatorInput(onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(4f)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(Alignment.Center)
            ) {
                CustomRowButton("MC", { onClick("MC") })
                CustomRowButton("MR", { onClick("MR") })
                CustomRowButton("MS", { onClick("MS") })
                CustomRowButton("M+", { onClick("M+") })
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomRowButton("<-", { onClick("<-") })
                CustomRowButton("CE", { onClick("CE") })
                CustomRowButton("C", { onClick("C") })
                CustomRowButton("±", { onClick("±") })
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomRowButton("7", { onClick("7") })
                CustomRowButton("8", { onClick("8") })
                CustomRowButton("9", { onClick("9") })
                CustomRowButton("/", { onClick("/") })
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomRowButton("4", { onClick("4") })
                CustomRowButton("5", { onClick("5") })
                CustomRowButton("6", { onClick("6") })
                CustomRowButton("*", { onClick("*") })
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomRowButton("1", { onClick("1") })
                CustomRowButton("2", { onClick("2") })
                CustomRowButton("3", { onClick("3") })
                CustomRowButton("-", { onClick("-") })
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomRowButton("0", { onClick("0") }, weight = 2f)
                CustomRowButton(".", { onClick(".") })
                CustomRowButton("+", { onClick("+") })
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            CustomColumnButton("M-", { onClick("M-") })
            CustomColumnButton("√", { onClick("√") })
            CustomColumnButton("%", { onClick("%") })
            CustomColumnButton("1/x", { onClick("1/x") })
            CustomColumnButton("=", { onClick("=") }, weight = 2f)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorInputPreview() {
    CustomTheme { CalculatorInput({}) }
}