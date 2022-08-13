package com.example.mealsapp.model

import com.example.mealsapp.model.api.MealsWebService
import com.example.mealsapp.model.response.MealsCategoriesResponse
import com.example.mealsapp.model.response.MealsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealsResponse>()
    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMealById(id: String): MealsResponse? {
        return cachedMeals.firstOrNull() { meal -> id == meal.id }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also {
                instance = it
            }
        }
    }

}