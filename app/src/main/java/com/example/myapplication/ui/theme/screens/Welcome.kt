package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun Welcome(){
    Column(modifier = Modifier
        .background(colorResource(id = R.color.background))
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally){

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
            horizontalArrangement = Arrangement.Center){
            Text(text= stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.read_share),
            contentDescription = "Read Share",
            modifier = Modifier.fillMaxWidth()
        )
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){


        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .width(230.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button))
                ) {
            Text(text = "Ingresar",
                fontSize = 20.sp,
                color = colorResource(id = R.color.white))

        }
        //Spacer(modifier = Modifier.height(20.dp))
    Row {
        Text(text = "¿No tienes una cuenta?",
            fontSize = 16.sp,
            color = colorResource(id = R.color.black))
        Spacer(modifier = Modifier.width(10.dp))
        LinkText {

        }
    }


    }
    }
}
@Composable
fun LinkText(onClick: () -> Unit) {
    val text = AnnotatedString.Builder()
        .apply {
            append("Regístrate")
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                ),
                start = 0,
                end = length
            )
            addStringAnnotation("LINK", "more_info", 0, length)
        }
        .toAnnotatedString()

    ClickableText(
        text = text,
        onClick = { offset ->
            text.getStringAnnotations("LINK", offset, offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    )
}

@Composable
@Preview
fun Pwelcome(){
    Welcome()
}