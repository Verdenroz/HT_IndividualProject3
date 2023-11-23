package com.farmingdale.ht_individualproject3.login.registration.parent

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_light_error
import com.example.compose.md_theme_light_onPrimaryContainer
import com.example.compose.md_theme_light_primary
import com.farmingdale.ht_individualproject3.R
import com.farmingdale.ht_individualproject3.database.Parent
import com.farmingdale.ht_individualproject3.viewmodels.ParentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ParentLogin(navController: NavHostController, viewModel: ParentViewModel) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val repo = viewModel.getRepo()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.login_registration_background),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.lighting(md_theme_light_primary, Color.DarkGray)
            ),
    ) {
        IconButton(
            onClick = { navController.navigate("Welcome") },
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_button)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(20.dp)
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
                text = stringResource(id = R.string.parent_login_headline),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
            )
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(text = stringResource(id = R.string.username_label)) },
                colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.password_label)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
            )
            OutlinedButton(
                onClick = {
                    var parent: Parent? = null
                    scope.launch(Dispatchers.IO) {
                        withContext(Dispatchers.Main){
                            Toast.makeText(context, "Logging in", Toast.LENGTH_SHORT).show()
                        }
                        parent = repo.database.parentDao().getParentByCredentials(userName, password)
                        if (parent != null) {
                            val intent = Intent(context, ParentPortalActivity::class.java).apply {
                                this.putExtra("ParentID", parent!!.id)
                            }
                            context.startActivity(intent)
                        }
                        else{
                            withContext(Dispatchers.Main){
                                Toast.makeText(context, "Could not find account", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                },
                colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_onPrimaryContainer),
            ) {
                Text(text = stringResource(id = R.string.login_btn))
            }

            TextButton(onClick = { navController.navigate("ParentRegistration") }) {
                Text(
                    text = stringResource(id = R.string.sign_up_label),
                    fontSize = 16.sp,
                    color = md_theme_light_onPrimaryContainer,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewParentLogin() {
    val viewModel = viewModel<ParentViewModel>()
    ParentLogin(rememberNavController(), viewModel)
}