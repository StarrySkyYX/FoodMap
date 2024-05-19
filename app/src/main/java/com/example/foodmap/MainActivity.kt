package com.example.foodmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.foodmap.ui.FoodMapApp
import com.example.foodmap.ui.theme.FoodMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodMapTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "home") {
                        composable("home") { FoodMapApp(navController) }
                        composable(
                            "detail/{nameId}/{addressId}/{imageId}/{menuId}",
                            arguments = listOf(
                                navArgument("nameId") { type = NavType.IntType },
                                navArgument("addressId") { type = NavType.IntType },
                                navArgument("imageId") { type = NavType.IntType },
                                navArgument("menuId") { type = NavType.IntType },
                            )
                        ) { backStackEntry ->
                            val nameId = backStackEntry.arguments?.getInt("nameId") ?: 0
                            val addressId = backStackEntry.arguments?.getInt("addressId") ?: 0
                            val imageId = backStackEntry.arguments?.getInt("imageId") ?: 0
                            val menuId = backStackEntry.arguments?.getInt("menuId") ?: 0
                            StoreApp(
                                nameId = nameId,
                                addressId = addressId,
                                imageId = imageId,
                                menuId = menuId,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
