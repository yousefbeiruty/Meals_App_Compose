package com.example.mealsapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

import coil.transform.CircleCropTransformation
import com.example.mealsapp.model.response.MealsResponse
import com.google.accompanist.coil.rememberCoilPainter
import java.lang.Float.min
import kotlin.math.max

@Composable
fun MealsDetailsScreen(meal: MealsResponse?) {
    val scrollState = rememberLazyListState ()
    val offSet = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offSet))
    Surface(color = MaterialTheme.colors.background) {
        Column() {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = Color.Green)
                    ) {
                        Image(
                            painter = rememberCoilPainter(request = meal?.imageUrl,
                                requestBuilder = { transformations(CircleCropTransformation()) }
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(size)
                        )
                    }
                    Text(
                        text = meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyListContent = (0..100).map { it.toString() }
            LazyColumn( state = scrollState) {
                items(dummyListContent) {
                    Text(text = it, modifier = Modifier.padding(8.dp ))
                }
            }
        }
    }


}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Norman(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 4.dp)
}