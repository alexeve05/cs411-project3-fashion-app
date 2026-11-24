package com.example.offline.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.offline.data.Look
import com.example.offline.data.LookRepo
import kotlin.math.abs
import kotlin.math.roundToInt

private val ink = Color(0xFF171717)
private val paper = Color(0xFFE8E5DF)
private val concrete = Color(0xFF92918D)
private val burgundy = Color(0xFF9D3034)
private val creme = Color(0xFFDCD8D0)

@Composable
fun LookBookScreen(
    lookbookName: String,
    collection: String,
    silhouette: Int,
    layering: Float,
    palette: String,
    onBack: () -> Unit,
    onBackToCover: () -> Unit
) {
    var prioritizeAccessories by rememberSaveable { mutableStateOf(false) }
    var accessoryPreferenceApplied by rememberSaveable { mutableStateOf(false) }
    val looks = LookRepo.getLooks(collection)
    val rankedLooks = looks.map { look ->
        look to calculateMatchScore(
            look = look,
            silhouette = silhouette,
            layering = layering,
            palette = palette,
            prioritizeAccessories = prioritizeAccessories
        )
    }.sortedByDescending { it.second }
    val editType = if (collection == "womens") {
        "WOMEN'S EDIT"
    } else {
        "MEN'S EDIT"
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = paper
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onBack,
                    border = BorderStroke(1.dp, concrete)
                ) {
                    Text(
                        text = "<- PROFILE",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = burgundy
                    )
                } //end outlinedButton
                OutlinedButton(
                    onClick = onBackToCover,
                    border = BorderStroke(1.dp, concrete)
                ) {
                    Text(
                        text = "START OVER",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = burgundy
                    )
                } //end outlinedButton
            } //end row
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "OFFLINE.",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = ink
                )
                Text(
                    text = "ISSUE 001 / 26",
                    fontSize = 16.sp,
                    color = concrete,
                    fontWeight = FontWeight.Bold
                )
            } //end row
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider(Modifier, DividerDefaults.Thickness, color = concrete)
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "YOUR PERSONAL STYLE EDIT",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = burgundy,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = lookbookName.ifBlank { "YOUR EDIT" }.uppercase(),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 35.sp,
                fontWeight = FontWeight.Black,
                color = ink,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "$editType  /  5 CURATED LOOKS",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = concrete,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = ink
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "YOUR STYLE PROFILE",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = burgundy,
                        letterSpacing = 1.5.sp
                    )
                    Text(
                        text = "Silhouette: $silhouette / 5",
                        fontSize = 14.sp,
                        color = paper
                    )
                    Text(
                        text = "Layering: ${layering.roundToInt()} / 5",
                        fontSize = 14.sp,
                        color = paper
                    )
                    Text(
                        text = "Palette: ${palette.replaceFirstChar { it.uppercase() }}",
                        fontSize = 14.sp,
                        color = paper
                    )
                } //end column
            } //end card
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = creme
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "CHOOSE YOUR ACCESSORY PREFERENCE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ink
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "This changes how your outfits are ranked.",
                        fontSize = 12.sp,
                        color = concrete,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = if (prioritizeAccessories) {
                                    "STATEMENT ACCESSORIES"
                                } else {
                                    "MINIMAL ACCESSORIES"
                                },
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = burgundy
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (prioritizeAccessories) {
                                    "Prioritize outfits with noticeable accessories."
                                } else {
                                    "Prioritize outfits without statement accessories."
                                },
                                fontSize = 12.sp,
                                color = ink
                            )
                        } //end column
                        Switch(
                            checked = prioritizeAccessories,
                            onCheckedChange = {
                                prioritizeAccessories = it
                                accessoryPreferenceApplied = false
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = paper,
                                checkedTrackColor = burgundy,
                                uncheckedThumbColor = paper,
                                uncheckedTrackColor = concrete
                            )
                        )
                    } //end row
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = {
                            accessoryPreferenceApplied = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ink,
                            contentColor = paper
                        )
                    ) {
                        Text(
                            text = "APPLY PREFERENCE",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } //end button
                } //end column
            } //end card
            Spacer(modifier = Modifier.height(24.dp))
            if (accessoryPreferenceApplied) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "YOUR MATCHES",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ink
                    )
                    Text(
                        text = "${rankedLooks.firstOrNull()?.second ?: 0}% TOP MATCH",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color = burgundy
                    )
                } //end row
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Matches are ranked by how closely each outfit fits your preferences.",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 12.sp,
                    color = concrete,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(16.dp))
                rankedLooks.forEachIndexed { index, result ->
                    LookCard(
                        rank = index + 1,
                        look = result.first,
                        score = result.second,
                        selectedSilhouette = silhouette,
                        selectedLayering = layering,
                        selectedPalette = palette,
                        prioritizeAccessories = prioritizeAccessories
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                } //end forEachIndexed
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = {
                        accessoryPreferenceApplied = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, concrete)
                ) {
                    Text(
                        text = "CHANGE ACCESSORY PREFERENCE",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ink
                    )
                } //end outlinedButton
            } else {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your outfit recommendations will appear here once you apply your accessory preference.",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 13.sp,
                    color = concrete,
                    textAlign = TextAlign.Center
                )
            } //end else
        } //end column
    } //end surface
} //end lookbookScreen
@Composable
private fun LookCard(
    rank: Int,
    look: Look,
    score: Int,
    selectedSilhouette: Int,
    selectedLayering: Float,
    selectedPalette: String,
    prioritizeAccessories: Boolean
) {
    val silhouetteMatches = selectedSilhouette == look.silhouette
    val layeringMatches = selectedLayering.roundToInt() == look.layering
    val paletteMatches = selectedPalette.equals(
        look.palette,
        ignoreCase = true
    )
    val accessoriesMatch =
        prioritizeAccessories == look.hasStatementAccessories
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = creme
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "0$rank - ${if (rank == 1) "TOP MATCH" else "LOOK $rank"}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = concrete
                )
                Text(
                    text = "$score% MATCH",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = burgundy
                )
            } //end row
            Spacer(modifier = Modifier.height(12.dp))
            Image(
                painter = painterResource(id = look.imageResId),
                contentDescription = look.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(5.dp))
                    .background(ink)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = look.name.uppercase(),
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = ink
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = look.description,
                fontSize = 14.sp,
                color = ink,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(Modifier, DividerDefaults.Thickness, color = concrete)
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "WHY THIS LOOK MATCHES",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = concrete,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            MatchAttribute(
                label = "SILHOUETTE",
                value = look.silhouette.toString(),
                matches = silhouetteMatches
            )
            MatchAttribute(
                label = "LAYERING",
                value = look.layering.toString(),
                matches = layeringMatches
            )
            MatchAttribute(
                label = "PALETTE",
                value = look.palette.uppercase(),
                matches = paletteMatches
            )
            MatchAttribute(
                label = "ACCESSORIES",
                value = if (look.hasStatementAccessories) {
                    "STATEMENT"
                } else {
                    "MINIMAL"
                },
                matches = accessoriesMatch
            )
        } //end column
    } //end card
} //end lookCard
@Composable
private fun MatchAttribute(
    label: String,
    value: String,
    matches: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = if (matches) burgundy else concrete,
            fontWeight = if (matches) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            textDecoration = if (matches) {
                TextDecoration.Underline
            } else {
                TextDecoration.None
            }
        )
        Text(
            text = if (matches) "$value" else value,
            fontSize = 12.sp,
            color = if (matches) burgundy else ink,
            fontWeight = if (matches) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )
    } //end row
} //end matchAttribute
private fun calculateMatchScore(
    look: Look,
    silhouette: Int,
    layering: Float,
    palette: String,
    prioritizeAccessories: Boolean
): Int {
    val silhouetteScore =
        (100f - abs(silhouette - look.silhouette) * 25f)
            .coerceIn(0f, 100f)
    val layeringScore =
        (100f - abs(layering - look.layering) * 25f)
            .coerceIn(0f, 100f)
    val paletteScore = if (
        palette.equals(look.palette, ignoreCase = true)
    ) {
        100f
    } else {
        35f
    }
    val accessoryScore = if (
        prioritizeAccessories == look.hasStatementAccessories
    ) {
        100f
    } else {
        35f
    }
    val finalScore =
        (silhouetteScore * 0.30f) +
                (layeringScore * 0.25f) +
                (paletteScore * 0.25f) +
                (accessoryScore * 0.20f)
    return finalScore.roundToInt()
} //end calculateMatchScore