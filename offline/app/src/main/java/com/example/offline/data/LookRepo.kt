package com.example.offline.data

import com.example.offline.R

object LookRepo{
    private val mensLooks = listOf(
        Look(
            id = "mens_look_1",
            name = "After Hours",
            description = "dramatic dark layering with a long coat and wide leg trousers",
            imageResId = R.drawable.mens_outfit_1,
            silhouette = 5,
            layering = 5,
            palette = "neutrals",
            hasStatementAccessories = true
        ), //end look 1
        Look(
            id = "mens_look_2",
            name = "Off-Duty",
            description = "a relaxed leather jacket paired with loose denim",
            imageResId = R.drawable.mens_outfit_2,
            silhouette = 3,
            layering = 2,
            palette = "earth tones",
            hasStatementAccessories = false
        ), //end look2
        Look(
            id = "mens_outfit_3",
            name = "clean slate",
            description = "an easy monochromatic look with oversized proportions",
            imageResId = R.drawable.mens_outfit_3,
            silhouette = 3,
            layering = 4,
            palette = "neutrals",
            hasStatementAccessories = true
        ), //end look3
        Look(
            id = "mens_outfit_4",
            name = "city transit",
            description = "an oversized hoodie and relaxed cargos for everyday streetwear",
            imageResId = R.drawable.mens_outfit_4,
            silhouette = 5,
            layering = 2,
            palette = "neutrals",
            hasStatementAccessories = true
        ), //end look4
        Look(
            id = "mens_outfit_5",
            name = "utility mode",
            description = "a casual long sleeve and camo trousers with a streetwear edge",
            imageResId = R.drawable.mens_outfit_5,
            silhouette = 2,
            layering = 1,
            palette = "earth tones",
            hasStatementAccessories = true
        ) //end look5
    ) //end menslooks

    private val womensLooks = listOf(
        Look(
            id = "womens_outfit_1",
            name = "checked out",
            description = "oversized outwear, plaid layering, and relaxed trousers",
            imageResId = R.drawable.womens_outfit_1,
            silhouette = 5,
            layering = 4,
            palette = "neutrals",
            hasStatementAccessories = true
        ), //end look 1
        Look(
            id = "womens_outfit_2",
            name = "soft structure",
            description = "a clean, relaxed combination of a light blouse and wide leg pants",
            imageResId = R.drawable.womens_outfit_2,
            silhouette = 1,
            layering = 1,
            palette = "neutrals",
            hasStatementAccessories = false
        ), //end look2
        Look(
            id = "womens_outfit_3",
            name = "blue hour",
            description = "a fitted top balanced with loose denim and cool-toned styling",
            imageResId = R.drawable.womens_outfit_3,
            silhouette = 2,
            layering = 1,
            palette = "cool tones",
            hasStatementAccessories = false
        ), //end look3
        Look(
            id = "womens_outfit_4",
            name = "downtown uniform",
            description = "layered outwear, a short skirt, tights, and statement boots",
            imageResId = R.drawable.womens_outfit_4,
            silhouette = 2,
            layering = 5,
            palette = "earth tones",
            hasStatementAccessories = true
        ), //end look4
        Look(
            id = "womens_outfit_5",
            name = "off-duty minimal",
            description = "a minimal black outfit with a fitted top and relaxed shorts",
            imageResId = R.drawable.womens_outfit_5,
            silhouette = 4,
            layering = 2,
            palette = "neutrals",
            hasStatementAccessories = true
        ) //end look 5
    ) //end womenslooks

    fun getLooks(collection: String): List<Look>{
        return when (collection.lowercase()){
            "womens" -> womensLooks
            else -> mensLooks
        } //end return
    } //end getlooks
} //end lookrepo