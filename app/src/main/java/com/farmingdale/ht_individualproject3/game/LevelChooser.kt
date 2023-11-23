package com.farmingdale.ht_individualproject3.game

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_light_error
import com.example.compose.md_theme_light_errorContainer
import com.example.compose.md_theme_light_onPrimary
import com.example.compose.md_theme_light_onPrimaryContainer
import com.example.compose.md_theme_light_tertiary
import com.farmingdale.ht_individualproject3.MainActivity
import com.farmingdale.ht_individualproject3.R

/**
 * View to let users choose level to play
 */
@Composable
fun LevelChooser(navController: NavHostController) {
    val context = LocalContext.current
    val screenwidth = LocalConfiguration.current.screenWidthDp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.game_background),
                contentScale = ContentScale.FillBounds
            )
    )
    {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(.45f)
                .border(4.dp, Color.Black),
            colors = CardDefaults.cardColors(containerColor = md_theme_light_tertiary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.easy_level_label),
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 36.sp
                )

                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(Dp(screenwidth / 4f))
                            .background(md_theme_light_onPrimaryContainer)
                            .clickable(onClick = {
                                navController.navigate("EasyStage1")
                            }),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.stage1),
                            color = md_theme_light_onPrimary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(Dp(screenwidth / 4f))
                            .background(md_theme_light_onPrimaryContainer)
                            .clickable(onClick = {
                                navController.navigate("EasyStage2")
                            }),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.stage2),
                            color = md_theme_light_onPrimary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(Dp(screenwidth / 4f))
                            .background(md_theme_light_onPrimaryContainer)
                            .clickable(onClick = {
                                navController.navigate("EasyStage3")
                            }),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.stage3),
                            color = md_theme_light_onPrimary
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
            Text(text = stringResource(id = R.string.home))
        }

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(.45f)
                .border(4.dp, Color.Black)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = md_theme_light_tertiary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.hard_level_label),
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 36.sp
                )

                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(Dp(screenwidth / 4f))
                            .background(md_theme_light_errorContainer)
                            .clickable(onClick = {
                                navController.navigate("HardStage1")
                            }),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.stage1),
                            color = md_theme_light_error
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(Dp(screenwidth / 4f))
                            .background(md_theme_light_errorContainer)
                            .clickable(onClick = {
                                navController.navigate("HardStage2")
                            }),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.stage2),
                            color = md_theme_light_error
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(Dp(screenwidth / 4f))
                            .background(md_theme_light_errorContainer)
                            .clickable(onClick = {
                                navController.navigate("HardStage3")
                            }),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.stage3),
                            color = md_theme_light_error
                        )
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun PreviewLevelChooser() {
    LevelChooser(rememberNavController())
}