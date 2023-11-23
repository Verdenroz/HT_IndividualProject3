package com.farmingdale.ht_individualproject3.login.registration.child

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_light_error
import com.example.compose.md_theme_light_onPrimaryContainer
import com.farmingdale.ht_individualproject3.R
import com.farmingdale.ht_individualproject3.database.Child
import com.farmingdale.ht_individualproject3.database.Parent
import com.farmingdale.ht_individualproject3.game.GameActivity
import com.farmingdale.ht_individualproject3.login.registration.parent.ParentPortalActivity
import com.farmingdale.ht_individualproject3.viewmodels.ChildViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Child Registration screen
 */
@Composable
fun ChildRegistration(navController: NavHostController, viewModel: ChildViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repo = viewModel.getRepo()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    //to show if users if data entered is invalid
    var isErrorFirstName by remember { mutableStateOf(true) }
    var isErrorLastName by remember { mutableStateOf(true) }
    var isErrorMonth by remember { mutableStateOf(true) }
    var isErrorDay by remember { mutableStateOf(true) }
    var isErrorYear by remember { mutableStateOf(true) }
    var isErrorUserName by remember { mutableStateOf(true) }
    var isErrorPassword by remember { mutableStateOf(true) }
    var isErrorConfirmPassword by remember { mutableStateOf(true) }

    var dialogVisibility by remember { mutableStateOf(false) }

    fun validateData(
        firstName: String,
        lastName: String,
        password: String,
        confirmPassword: String,
        username: String,
        month: String,
        day: String,
        year: String
    ): Boolean {
        isErrorFirstName = firstName.length in 3..30
        isErrorLastName = lastName.length in 3..30
        isErrorUserName = username.length in 3..30
        isErrorPassword = password.isNotBlank()
        isErrorConfirmPassword = password == confirmPassword
        //validate month
        isErrorMonth = try {
            month.toInt() in 1..12
        } catch (e: NumberFormatException) {
            false
        }
        //validate day
        isErrorDay = try {
            when (month.toInt()) {
                1, 3, 5, 7, 8, 10, 12 -> day.toInt() in 1..31
                4, 6, 9, 11 -> day.toInt() in 1..30
                2 -> day.toInt() in 1..29
                else -> false
            }
        } catch (e: NumberFormatException) {
            false
        }
        //validate year
        isErrorYear = try {
            year.toInt() in 1900..2100
        } catch (e: NumberFormatException) {
            false
        }
        //returns true only if all the data is valid
        return isErrorFirstName && isErrorLastName && isErrorUserName && isErrorPassword && isErrorConfirmPassword && isErrorMonth && isErrorDay && isErrorYear
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.login_registration_background),
                contentScale = ContentScale.FillBounds,
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
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.main_icon),
                contentDescription = stringResource(id = R.string.background),
                modifier = Modifier
                    .size(200.dp)
            )
            Text(
                text = stringResource(id = R.string.child_registration_headline),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(text = stringResource(id = R.string.firstName_label)) },
                    colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                    isError = !isErrorFirstName,
                    modifier = Modifier.weight(0.5f)
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text(text = stringResource(id = R.string.lastName_label)) },
                    colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                    isError = !isErrorLastName,
                    modifier = Modifier.weight(0.5f)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = month,
                    onValueChange = { month = it },
                    label = { Text(text = stringResource(id = R.string.month_label)) },
                    colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                    isError = !isErrorMonth,
                    modifier = Modifier.weight(0.33f)
                )
                OutlinedTextField(
                    value = day,
                    onValueChange = { day = it },
                    label = { Text(text = stringResource(id = R.string.day_label)) },
                    colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                    isError = !isErrorDay,
                    modifier = Modifier.weight(0.33f)
                )
                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text(text = stringResource(id = R.string.year_label)) },
                    colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                    isError = !isErrorYear,
                    modifier = Modifier.weight(0.33f)
                )
            }
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(text = stringResource(id = R.string.username_label)) },
                colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                isError = !isErrorUserName
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.password_label)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                isError = !isErrorPassword
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = stringResource(id = R.string.confirm_password_label)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                isError = !isErrorConfirmPassword
            )
            OutlinedButton(
                onClick = {
                    if (validateData(
                            firstName,
                            lastName,
                            password,
                            confirmPassword,
                            userName,
                            month,
                            day,
                            year
                        )
                    ) {
                        //to show dialog asking for parent
                        dialogVisibility = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_onPrimaryContainer),
            ) {
                Text(text = stringResource(id = R.string.register_btn))
            }
        }
    }
    //shows dialog to get a parent
        if (dialogVisibility) {
            Dialog(
                onDismissRequest = { dialogVisibility = false },
            ) {
                var parentUsername by remember { mutableStateOf("") }
                var parentPassword by remember { mutableStateOf("") }
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.need_parent_dialog),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        OutlinedTextField(
                            value = parentUsername,
                            onValueChange = { parentUsername = it },
                            label = { Text(text = stringResource(id = R.string.username_label)) },
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                        )
                        OutlinedTextField(
                            value = parentPassword,
                            onValueChange = { parentPassword = it },
                            label = { Text(text = stringResource(id = R.string.password_label)) },
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.colors(errorIndicatorColor = md_theme_light_error),
                        )
                        TextButton(onClick = { navController.navigate("ParentRegistration") }) {
                            Text(
                                text = stringResource(id = R.string.sign_up_label),
                                fontSize = 16.sp,
                                color = md_theme_light_onPrimaryContainer,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        //Exits dialog
                        TextButton(onClick = { dialogVisibility = false }) {
                            Text(text = stringResource(id = android.R.string.cancel))
                        }

                        TextButton(onClick = {
                            var parent: Parent? = null
                            //this coroutine will fetch the parent account
                            scope.launch(Dispatchers.IO) {
                                withContext(Dispatchers.Main){
                                    Toast.makeText(context, "Logging in", Toast.LENGTH_SHORT).show()
                                }
                                parent = repo.database.parentDao().getParentByCredentials(parentUsername, parentPassword)
                                //if parent is found, create child accout with parent id
                                if (parent != null) {
                                    val child = Child(
                                        parentId = parent!!.id,
                                        firstName = firstName,
                                        lastName = lastName,
                                        userName = userName,
                                        password = password,
                                        month = month,
                                        day = day,
                                        year = year
                                    )
                                    repo.database.childDao().insertItem(child)
                                    dialogVisibility = false
                                    val newAccount = repo.database.childDao().getChildByCredentials(userName, password)
                                    if (newAccount != null) {
                                        val intent = Intent(context, GameActivity::class.java)
                                        intent.putExtra("ChildId", newAccount.childId)
                                        context.startActivity(intent)
                                    }
                                }
                                else{
                                    //if parent cannot be found notify user
                                    withContext(Dispatchers.Main){
                                        Toast.makeText(context, "Could not find account", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                        }
                        ) {
                            Text(text = stringResource(id = R.string.login_btn))
                        }
                    }
                }
            }
        }

}

@Preview
@Composable
fun PreviewChildRegistration() {
    val childViewModel = viewModel<ChildViewModel>()
    ChildRegistration(navController = rememberNavController(), childViewModel)
}