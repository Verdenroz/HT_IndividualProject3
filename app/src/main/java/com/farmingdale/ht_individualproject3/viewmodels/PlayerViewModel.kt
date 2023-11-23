package com.farmingdale.ht_individualproject3.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farmingdale.ht_individualproject3.database.ChildProgress
import com.farmingdale.ht_individualproject3.database.ImplParentChildRepo
import com.farmingdale.ht_individualproject3.datatypes.Directions
import com.farmingdale.ht_individualproject3.datatypes.Node
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Handles logic for players
 */
class PlayerViewModel(repo: ImplParentChildRepo, childId: Int) : ViewModel() {
    private val repo = repo
    private val childID = childId
    var playerPositionX = mutableStateOf(0)
        private set
    var playerPositionY = mutableStateOf(0)
        private set
    var currentNode: MutableState<Node?> = mutableStateOf(null)
        private set

    /**
     * Calculates how many times to move in a direction
     */
    fun calculateMovement(directions: Directions): Int {
        var count = 0
        var node = currentNode.value
        Log.d("ViewModel Node", node.toString())
        when (directions) {
            Directions.UP -> {
                while (node != null && node.hasUp) {
                    count++
                    node = node.next
                    if (node != null) {
                        updateCurrentNode(node)
                    }
                }
            }

            Directions.DOWN -> {
                while (node != null && node.hasDown) {
                    count++
                    node = node.next
                    if (node != null) {
                        updateCurrentNode(node)
                    }
                }

            }

            Directions.RIGHT -> {
                while (node != null && node.hasRight) {
                    count++
                    node = node.next
                    if (node != null) {
                        updateCurrentNode(node)
                    }
                }
            }

            Directions.LEFT -> {
                while (node != null && node.hasLeft) {
                    count++
                    node = node.next
                    if (node != null) {
                        updateCurrentNode(node)
                    }
                }
            }
        }
        Log.d("Movement", "$count")
        return count
    }

    /**
     * Adjusts player position in one direction
     * @param moveBy how many times to move in a direction
     */
    fun movePlayer(moveBy: Int, directions: Directions) {
        when (directions) {
            Directions.UP -> {
                viewModelScope.launch {
                    for (i in 0 until moveBy) {
                        playerPositionY.value++
                        Log.d("Position", "${playerPositionX.value},${playerPositionY.value}")
                        delay(750)
                    }
                }
            }

            Directions.DOWN -> {
                viewModelScope.launch {
                    for (i in 0 until moveBy) {
                        playerPositionY.value--
                        Log.d("Position", "${playerPositionX.value},${playerPositionY.value}")
                        delay(750)
                    }
                }
            }

            Directions.RIGHT -> {
                viewModelScope.launch {
                    for (i in 0 until moveBy) {
                        playerPositionX.value++
                        Log.d("Position", "${playerPositionX.value},${playerPositionY.value}")
                        delay(750)
                    }
                }
            }

            Directions.LEFT -> {
                viewModelScope.launch {
                    for (i in 0 until moveBy) {
                        playerPositionX.value--
                        Log.d("Position", "${playerPositionX.value},${playerPositionY.value}")
                        delay(750)
                    }
                }
            }
        }
    }

    fun updateCurrentNode(node: Node) {
        currentNode.value = node
    }

    fun getChildID(): Int {
        return childID
    }

    suspend fun getPoints(): Int? = withContext(Dispatchers.IO) {
        val points = repo.database.childProgressDao().getPoints(childID)
        if (points != null) points else null
    }

    /**
     * Resets position to a set (x,y)
     */
    fun restart(positionX: Int, positionY: Int) {
        playerPositionX.value = positionX
        playerPositionY.value = positionY
    }

    /**
     * Creates progress for child
     */
    fun createProgressPoint(progress: ChildProgress) {
        val progress = progress
        viewModelScope.launch(Dispatchers.IO) {
            repo.database.childProgressDao().insertItem(progress)
        }
    }

}