package com.farmingdale.ht_individualproject3.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.farmingdale.ht_individualproject3.datatypes.Arrow
import com.farmingdale.ht_individualproject3.viewmodels.DragViewModel

/**
 * Creates the top bar for all game stages with the drag and drop items
 */
@Composable
fun ArrowTopBar() {
    val dragViewModel: DragViewModel = viewModel<DragViewModel>()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val addedArrows = remember { mutableStateOf(arrayOfNulls<Arrow>(3)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            DropItem<Arrow>(
                modifier = Modifier
                    .size(Dp(screenWidth / 9f))
            ) { isInBound, arrow ->
                if (arrow != null) {
                    LaunchedEffect(key1 = arrow) {
                        addedArrows.value[0] = arrow
                        dragViewModel.updateAddedItems(addedArrows.value)
                    }
                }
                if (isInBound) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(
                                Color.LightGray.copy(0.5f),
                                RoundedCornerShape(15.dp)
                            )
                    )
                } else {
                    if (dragViewModel.addedItems[0] != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(
                                    Color.White.copy(0.6f),
                                    RoundedCornerShape(15.dp)
                                )
                        ) {
                            Icon(
                                dragViewModel.addedItems[0]!!.img,
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(
                                    Color.DarkGray.copy(0.6f),
                                    RoundedCornerShape(15.dp)
                                )
                        )
                    }

                }
            }
            DropItem<Arrow>(
                modifier = Modifier
                    .size(Dp(screenWidth / 9f))
            ) { isInBound, arrow ->
                if (arrow != null) {
                    LaunchedEffect(key1 = arrow) {
                        addedArrows.value[1] = arrow
                        dragViewModel.updateAddedItems(addedArrows.value)
                    }
                }
                if (isInBound) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(
                                Color.LightGray.copy(0.5f),
                                RoundedCornerShape(15.dp)
                            )
                    )
                } else {
                    if (dragViewModel.addedItems[1] != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(
                                    Color.White.copy(0.6f),
                                    RoundedCornerShape(15.dp)
                                )
                        ) {
                            Icon(
                                dragViewModel.addedItems[1]!!.img,
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(
                                    Color.DarkGray.copy(0.6f),
                                    RoundedCornerShape(15.dp)
                                )
                        )
                    }

                }
            }
            DropItem<Arrow>(
                modifier = Modifier
                    .size(Dp(screenWidth / 9f))
            ) { isInBound, arrow ->
                if (arrow != null) {
                    LaunchedEffect(key1 = arrow) {
                        addedArrows.value[2] = arrow
                        dragViewModel.updateAddedItems(addedArrows.value)
                    }
                }
                if (isInBound) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(
                                Color.LightGray.copy(0.5f),
                                RoundedCornerShape(15.dp)
                            )
                    )
                } else {
                    if (dragViewModel.addedItems[2] != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(
                                    Color.White.copy(0.6f),
                                    RoundedCornerShape(15.dp)
                                )
                        ) {
                            Icon(
                                dragViewModel.addedItems[2]!!.img,
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(
                                    Color.DarkGray.copy(0.6f),
                                    RoundedCornerShape(15.dp)
                                )
                        )
                    }

                }
            }

        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End)) {
            dragViewModel.items.forEach { arrow ->
                DragTarget(
                    dataToDrop = arrow,
                    viewModel = dragViewModel
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.LightGray),
                    ) {
                        Icon(
                            arrow.img,
                            contentDescription = "",
                            modifier = Modifier.size(Dp(screenWidth / 9f))
                        )
                    }
                }
            }
        }
    }

    
}

@Preview
@Composable
fun PreviewArrowTopBar() {
    ArrowTopBar()
}