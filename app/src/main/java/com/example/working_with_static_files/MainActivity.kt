package com.example.working_with_static_files

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.working_with_static_files.ui.theme.Working_with_Static_FilesTheme

// BITS Pilani Official Brand Colors
val BitsBlue = Color(0xFF003366)
val BitsRed = Color(0xFFC41230)
val BackgroundGray = Color(0xFFF2F2F2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Working_with_Static_FilesTheme {
                StaticFileDemo()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaticFileDemo() {
    val context = LocalContext.current
    // State to hold the content fetched from files [cite: 13, 114]
    var displayedContent by remember { mutableStateOf("Ready for demo...\nSelect a source above.") }
    var currentSource by remember { mutableStateOf("No Source Selected") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("BITS PILANI EXPLORER", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Mobile Development Session", color = Color.White, fontSize = 10.sp)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = BitsBlue)
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = BitsBlue, contentPadding = PaddingValues(8.dp)) {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("JIBIN N | SDPD ", color = Color.White, fontWeight = FontWeight.Medium)
                    Text("BITS Pilani - WILP 2026", color = Color.LightGray, fontSize = 11.sp)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(BackgroundGray)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Working with Static Files",
                style = MaterialTheme.typography.headlineSmall,
                color = BitsBlue,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Packaged Read-Only Resources",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // BUTTON 1: Fetch from RAW
            // Static files are read-only and available immediately after installation [cite: 31, 39]
            Button(
                onClick = {
                    currentSource = "res/raw/config.json"
                    try {
                        // Access via resource id -> R.raw.config [cite: 64, 80]
                        val inputStream = context.resources.openRawResource(R.raw.config)
                        displayedContent = inputStream.bufferedReader().use { it.readText() }
                    } catch (e: Exception) {
                        displayedContent = "Error: File 'res/raw/config.json' not found!"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = BitsBlue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Fetch App Config (RAW)")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // BUTTON 2: Fetch from ASSETS
            // Access via path string [cite: 68, 116]
            Button(
                onClick = {
                    // UPDATED: Path changed from "help/user_guide.txt" to "user_guide.txt"
                    // based on your project structure [cite: 103]
                    currentSource = "assets/user_guide.txt"
                    try {
                        val inputStream = context.assets.open("user_guide.txt")
                        displayedContent = inputStream.bufferedReader().use { it.readText() }
                    } catch (e: Exception) {
                        displayedContent = "Error: File 'user_guide.txt' not found in Assets!"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = BitsRed),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Fetch User Guide (ASSETS)")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // DISPLAY AREA
            Text(
                text = "Source: $currentSource",
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = BitsBlue
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                color = Color.White,
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 4.dp
            ) {
                Text(
                    text = displayedContent,
                    modifier = Modifier.padding(16.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}