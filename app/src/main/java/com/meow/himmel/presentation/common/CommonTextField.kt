package com.meow.himmel.presentation.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Eye
import compose.icons.fontawesomeicons.regular.EyeSlash

@Composable
fun SingleLinedTextField(
    text: String,
    label: String,
    placeholder: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        singleLine = true,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
    )
}

@Composable
fun SingleLinedToggleablePasswordField(
    text: String,
    label: String,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        singleLine = true,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            if (isPasswordVisible) {
                IconButton(
                    onClick = { isPasswordVisible = false },
                    modifier = Modifier.fillMaxSize(0.05f)
                ) {
                    Icon(imageVector = FontAwesomeIcons.Regular.EyeSlash, contentDescription = null)
                }
            } else {
                IconButton(
                    onClick = { isPasswordVisible = true },
                    modifier = Modifier.fillMaxSize(0.05f)
                ) {
                    Icon(imageVector = FontAwesomeIcons.Regular.Eye, contentDescription = null)
                }
            }
        },
        leadingIcon = leadingIcon,
    )
}


@Composable
fun SingleLinedPasswordField(
    text: String,
    label: String,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        singleLine = true,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = leadingIcon,
    )
}

@Preview
@Composable
private fun asldfkj() {
    SingleLinedToggleablePasswordField(
        text = "",
        label = "Password",
        placeholder = "Your email address",
        onValueChange = {}
    )
}