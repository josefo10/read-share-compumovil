package com.example.myapplication.ui.theme.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.navigation.AppScreens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Sigin(navController: NavController,
          endEmail: (String) -> Unit){

    var user = User()
    val current = LocalContext.current
    var cPass by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .padding(2.dp),
        topBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = colorResource(id = R.color.background))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.read_share),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.5f)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    fontSize = 30.sp,
                    color = colorResource(id = R.color.black)
                )

            }
        }) {
            innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background))
                .verticalScroll(ScrollState(1), enabled = true)
               ,
            Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        colorResource(id = R.color.background),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.TopCenter
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            colorResource(id = R.color.login),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(12.dp)
                        ,
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {

                    Text(text = "Regístrese con:", fontSize = 18.sp)


                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Image(
                                painter = painterResource(id = R.drawable.google),
                                contentDescription = ""
                            )
                        }
                        Text(
                            text = "ó",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            fontSize = 16.sp
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Image(
                                painter = painterResource(id = R.drawable.facebook),
                                contentDescription = ""
                            )
                        }

                    }
                    CampText(type = "", name = "Nombre") {
                        user.name = it
                    }
                    CampText(type = "email", name = "Correo") {
                        user.email = it
                    }
                    CampText(type = "pass", name = "Contraseña") {
                        user.pass = it
                    }
                    CampText(type = "pass", name = "Confirmar Contraseña") {
                        cPass = it
                    }
                    CampText(type = "", name = "Direccion") {
                        user.address = it
                    }
                    CampText(type = "phone", name = "Telefono") {
                        user.phone = it
                    }

                    Row(modifier = Modifier.padding(10.dp)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Button(
                                onClick = {

                                    if (user.isNotBlank()){
                                        if((user.pass == cPass)){
                                            endEmail(user.email)
                                            create(user,navController,current)

                                        }else{
                                                showToast("Las contraseñas no son iguales", current)
                                        }
                                    }else{

                                        showToast("Campos vacíos",current)
                                    }
                                },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(200.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(
                                        id = R.color.button
                                    )
                                )
                            ) {
                                Text(text = "Enviar", fontSize = 18.sp)
                            }

                        }


                    }
                }
            }
        }
        }
    }


fun create(user: User, navController: NavController,
           current:Context){
    val db = FirebaseFirestore.getInstance()

    FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email,user.pass)
        .addOnCompleteListener{
            if(it.isSuccessful){
                db.collection("users").document(user.email).set(
                    hashMapOf(
                        "name" to user.name,
                        "address" to user.address,
                        "phone" to user.phone
                    )
                )

                navController.navigate(AppScreens.Main.route)


            }else{
                showToast("Problemas para registrar", current)
            }

        }




}

@Composable
private fun createGoogleSignInIntent(): Intent {
    val current = LocalContext.current
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(stringResource(id = R.string.tokenId))
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(current, gso)
    return googleSignInClient.signInIntent
}
