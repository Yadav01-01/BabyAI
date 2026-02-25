package com.compose.babyai.util

import android.content.Context
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShareButton(context: Context) {

    Button(onClick = {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Hey! Check this out ")
        }

        context.startActivity(
            Intent.createChooser(intent, "Share via")
        )
    }) {
        Text("Share")
    }
}