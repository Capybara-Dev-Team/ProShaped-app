package com.example.proshapedapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.proshapedapp.gender.GenderPicker
import com.example.proshapedapp.ui.theme.GenderPickerTheme

class GenderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenderPickerTheme {
                GenderPicker(
                    modifier = Modifier.fillMaxSize()
                ) {
//                    Button(
//                        onClick = {
//                            val intent = Intent(this,MainActivity::class.java)
//                            startActivity(intent)
//                        }
//                    ) {
//
//                    }
                }
            }
        }
    }
}