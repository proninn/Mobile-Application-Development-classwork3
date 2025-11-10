package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryApp()
        }
    }
}

// 数据类：代表一幅画作
data class Artwork(
    val imageResId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtGalleryApp() {
    // 图片数据列表
    val artworks = listOf(
        Artwork(
            R.drawable._1,
            "阿尔诺芬妮夫妇像",
            "Jan van Eyck",
            "1434"
        ),
        Artwork(
            R.drawable._2,
            "宫娥",
            "委拉斯凯茨",
            "1656"
        ),
        Artwork(
            R.drawable._3,
            "蒙娜丽莎",
            "达芬奇",
            "1506"
        )
    )

    // 当前显示的图片索引
    var currentIndex by remember { mutableStateOf(0) }

    // 获取当前画作
    val currentArtwork = artworks[currentIndex]

    // 按钮点击逻辑
    val onPreviousClick = {
        if (currentIndex > 0) {
            currentIndex--
        }
    }

    val onNextClick = {
        if (currentIndex < artworks.size - 1) {
            currentIndex++
        }
    }

    // 主界面布局
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 图片区域
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 5f)
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = currentArtwork.imageResId),
                contentDescription = currentArtwork.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }


        // 描述信息区域
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = currentArtwork.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "${currentArtwork.artist} (${currentArtwork.year})",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // 按钮区域
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onPreviousClick,
                enabled = currentIndex > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentIndex > 0) Color(0xFF4A6FA5) else Color.Gray
                ),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Previous", color = Color.White)
            }

            Button(
                onClick = onNextClick,
                enabled = currentIndex < artworks.size - 1,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentIndex < artworks.size - 1) Color(0xFF4A6FA5) else Color.Gray
                ),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Next", color = Color.White)
            }
        }
    }
}