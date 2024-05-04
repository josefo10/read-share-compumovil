package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun Charge(){
    Column(modifier = Modifier
        .background(colorResource(id = R.color.background))
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally){

        Row(){
            Text(text= stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                color = colorResource(id = R.color.black))
        }
            Image(
                painter = painterResource(id = R.drawable.read_share),
                contentDescription = "Read Share",
                modifier = Modifier.fillMaxWidth()
                    .alpha(0.8f)
            )


    }
}

@Composable
    @Preview
        fun PChargge(){
    Charge()
}