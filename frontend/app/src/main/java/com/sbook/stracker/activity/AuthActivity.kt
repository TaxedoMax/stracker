package com.sbook.stracker.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sbook.stracker.ui.theme.STrackerTheme
import com.sbook.stracker.view.AuthNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    val userId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AuthNavHost(
                        onAuthSuccess = {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("userId", userId)
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}