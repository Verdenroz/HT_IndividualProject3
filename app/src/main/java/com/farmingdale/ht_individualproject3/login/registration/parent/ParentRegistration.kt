package com.farmingdale.ht_individualproject3.login.registration.parent

import android.content.Intent
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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

/**
 * View for Parent Registration
 */
@Composable
fun ParentRegistration(navController: NavHostController, viewModel: ParentViewModel) {
    val context = LocalContext.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    //to show user if data is entered correctly
    var isErrorFirstName by remember { mutableStateOf(true) }
    var isErrorLastName by remember { mutableStateOf(true) }
    var isErrorMonth by remember { mutableStateOf(true) }
    var isErrorDay by remember { mutableStateOf(true) }
    var isErrorYear by remember { mutableStateOf(true) }
    var isErrorUserName by remember { mutableStateOf(true) }
    var isErrorPassword by remember { mutableStateOf(true) }
    var isErrorConfirmPassword by remember { mutableStateOf(true) }

    /**
     * Validates entered data
     */
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
                text = stringResource(id = R.string.parent_registration_headline),
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
                    //if data is valid
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
                        //create parent
                        val parent = Parent(
                            firstName = firstName,
                            lastName = lastName,
                            userName = userName,
                            password = password,
                            month = month,
                            day = day,
                            year = year
                        )
                        //create account
                        viewModel.createParentAccount(parent)
                        val intent = Intent(context, ParentPortalActivity::class.java).apply {
                            this.putExtra("ParentID", parent.id)
                        }
                        Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT)
                            .show()
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_onPrimaryContainer),
            ) {
                Text(text = stringResource(id = R.string.register_btn))
            }
        }
    }

}

@Preview
@Composable
fun PreviewParentRegistration() {
    val parentViewModel = viewModel<ParentViewModel>()
    ParentRegistration(navController = rememberNavController(), parentViewModel)
}