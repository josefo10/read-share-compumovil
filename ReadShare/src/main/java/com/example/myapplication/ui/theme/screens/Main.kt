package com.example.myapplication.ui.theme.screens

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@Composable
fun Main(
    token : String,
    navController: NavController
){
    var bookMenuFlag by remember { mutableStateOf(false) }
    var name by remember {
        mutableStateOf("")
    }
    val db = FirebaseFirestore.getInstance()
    db.collection("users").document(token).get().addOnSuccessListener {
        name = (it.get("name") as String?).toString()
    }
    Scaffold(modifier = Modifier
        .fillMaxSize(),
        bottomBar = {
            
            Row (horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.main),
                        contentDescription = "", tint = colorResource(id = R.color.black))
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.search),
                        contentDescription = "")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.menu),
                        contentDescription = "", tint = colorResource(id = R.color.black))
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.acount),
                        contentDescription = "", tint = colorResource(id = R.color.black))
                }
            }
            
        },
        containerColor = colorResource(id = R.color.background),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(AppScreens.Upload.route)},
                modifier = Modifier
                    .size(50.dp)
                                   ) {
                Icon(painter = painterResource(id = R.drawable.add_book), contentDescription = "",
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxSize()
                       , tint = colorResource(id = R.color.button))
            }
        }

        ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = name)

            Button(onClick = { FirebaseAuth.getInstance().signOut()
                    navController.popBackStack()


            }) {

                Text(text = "Cerrar sesión")
            }



            Box(modifier = Modifier.fillMaxSize()) {
                // Contenido de la pantalla

                // Menú emergente
                DropdownMenu(
                    expanded = bookMenuFlag,
                    onDismissRequest = { bookMenuFlag = false },
                    modifier = Modifier
                        .padding(16.dp)  // Ajusta el margen del menú emergente según sea necesario
                        .align(Alignment.BottomStart)  // Alinea el menú emergente en la esquina inferior derecha
                ) {
                    DropdownMenuItem(onClick = {
                        // Acción para el primer elemento del menú
                        bookMenuFlag = false
                    }, text = { Text(text = "Intercambiar Libro") })
                    DropdownMenuItem(onClick = {
                        // Acción para el segundo elemento del menú
                        bookMenuFlag = false
                    }, text = { Text(text = "Vender Libro") })
                    // Agrega más elementos de menú según sea necesario
                }
            }


        }
        
    }
    
}


