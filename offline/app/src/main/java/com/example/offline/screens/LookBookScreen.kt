package com.example.offline.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun LookBookScreen(
    lookbookName: String,
    collection: String,
    silhouette: Int,
    layering: Float,
    palette: String,
    onBack: () -> Unit
){
    var prioritizeAccessories by rememberSaveable { mutableStateOf(false) }
    val looks = LookRepo.getLooks(collection)
    val rankedLooks = looks.map {
        look -> look to calculateMatchScore(
            look = look,
            silhouette = silhouette,
            layering = layering,
            palette = palette,
            prioritizeAccessories = prioritizeAccessories
        )
    }.sortedByDescending { it.second }
    val collectionName = if (collection == "womens"){
        "WOMEN'S COLLECTION"
    } //end if
    else {
        "MEN'S COLLECTION"
    } //end else
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = paper
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "OFFLINE.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = ink
                )
                Text(
                    text = "ISSUE 001 / 03",
                    fontSize = 10.sp,
                    color = concrete,
                    fontWeight = FontWeight.Bold
                )
            } //end row
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = concrete)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "your personal lookbook.",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = burgundy,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "the daily edit",
                fontSize = 34.sp,
                fontWeight = FontWeight.Black,
                color = ink,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "curated for ${lookbookName.ifBlank { "you" }}.",
                fontSize = 15.sp,
                color = concrete,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = ink),
                shape = RoundedCornerShape(8.dp)
            ) {
               Column(
                   modifier = Modifier.padding(18.dp),
                   verticalArrangement = Arrangement.spacedBy(8.dp)
               ) {
                   Text(
                       text = "your style profile",
                       fontSize = 10.sp,
                       fontWeight = FontWeight.Bold,
                       color = burgundy,
                       letterSpacing = 2.sp
                   )
                   Text(
                       text = collectionName,
                       fontSize = 19.sp,
                       fontWeight = FontWeight.Bold,
                       color = paper
                   )
                   Text(
                       text = "Silhouette: $silhouette / 5",
                       fontSize = 13.sp,
                       color = paper
                   )
                   Text(
                       text = "Layering: ${layering.roundToInt()} / 5",
                       fontSize = 13.sp,
                       color = paper,
                   )
                   Text(
                       text = "Palette: ${palette.replaceFirstChar { it.uppercase() }}",
                       fontSize = 13.sp,
                       color = paper
                   )
               } //end column
            } //end card
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDCD8D0)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "prioritize statement accessories",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = ink
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (prioritizeAccessories){
                                "Give accessory-heavy outfits a higher score"
                            } //end if
                            else {
                                "Prioritize outfits with minimal accessories"
                            }, //end else
                            fontSize = 12.sp,
                            color = concrete
                        )
                    } //end column
                    Switch(
                        checked = prioritizeAccessories,
                        onCheckedChange = { prioritizeAccessories = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = paper,
                            checkedTrackColor = burgundy,
                            uncheckedThumbColor = paper,
                            uncheckedTrackColor = concrete
                        )
                    )
                } //end row
            } //end card
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "01 - your top match",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ink
                )
                Text(
                    text = "${rankedLooks.first().second}%",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color = burgundy
                )
            } //end row
            Spacer(modifier = Modifier.height(12.dp))
            rankedLooks.forEachIndexed { index, result ->
                LookCard(
                    rank = index + 1,
                    look = result.first,
                    score = result.second
                )
                Spacer(modifier = Modifier.height(16.dp))
            } //end forEachIndexed
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = concrete)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "That's your edit",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = ink
            )
            Text(
                text = "five looks. one personal style direction",
                fontSize = 12.sp,
                color = concrete,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = burgundy,
                    contentColor = paper
                ),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "edit my style profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )
            } //end button
            Spacer(modifier = Modifier.height(12.dp))
        } //end column
    } //end surface
} //end lookbookScreen

@Composable
private fun LookCard(
    rank: Int,
    look: Look,
    score: Int
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFDCD8D0)),
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
                    text = "0$rank - ${if (rank == 1) "TOP MATCH" else "Look $rank"}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = concrete
                )
                Text(
                    text = "$score% MATCH",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = burgundy
                )
            } //end row
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = look.imageResId),
                contentDescription = look.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = look.name.uppercase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = ink
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = look.description,
                fontSize = 13.sp,
                color = ink
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = concrete)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "SILHOUETTE ${look.silhouette} / LAYERING ${look.layering} / ${look.palette.uppercase()}",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = concrete
            )
        } //end column
    } //end card
} //end lookCard

private fun calculateMatchScore(
    look: Look,
    silhouette: Int,
    layering: Float,
    palette: String,
    prioritizeAccessories: Boolean
): Int {
    val silhouetteScore = (100f - abs(silhouette - look.silhouette) * 25f).coerceIn(0f, 100f)
    val layeringScore = (100f - abs(layering - look.layering) * 25f).coerceIn(0f, 100f)
    val paletteScore = if (palette.equals(look.palette, ignoreCase = true)){
        100f
    } else {
        35f
    }
    val accessoryScore = if (prioritizeAccessories == look.hasStatementAccessories){
        100f
    } else {
        35f
    }
    val finalScore = (silhouetteScore * 0.30f) + (layeringScore * 0.25f) + (paletteScore * 0.25f) + (accessoryScore * 0.20f)
    return finalScore.roundToInt()
} //end calculateMatchScore