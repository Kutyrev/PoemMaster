package com.github.kutyrev.poemmaster.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.kutyrev.poemmaster.ui.navigation.PoemMasterNavHost
import com.github.kutyrev.poemmaster.ui.theme.PoemMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PoemMasterTheme {
                PoemMasterApp()
            }
        }
    }
}

@Composable
fun PoemMasterApp() {
    val navController = rememberNavController()
    PoemMasterNavHost(
        navController = navController,
        modifier = Modifier
    )
}