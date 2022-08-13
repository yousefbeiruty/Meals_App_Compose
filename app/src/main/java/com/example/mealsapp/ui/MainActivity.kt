package com.example.mealsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealsapp.ui.details.MealDetailsViewModel
import com.example.mealsapp.ui.details.MealsDetailsScreen
import com.example.mealsapp.ui.meals.MealsCategoriesScreen
import com.example.mealsapp.ui.theme.MealsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppTheme {
                // A surface container using the 'background' color from the theme
                FoodzApp()
            }
        }
    }
}

@Composable
fun FoodzApp() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "meals_list") {
        composable(route = "meals_list") {
            MealsCategoriesScreen {
                navigationController.navigate(route = "details/$it")
            }
        }
        composable(
            route = "details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel: MealDetailsViewModel = viewModel()
            MealsDetailsScreen(meal =viewModel.mealState.value)
        }
    }
}

