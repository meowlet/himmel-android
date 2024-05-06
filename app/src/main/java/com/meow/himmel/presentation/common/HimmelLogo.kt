package com.meow.himmel.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.meow.himmel.R

@Composable
fun HimmelLogo(
    modifier: Modifier = Modifier
) {
//    Image(
//        modifier = modifier,
//        painter = painterResource(id = R.drawable.himmel_logo),
//        contentDescription = "Himmel Logo",
//    )
    Text(text = "ひ", style = MaterialTheme.typography.displayMedium, modifier = modifier)
}