package com.farmingdale.ht_individualproject3.game

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farmingdale.ht_individualproject3.game.easy.level.EasyStageOne
import com.farmingdale.ht_individualproject3.game.easy.level.EasyStageThree
import com.farmingdale.ht_individualproject3.game.easy.level.EasyStageTwo
import com.farmingdale.ht_individualproject3.game.hard.level.HardStageOne
import com.farmingdale.ht_individualproject3.game.hard.level.HardStageThree
import com.farmingdale.ht_individualproject3.game.hard.level.HardStageTwo
import com.farmingdale.ht_individualproject3.viewmodels.PlayerViewModel

/**
 * Handles navigation between games
 */
@Composable
fun GameNavigation(playerViewModel: PlayerViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LevelChooser") {

        composable("LevelChooser"){
            LevelChooser(navController)
        }
        composable("EasyStage1"){
            playerViewModel.restart(0,0)
            EasyStageOne(navController, playerViewModel = playerViewModel)
        }
        composable("EasyStage2"){
            playerViewModel.restart(5,0)
            EasyStageTwo(navController = navController, playerViewModel = playerViewModel)
        }
        composable("EasyStage3"){
            playerViewModel.restart(0, 0)
            EasyStageThree(navController = navController, playerViewModel = playerViewModel)
        }
        composable("HardStage1"){
            playerViewModel.restart(0,5)
            HardStageOne(navController = navController, playerViewModel = playerViewModel)
        }
        composable("HardStage2"){
            playerViewModel.restart(0, 3)
            HardStageTwo(navController = navController, playerViewModel = playerViewModel)
        }
        composable("HardStage3"){
            playerViewModel.restart(0,5)
            HardStageThree(navController = navController, playerViewModel = playerViewModel)
        }
    }
}