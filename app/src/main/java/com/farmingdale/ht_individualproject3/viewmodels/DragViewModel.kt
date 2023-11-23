package com.farmingdale.ht_individualproject3.viewmodels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.farmingdale.ht_individualproject3.datatypes.Arrow
import com.farmingdale.ht_individualproject3.datatypes.Directions

/**
 * Handles login for drag and drop
 */
class DragViewModel: ViewModel() {

    var isDragging by mutableStateOf(false)
        private set
    var items by mutableStateOf(listOf(
        Arrow(Directions.UP, Icons.Filled.KeyboardArrowUp),
        Arrow(Directions.DOWN, Icons.Filled.KeyboardArrowDown),
        Arrow(Directions.LEFT, Icons.Filled.KeyboardArrowLeft),
        Arrow(Directions.RIGHT, Icons.Filled.KeyboardArrowRight)
    ))
        private set
    var addedItems by mutableStateOf(arrayOfNulls<Arrow>(3))
        private set

    fun startDragging(){
        isDragging = true
    }

    fun stopDragging(){
        isDragging = false
    }

    fun updateAddedItems(array: Array<Arrow?>){
        addedItems = array
        Log.d("Update", addedItems.joinToString())
    }

    fun restart(){
        addedItems = arrayOfNulls<Arrow>(3)
    }
}