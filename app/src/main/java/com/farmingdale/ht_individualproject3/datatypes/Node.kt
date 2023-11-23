package com.farmingdale.ht_individualproject3.datatypes

/**
 * Holds data for each node in a game
 * Inspired by LinkedLists
 */
data class Node(
    val hasRight: Boolean = false,
    val hasLeft: Boolean = false,
    val hasUp: Boolean = false,
    val hasDown: Boolean = false,
    val position: Pair<Int, Int>,
    var next: Node? = null
)
