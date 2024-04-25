package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun Sigin(){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .verticalScroll(ScrollState(2), enabled = true)
            .padding(2.dp)) {


        Box(modifier = Modifier
            .height(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.read_share),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .alpha(0.5f)
                    .fillMaxSize()
            )
            Text(text = stringResource(id = R.string.app_name),modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
                fontSize = 30.sp,
                color = colorResource(id = R.color.black)
            )
        }



            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        color = colorResource(id = R.color.login),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Text(text = "Registrarse con:", fontSize = 18.sp)


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
                    Text(text = "ó", modifier = Modifier.padding(horizontal = 8.dp), fontSize = 16.sp)
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = ""
                        )
                    }

                }
                CampText(type = "", name = "Nombre") {

                }
                CampText(type = "email", name = "Correo") {

                }
                CampText(type = "pass", name = "Contraseña") {

                }
                CampText(type = "pass", name = "Confirmar Contraseña") {

                }
                CampText(type = "", name = "Direccion") {

                }
                CampText(type = "phone", name = "Telefono") {

                }

Row(modifier = Modifier.padding(10.dp)){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(10.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button))
        ) {
            Text(text = "Enviar", fontSize = 18.sp)
        }

    }

}



            }

    }
}

@Composable
@Preview
fun PSigin(){
    Sigin()
}