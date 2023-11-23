package com.farmingdale.ht_individualproject3.game.hard.level

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.compose.md_theme_light_primary
import com.farmingdale.ht_individualproject3.R
import com.farmingdale.ht_individualproject3.database.ChildProgress
import com.farmingdale.ht_individualproject3.datatypes.Arrow
import com.farmingdale.ht_individualproject3.datatypes.Node
import com.farmingdale.ht_individualproject3.game.ArrowTopBar
import com.farmingdale.ht_individualproject3.game.DraggableScreen
import com.farmingdale.ht_individualproject3.viewmodels.DragViewModel
import com.farmingdale.ht_individualproject3.viewmodels.PlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * For Hard Stage One
 */
@Composable
fun HardStageOne(navController: NavHostController, playerViewModel: PlayerViewModel) {
    val dragViewModel = viewModel<DragViewModel>()
    val playPositionX by remember { mutableStateOf(playerViewModel.playerPositionX) }
    val playPositionY by remember { mutableStateOf(playerViewModel.playerPositionY) }
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var nodes by remember { mutableStateOf(Node(position = Pair(0, 0))) }

    Scaffold(
        topBar = {
            DraggableScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(.8f))
            ) {
                ArrowTopBar()
            }
        }
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black.copy(.8f))
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
            ) {
                nodes = Node(
                    position = Pair(0, 5), hasDown = true, next =
                    Node(
                        position = Pair(0, 4), hasDown = true, hasUp = true, next =
                        Node(
                            position = Pair(0, 3), hasDown = true, hasUp = true, next =
                            Node(
                                position = Pair(0, 2), hasDown = true, hasUp = true, next =
                                Node(
                                    position = Pair(0, 1), hasDown = true, hasUp = true, next =
                                    Node(
                                        position = Pair(0, 0), hasUp = true, hasRight = true, next =
                                        Node(
                                            position = Pair(5, 0),
                                            hasRight = true,
                                            next =
                                            Node(
                                                position = Pair(4, 0),
                                                hasRight = true,
                                                hasLeft = true,
                                                next =
                                                Node(
                                                    position = Pair(3, 0),
                                                    hasRight = true,
                                                    hasLeft = true,
                                                    next =
                                                    Node(
                                                        position = Pair(2, 0),
                                                        hasRight = true,
                                                        hasLeft = true,
                                                        next =
                                                        Node(
                                                            position = Pair(1, 0),
                                                            hasRight = true,
                                                            hasLeft = true,
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
                for (i in 4 downTo 0) {
                    if (playPositionX.value == 0 && playPositionY.value == i + 1) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .border(
                                    1.dp,
                                    color = Color.Red,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(
                                    Color.LightGray.copy(0.5f),
                                    RoundedCornerShape(15.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.heart_icon),
                                contentDescription = stringResource(id = R.string.play)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
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
                    }
                }
                Row {
                    for (i in 0 until 7) {
                        if (playPositionX.value == i && playPositionY.value == 0) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .border(
                                        1.dp,
                                        color = Color.Red,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .background(
                                        Color.LightGray.copy(0.5f),
                                        RoundedCornerShape(15.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.heart_icon),
                                    contentDescription = stringResource(id = R.string.play)
                                )
                            }
                        } else if (i == 6) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .border(
                                        1.dp,
                                        color = Color.Red,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .background(
                                        Color.LightGray.copy(0.5f),
                                        RoundedCornerShape(15.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.flag_icon),
                                    contentDescription = stringResource(id = R.string.goal)
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
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
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.align(Alignment.TopStart),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = {
                        val droppedItems = dragViewModel.addedItems
                        if (droppedItems.isNotEmpty()) {
                            val arrows = mutableListOf<Arrow>()
                            for (i in droppedItems) {
                                if (i != null) {
                                    arrows.add(i)
                                }
                            }
                            Log.d("Arrows", arrows.toString())
                            scope.launch {
                                playerViewModel.updateCurrentNode(nodes)
                                for (i in 0 until arrows.size) {
                                    val moveBy =
                                        playerViewModel.calculateMovement(arrows[i].directions)
                                    if (moveBy > 0) {
                                        playerViewModel.movePlayer(moveBy, arrows[i].directions)
                                        delay((750 * moveBy).toLong())
                                    }
                                }
                                showDialog = true
                            }
                        }
                    },
                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = stringResource(id = R.string.play),
                        modifier = Modifier
                            .size(100.dp),
                        tint = Color.Green
                    )
                }
                Text(
                    text = stringResource(id = R.string.play),
                    color = md_theme_light_primary
                )
            }
        }
    }
    //notifies user of results
    if (showDialog) {
        Log.d("Final Position", "${playPositionX.value},${playPositionY.value}")
        Dialog(onDismissRequest = { showDialog = false }) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(5f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (playPositionX.value == 6 && playPositionY.value == 0) {
                        val pointstoAdd = 3000
                        Text(
                            text = stringResource(id = R.string.winner),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = stringResource(id = R.string.earned),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = "$pointstoAdd",
                            style = MaterialTheme.typography.titleLarge
                        )
                        //creates progress point
                        val date = Calendar.getInstance()
                        val months = date.get(Calendar.MONTH) + 1
                        val days = date.get(Calendar.DAY_OF_MONTH).toString()
                        val year = date.get(Calendar.YEAR).toString()
                        LaunchedEffect(key1 = Unit) {
                            val points = playerViewModel.getPoints()
                            Log.d("Points", points.toString())
                            if (points != null) {
                                val progress = ChildProgress(
                                    childNumber = playerViewModel.getChildID(),
                                    points = points + pointstoAdd,
                                    month = months.toString(),
                                    day = days,
                                    year = year
                                )
                                Log.d("Progress Added", progress.toString())
                                playerViewModel.createProgressPoint(progress)
                            } else {
                                val progress = ChildProgress(
                                    childNumber = playerViewModel.getChildID(),
                                    points = pointstoAdd,
                                    month = months.toString(),
                                    day = days,
                                    year = year
                                )
                                Log.d("Progress", progress.toString())
                                playerViewModel.createProgressPoint(progress)
                            }
                        }
                    } else {
                        Text(
                            text = stringResource(id = R.string.loser),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    //options for users
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            IconButton(onClick = {
                                showDialog = false
                                dragViewModel.restart()
                                navController.navigate("LevelChooser")
                            }) {
                                Icon(Icons.Filled.Home, contentDescription = "")
                            }
                            Text(text = stringResource(id = R.string.goback))
                        }
                        Column {
                            IconButton(onClick = {
                                showDialog = false
                                playerViewModel.restart(0, 5)
                                dragViewModel.restart()
                            }) {
                                Icon(Icons.Filled.Refresh, contentDescription = "")
                            }
                            Text(text = stringResource(id = R.string.restart))
                        }
                        Column {
                            IconButton(onClick = {
                                showDialog = false
                                dragViewModel.restart()
                                navController.navigate("HardStage2")
                            }) {
                                Icon(Icons.Filled.ArrowForward, contentDescription = "")
                            }
                            Text(text = stringResource(id = R.string.nextStage))
                        }

                    }
                }
            }
        }
    }

}

