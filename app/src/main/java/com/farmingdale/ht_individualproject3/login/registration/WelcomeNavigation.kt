package com.farmingdale.ht_individualproject3.login.registration

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farmingdale.ht_individualproject3.login.registration.child.ChildLogin
import com.farmingdale.ht_individualproject3.login.registration.child.ChildRegistration
import com.farmingdale.ht_individualproject3.login.registration.parent.ParentLogin
import com.farmingdale.ht_individualproject3.login.registration.parent.ParentRegistration
import com.farmingdale.ht_individualproject3.viewmodels.ChildViewModel
import com.farmingdale.ht_individualproject3.viewmodels.ParentViewModel

/**
 * Navigation for login and registration
 */
@Composable
fun WelcomeNavigation(childViewModel: ChildViewModel, parentViewModel: ParentViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Welcome") {

        composable("Welcome") {
            WelcomeScreen(navController = navController)
        }

        composable("ChildLogin"){
            ChildLogin(navController = navController, childViewModel)
        }

        composable("ParentLogin"){
            ParentLogin(navController = navController, parentViewModel)
        }

        composable("ChildRegistration"){
            ChildRegistration(navController = navController, viewModel = childViewModel)
        }
        
        composable("ParentRegistration"){
            ParentRegistration(navController = navController, parentViewModel)
        }

    }
}