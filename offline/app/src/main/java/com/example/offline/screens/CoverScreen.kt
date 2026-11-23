package com.example.offline.screens
import android.R
import android.view.RoundedCorner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.function.IntConsumer
import kotlin.coroutines.Continuation

private val ink = Color(0xFF171717)
private val paper = Color(0xFFE8E5DF)
private val concrete = Color(0xFF92918D)
private val burgundy = Color(0xFF9D3034)

@Composable
fun CoverScreen(
    onContinue: (String, String) -> Unit
) {
    var lookbookName by rememberSaveable { mutableStateOf("") }
    var selectedCollection by rememberSaveable { mutableStateOf("mens") }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = paper
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 440.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "independent style journal",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = ink,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "issue 001 / 2026",
                        fontSize = 10.sp,
                        color = concrete
                    )
                } //end row
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    modifier = Modifier.size(68.dp),
                    shape = CircleShape,
                    color = ink
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Filled.Checkroom,
                            contentDescription = "Fashion icon",
                            tint = paper,
                            modifier = Modifier.size(34.dp)
                        )
                    } //end box
                } //end surface
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "OFFLINE.",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Black,
                    color = ink,
                    letterSpacing = (-1).sp
                )
                Text(
                    text = "your personal street style edit",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = concrete,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(28.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = ink,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(22.dp)) {
                        Text(
                            text = "find your",
                            fontWeight = FontWeight.Black,
                            fontSize = 30.sp,
                            color = paper,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "uniform.",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Light,
                            color = paper,
                            letterSpacing = (-1).sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "a curated lookbook, bult around your style.",
                            fontSize = 13.sp,
                            color = concrete
                        )
                    } //end column
                } //end surface
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "01 / NAME YOUR COLLECTION",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ink,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = lookbookName,
                    onValueChange = { lookbookName = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Lookbook Name") },
                    placeholder = { Text("My airport fits...") },
                    singleLine = true,
                    shape = RoundedCornerShape(6.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = burgundy,
                        unfocusedBorderColor = concrete,
                        focusedLabelColor = burgundy,
                        cursorColor = burgundy,
                        focusedTextColor = ink,
                        unfocusedTextColor = ink
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "02 / CHOOSE YOUR COLLECTION",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ink,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if(selectedCollection == "mens"){
                        Button(
                            onClick = {
                                selectedCollection = "mens"
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ink,
                                contentColor = paper
                            )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "MEN'S",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "COLLECTION",
                                    fontSize = 9.sp
                                )
                            } //end column
                        } //end button
                    } //end if
                    else {
                        OutlinedButton(
                            onClick = {
                                selectedCollection = "mens"
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(1.dp, concrete),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = ink
                            )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "MEN'S",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "COLLECTION",
                                    fontSize = 9.sp
                                )
                            } // end column
                        } // end outlinedButton
                    } //end else
                    if (selectedCollection == "womens"){
                        Button(
                            onClick = {
                                selectedCollection = "womens"
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ink,
                                contentColor = paper
                            )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "WOMEN'S",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "COLLECTION",
                                    fontSize = 9.sp
                                )
                            } //end column
                        } //end button
                    } //end if
                    else {
                        OutlinedButton(
                            onClick = {
                                selectedCollection = "womens"
                            }, //end onClick
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(1.dp, concrete),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = ink
                            )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "WOMEN'S",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "COLLECTION",
                                    fontSize = 9.sp
                                )
                            } //end column
                        } //end outlinedButton
                    } //end else
                } //end row
                Spacer(modifier = Modifier.height(28.dp))
                Button(
                    onClick = {
                        onContinue(
                            lookbookName.trim(),
                            selectedCollection
                        )
                    }, //end onClick
                    enabled = lookbookName.isNotBlank(),
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = burgundy,
                        contentColor = paper,
                        disabledContainerColor = concrete,
                        disabledContentColor = paper
                    )
                ) {
                    Text(
                        text = "build my style profile ->",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                } //end button
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "personal style isn't one size fit all.",
                    fontSize = 9.sp,
                    color = concrete,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center
                )
            } //end column
        } //end box
    } //end surface
} //end coverScreen
