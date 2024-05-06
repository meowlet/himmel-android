package com.meow.himmel.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.FacebookF


@Composable
fun IconButton(
    icon: ImageVector,
    text: String,
    isOutlined: Boolean = false,
    onClick: () -> Unit
) {
    when (isOutlined) {
        true ->
            OutlinedButton(onClick = onClick) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.weight(0.075f)
                    )
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.925f)
                    )
                }
            }

        false -> Button(onClick = onClick) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.weight(0.075f)
                )
                Text(text = text, textAlign = TextAlign.Center, modifier = Modifier.weight(0.925f))
            }
        }
    }
}

@Preview
@Composable
private fun adslfkj() {
    IconButton(icon = FontAwesomeIcons.Brands.FacebookF, text = "Facebook", isOutlined = true) {}
}
