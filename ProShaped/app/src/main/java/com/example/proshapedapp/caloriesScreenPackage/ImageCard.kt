package com.example.proshapedapp.caloriesScreenPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp    //shadow
    ) {
        //items stacked on top of each other
        Box(
            modifier = Modifier
                .height(300.dp)

        ){
            //what's first is at the bottom of the stack

            //image
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )

            //gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f   //change based on where the box is put
                        )
                    )
            )

            //text
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp), //so it's not completely at the bottom
                contentAlignment = Alignment.BottomStart
            ){
                Text(title, style = TextStyle(color = Color.White), fontSize = 16.sp)
            }

        }
    }
}