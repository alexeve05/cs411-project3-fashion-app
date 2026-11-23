package com.example.offline.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ink = Color(0xFF171717)
private val paper = Color(0xFFE8E5DF)
private val concrete = Color(0xFF92918D)
private val burgundy = Color(0xFF9D3034)

@Composable
fun StyleProfileScreen(
    lookbookName: String,
    collection: String,
    onContinue: (Int, Float, String) -> Unit,
    onBack: () -> Unit
) {
    var selectedSilhouette by rememberSaveable { mutableIntStateOf(3) }
    var selectedLayering by rememberSaveable { mutableFloatStateOf(3f) }
    var selectedPalette by rememberSaveable { mutableStateOf("neutrals") }
    val silhouetteOptions = listOf(
        1 to "slim/fitted",
        2 to "slightly fitted",
        3 to "relaxed",
        4 to "oversized",
        5 to "extra oversized"
    )
    val paletteOptions = listOf(
        "neutrals",
        "earth tones",
        "cool tones",
        "bold colors"
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = paper
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "OFFLINE. / STYLE PROFILE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = ink,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "02 - 03",
                    fontSize = 10.sp,
                    color = concrete
                )
            } //end row
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "define your",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color = ink
            )
            Text(
                text = "silhouette.",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 36.sp,
                fontWeight = FontWeight.Light,
                color = ink
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "let's get a feel for your personal style, $lookbookName.",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 13.sp,
                color = concrete
            )
            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader("01 / FIT PREFERENCE")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "How do you like your clothes to fit?",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = ink
            )
            Spacer(modifier = Modifier.height(8.dp))
            silhouetteOptions.forEach { (value, label) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = selectedSilhouette == value,
                        onClick = {
                            selectedSilhouette = value
                        }, //end onClick
                        colors = RadioButtonDefaults.colors(
                            selectedColor = burgundy,
                            unselectedColor = concrete
                        )
                    )
                    Text(
                        text = label,
                        fontSize = 14.sp,
                        color = ink
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = value.toString().padStart(2, '0'),
                        fontSize = 11.sp,
                        color = concrete
                    )
                } //end row
            } //end silhouetteOptions
            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader("02 / LAYERING")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "How much layering do you prefer?",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = ink
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "minimal",
                    fontSize = 10.sp,
                    color = concrete,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${selectedLayering.toInt()} / 5",
                    fontSize = 12.sp,
                    color = burgundy,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "heavy",
                    fontSize = 10.sp,
                    color = concrete,
                    fontWeight = FontWeight.Bold
                )
            } //end row
            Slider(
                value = selectedLayering,
                onValueChange = { selectedLayering = it },
                valueRange = 1f..5f,
                steps = 3,
                colors = SliderDefaults.colors(
                    thumbColor = burgundy,
                    activeTrackColor = burgundy,
                    inactiveTrackColor = concrete
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader("03 / COLOR PALETTE")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Which colors do you gravitate toward?",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = ink
            )
            Spacer(modifier = Modifier.height(12.dp))
            paletteOptions.chunked(2).forEach { rowOptions ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowOptions.forEach{ palette ->
                        val isSelected = selectedPalette == palette
                        if (isSelected){
                            Button(
                                onClick = { selectedPalette = palette },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = ink,
                                    contentColor = paper
                                )
                            ) {
                                Text(
                                    text = palette.replaceFirstChar { it.uppercase() },
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            } //end button
                        } //end if
                        else {
                            OutlinedButton(
                                onClick = { selectedPalette = palette },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, concrete),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = ink
                                )
                            ) {
                                Text(
                                    text = palette.replaceFirstChar { it.uppercase() },
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            } //end outlinedButton
                        } //end else
                    } //end rowOptions
                } //end row
            } //end paletteOptions
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = {
                    onContinue(
                        selectedSilhouette,
                        selectedLayering,
                        selectedPalette
                    )
                },
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = burgundy,
                    contentColor = paper
                )
            ) {
                Text(
                    text = "generate my lookbook ->",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            } //end button
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, concrete)
            ) {
                Text(
                    text = "<- back to cover",
                    color = ink,
                    fontSize = 11.sp
                )
            } //end outlinedButton
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "your style. your rules.",
                fontSize = 9.sp,
                color = concrete,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )
        } //end column
    } //end surface
} //end styleProfileScreen

@Composable
private fun SectionHeader(title: String){
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = burgundy,
        letterSpacing = 1.sp
    )
} //end sectionHeader