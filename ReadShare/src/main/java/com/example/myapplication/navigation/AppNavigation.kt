package com.example.myapplication.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.screens.Charge
import com.example.myapplication.ui.theme.screens.Login
import com.example.myapplication.ui.theme.screens.Sigin
import com.example.myapplication.ui.theme.screens.Welcome
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.Charge.route){
        composable(route = AppScreens.Charge.route){
            LaunchedEffect(Unit) {
                // Espera tres segundos
                delay(3000)
                // Navega a la siguiente pantalla después de 3 segundos
                navController.navigate(AppScreens.Welcome.route) {
                    // Limpiar la pila de retroceso
                    popUpTo(AppScreens.Charge.route){
                        inclusive = true
                    }
                    // Evitar que la pantalla de carga esté en la pila de retroceso
                    launchSingleTop = true
                }
            }
            Charge()
        }
        composable(route = AppScreens.Welcome.route){
            Welcome(navController)
        }
        composable(route= AppScreens.Login.route){
            Login()
        }
        composable(route= AppScreens.Sigin.route){
            Sigin()
        }
    }
}

