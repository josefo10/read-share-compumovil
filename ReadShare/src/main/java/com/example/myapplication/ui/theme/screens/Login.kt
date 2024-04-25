package com.example.myapplication.ui.theme.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun Login(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()) {


        Box(modifier = Modifier.padding(vertical = 25.dp)
            .height(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.read_share),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.alpha(0.5f)
            )
            Text(text = stringResource(id = R.string.app_name),modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
                fontSize = 30.sp,
                color = colorResource(id = R.color.black))
        }


        Box(modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.login),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(30.dp),
            contentAlignment = Alignment.TopCenter
            ){
            Column (modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Text(text = "Ingresar con:", fontSize = 18.sp)


                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
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
                CampText(type = "email", name = "Correo") {

                }
                CampText(type = "pass", name = "Contraseña") {

                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button))
                ) {
                    Text(text = "Ingresar", fontSize = 18.sp)
                }
                LinkText("¿Olvidaste tu contraseña?") {

                }


            }
        }

    }

}

@Composable
@Preview
fun PLogin(){
    Login()
}


@Composable
fun CampText(
    type: String,
    name: String,
    endText: (String) -> Unit
) {
    val current = LocalContext.current

    var text by remember {
        mutableStateOf("")
    }

    var flag by remember {
        mutableStateOf(KeyboardType.Text)
    }

    val visualTransformation = if (type == "pass") {
       PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    when (type) {
        "pass" -> {
            flag = KeyboardType.Password
        }
        "email" -> {
            flag = KeyboardType.Email
        }
        "phone" -> {
            flag = KeyboardType.Phone
        }
            }

    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.length <= 30) {
                text = it
                endText(text)
            } else {
                showToast("Demasiados caracteres", current)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(60.dp),
        label = { Text(text = name,
            fontSize = 14.sp) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = flag,
            imeAction = ImeAction.Default
        ),
        readOnly = false,
        colors = OutlinedTextFieldDefaults.colors(colorResource(id =R.color.background), focusedBorderColor = colorResource(
            id = R.color.black
        ), focusedLabelColor = colorResource(id = R.color.black), unfocusedBorderColor = colorResource(
            id = R.color.label
        )
        ),
        textStyle = TextStyle(color = colorResource(id = R.color.black), fontSize = 14.sp),
        visualTransformation = visualTransformation )
}
private fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}