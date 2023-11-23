package com.farmingdale.ht_individualproject3.game

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.farmingdale.ht_individualproject3.R
import com.farmingdale.ht_individualproject3.database.ImplParentChildRepo
import com.farmingdale.ht_individualproject3.database.ParentChildDatabase
import com.farmingdale.ht_individualproject3.viewmodels.PlayerViewModel

/**
 * Activity for the game
 */
class GameActivity: ComponentActivity() {
    private lateinit var repo: ImplParentChildRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                ParentChildDatabase::class.java,
                "parentchild.db"
            ).build()
        }
        val childId = intent.getIntExtra("ChildId", 0)
        repo = ImplParentChildRepo(database)
        val playerViewModel = PlayerViewModel(repo, childId)
        super.onCreate(savedInstanceState)

        //plays background music
        val mediaPlayer = MediaPlayer.create(this, R.raw.background)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        setContent {
            GameNavigation(playerViewModel)

        }
    }

}