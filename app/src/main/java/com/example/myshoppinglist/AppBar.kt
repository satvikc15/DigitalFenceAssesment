package com.example.myshoppinglist

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(title:String, onBackNavClicked:()->Unit) {
    val navigation: (@Composable () -> Unit) = {
        if(!title.contains("My Shopping List")) {
            IconButton(onClick = { onBackNavClicked() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }else{
            null
        }
    }
    TopAppBar(
        title = { Text(text = title,
            color = colorResource(R.color.white),
            modifier = Modifier.padding(8.dp).height(32.dp) )},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.app_default)
        ),
        navigationIcon = navigation
    )

}