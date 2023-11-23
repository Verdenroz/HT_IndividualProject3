package com.farmingdale.ht_individualproject3

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.compose.AppTheme
import com.farmingdale.ht_individualproject3.database.ImplParentChildRepo
import com.farmingdale.ht_individualproject3.database.ParentChildDatabase
import com.farmingdale.ht_individualproject3.login.registration.WelcomeNavigation
import com.farmingdale.ht_individualproject3.viewmodels.ChildViewModel
import com.farmingdale.ht_individualproject3.viewmodels.ParentViewModel

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            ParentChildDatabase::class.java,
            "parentchild.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mediaPlayer = MediaPlayer.create(this, R.raw.open)
        mediaPlayer.start()
        setContent {
            val repo = ImplParentChildRepo(database)
            val childViewModel = ChildViewModel(repo)
            val parentViewModel = ParentViewModel(repo)
            AppTheme {
               WelcomeNavigation(childViewModel = childViewModel, parentViewModel = parentViewModel)
            }
        }
    }
}

