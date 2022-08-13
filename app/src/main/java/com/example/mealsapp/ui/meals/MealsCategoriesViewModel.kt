package com.example.mealsapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.model.MealsRepository
import com.example.mealsapp.model.response.MealsCategoriesResponse
import com.example.mealsapp.model.response.MealsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    init {

        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }
    val mealsState: MutableState<List<MealsResponse>> = mutableStateOf(emptyList<MealsResponse>())

    private suspend fun getMeals(): List<MealsResponse> {
        return repository.getMeals().categories
    }
}