package com.example.offline.nav

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.offline.screens.CoverScreen
import com.example.offline.screens.StyleProfileScreen
import com.example.offline.screens.LookBookScreen

object AppRoutes {
    const val Cover = "cover"
    const val Profile = "profile/{lookbookName}/{collection}"
    const val Lookbook = "lookbook/{lookbookName}/{collection}/{silhouette}/{layering}/{palette}"

    fun profile(lookbookName: String, collection: String): String {
        return "profile/${Uri.encode(lookbookName)}/${Uri.encode(collection)}"
    }
    fun lookbook(lookbookName: String, collection: String, silhouette: Int, layering: Float, palette: String): String{
        return "lookbook/${Uri.encode(lookbookName)}/${Uri.encode(collection)}/$silhouette/$layering/${Uri.encode(palette)}"

    }
}

@Composable
fun AppNav(){
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = AppRoutes.Cover
    ) {
        //screen 1
        composable(
            AppRoutes.Cover
        )
        {
            CoverScreen(
                onContinue = {
                lookbookName, collection -> navController.navigate(
                    AppRoutes.profile(
                        lookbookName, collection
                        )
                    )
                }
            )
        }
        //screen 2
        composable(
            route = AppRoutes.Profile, arguments = listOf(
                navArgument("lookbookName"){
                    type = NavType.StringType
                },
                navArgument("collection"){
                    type = NavType.StringType
                }
            )
        ) {
            backStackEntry ->
            val lookbookName = backStackEntry.arguments?.getString("lookbookName") ?: ""
            val collection = backStackEntry.arguments?.getString("collection") ?: "mens"
            StyleProfileScreen(
                lookbookName = lookbookName,
                collection = collection,
                onContinue = {
                    silhouette, layering, palette ->
                    navController.navigate(AppRoutes.lookbook(lookbookName, collection, silhouette, layering, palette)
                    )
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // screen 3
        composable(
            route = AppRoutes.Lookbook, arguments = listOf(
                navArgument("lookbookName"){
                    type = NavType.StringType
                },
                navArgument("collection"){
                    type = NavType.StringType
                },
                navArgument("silhouette"){
                    type = NavType.IntType
                },
                navArgument("layering"){
                    type = NavType.FloatType
                },
                navArgument("palette"){
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val lookbookName = backStackEntry.arguments?.getString("lookbookName") ?: ""
            val collection = backStackEntry.arguments?.getString("collection") ?: "mens"
            val silhouette = backStackEntry.arguments?.getInt("silhouette") ?: 3
            val layering = backStackEntry.arguments?.getFloat("layering") ?: 3f
            val palette = backStackEntry.arguments?.getString("palette") ?: "Neutrals"
            LookBookScreen(
                lookbookName = lookbookName,
                collection = collection,
                silhouette = silhouette,
                layering = layering,
                palette = palette,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}