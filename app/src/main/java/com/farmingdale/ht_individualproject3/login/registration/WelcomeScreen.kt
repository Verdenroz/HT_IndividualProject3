package com.farmingdale.ht_individualproject3.login.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_light_onPrimaryContainer
import com.farmingdale.ht_individualproject3.R

/**
 * The initial screen
 */
@Composable
fun WelcomeScreen(navController: NavHostController) {
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.welcome_background),
                    contentScale = ContentScale.FillBounds
                ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_icon),
                    contentDescription = stringResource(id = R.string.background),
                    modifier = Modifier
                        .size(300.dp)
                )
                Text(
                    text = stringResource(id = R.string.headline),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 46.sp,
                )
            }
            OutlinedButton(
                onClick = { navController.navigate("ChildLogin") },
                colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_onPrimaryContainer),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
            ) {
                Text(text = stringResource(id = R.string.welcome_btn))
                Icon(Icons.Filled.PlayArrow, contentDescription = stringResource(id = R.string.welcome_btn))
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { navController.navigate("ParentLogin") }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(id = R.string.parent_portal_btn),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    Text(text = stringResource(id = R.string.parent_portal_btn))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWelcomeScreen() {
    WelcomeScreen(rememberNavController())
}