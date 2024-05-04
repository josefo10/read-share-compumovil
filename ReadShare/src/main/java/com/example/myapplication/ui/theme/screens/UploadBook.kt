package com.example.myapplication.ui.theme.screens

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.example.myapplication.R
import com.example.myapplication.model.Book
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun UploadBook(){
    var book = Book()
    val context = LocalContext.current
    var selectedImageBitmaps by remember { mutableStateOf<List<ImageBitmap>>(emptyList()) }

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .background(
            colorResource(id = R.color.background),
            shape = RoundedCornerShape(50.dp)
        )
        .padding(20.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,) {
        Text(text = "Compartir libro", fontSize = 24.sp, fontFamily = FontFamily.SansSerif, color = colorResource(
            id = R.color.black
        ))
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,){


            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(60.dp),
                label = {
                    Text(
                        text = "Title",
                        fontSize = 14.sp
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Default
                ),
                readOnly = false,
                colors = OutlinedTextFieldDefaults.colors(
                    colorResource(id = R.color.background),
                    focusedBorderColor = colorResource(
                        id = R.color.black
                    ),
                    focusedLabelColor = colorResource(id = R.color.black),
                    unfocusedBorderColor = colorResource(
                        id = R.color.label
                    )
                ),
                textStyle = TextStyle(color = colorResource(id = R.color.black), fontSize = 14.sp)
            )

            OutlinedTextField(
                value = description,
                onValueChange = {
                    if (it.length <= 60) {
                        description = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(60.dp),
                label = {
                    Text(
                        text = "Description",
                        fontSize = 14.sp
                    )
                },
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Go
                ),
                readOnly = false,
                colors = OutlinedTextFieldDefaults.colors(
                    colorResource(id = R.color.background),
                    focusedBorderColor = colorResource(
                        id = R.color.black
                    ),
                    focusedLabelColor = colorResource(id = R.color.black),
                    unfocusedBorderColor = colorResource(
                        id = R.color.label
                    )
                ),
                textStyle = TextStyle(color = colorResource(id = R.color.black), fontSize = 14.sp)
            )


            GalleryButton { imageBitmaps ->
                selectedImageBitmaps = imageBitmaps.take(5)// Limit to maximum 5 images
            }
        }
        SelectedImagesPreview(selectedImageBitmaps = selectedImageBitmaps)

    }
}

/*@Composable
@Preview
fun pUb(){

    UploadBook()
}*/
fun ImageBitmapFromUri(contentResolver: ContentResolver, uri: Uri): ImageBitmap? {
    return try {
        contentResolver.openInputStream(uri)?.use { inputStream ->
            val options = BitmapFactory.Options()
            options.inSampleSize = 2 // Sample size to reduce memory usage, adjust as needed
            BitmapFactory.decodeStream(inputStream, null, options)?.asImageBitmap()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun GalleryButton(onImagesSelected: (imageBitmaps: List<ImageBitmap>) -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { uris ->
        // Handle the result of image selection
        val imageBitmaps = uris.mapNotNull { uri ->
            ImageBitmapFromUri(context.contentResolver, uri)
        }
        onImagesSelected(imageBitmaps)
    }

    IconButton(
        onClick = { launcher.launch("image/*") },
        modifier = Modifier
            .padding(16.dp)
            .size(50.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_mage),
            contentDescription = "Agregar imágenes",
            tint = LocalContentColor.current
        )
    }
}

@Composable
fun SelectedImagesPreview(selectedImageBitmaps: List<ImageBitmap>) {
    LazyRow {
        items(selectedImageBitmaps) { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
@Preview
fun PUB(){
    UploadBook()
}