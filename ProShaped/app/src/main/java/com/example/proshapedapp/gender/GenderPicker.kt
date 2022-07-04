package com.example.proshapedapp.gender

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proshapedapp.R
import com.example.proshapedapp.Screen
import com.example.proshapedapp.gender.Gender

@Composable
fun GenderPicker(
    modifier: Modifier = Modifier,
    maleGradient: List<Color> = listOf(Color(0xFF6D6DFF), Color.Blue),
    femaleGradient: List<Color> = listOf(Color(0xFFEA76FF), Color.Magenta),
    distanceBetweenGenders: Dp = 50.dp,
    pathScaleFactor: Float = 7f,
    navController: NavHostController
//    onGenderSelected: String
) {
    var selectedGender by remember {
        mutableStateOf<Gender>(Gender.Female)
    }
    var center by remember {
        mutableStateOf(Offset.Unspecified)
    }

    val malePathString = stringResource(id = R.string.male_path)
    val femalePathString = stringResource(id = R.string.female_path)

    val malePath = remember {
        PathParser().parsePathString(malePathString).toPath()
    }
    val femalePath = remember {
        PathParser().parsePathString(femalePathString).toPath()
    }

    val malePathBounds = remember {
        malePath.getBounds()
    }
    val femalePathBounds = remember {
        femalePath.getBounds()
    }

    var maleTranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var femaleTranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var currentClickOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    val maleSelectionRadius = animateFloatAsState(
        targetValue = if(selectedGender is Gender.Male) 80f else 0f,
        animationSpec = tween(durationMillis = 500)
    )
    val femaleSelectionRadius = animateFloatAsState(
        targetValue = if(selectedGender is Gender.Female) 80f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = modifier
                .pointerInput(true) {
                    detectTapGestures {
                        val transformedMaleRect = Rect(
                            offset = maleTranslationOffset,
                            size = malePathBounds.size * pathScaleFactor
                        )
                        val transformedFemaleRect = Rect(
                            offset = femaleTranslationOffset,
                            size = femalePathBounds.size * pathScaleFactor
                        )
                        if(selectedGender !is Gender.Male && transformedMaleRect.contains(it)) {
                            currentClickOffset = it
                            selectedGender = Gender.Male
//                        onGenderSelected(Gender.Male)
                        } else if(selectedGender !is Gender.Female && transformedFemaleRect.contains(it)) {
                            currentClickOffset = it
                            selectedGender = Gender.Female
//                        onGenderSelected(Gender.Female)
                        }
                    }
                }
        ) {
            center = this.center

            maleTranslationOffset = Offset(
                x = center.x - malePathBounds.width * pathScaleFactor - distanceBetweenGenders.toPx() / 2f,
                y = center.y - pathScaleFactor * malePathBounds.height / 2f
            )
            femaleTranslationOffset = Offset(
                x = center.x + distanceBetweenGenders.toPx() / 2f,
                y = center.y - pathScaleFactor * femalePathBounds.height / 2f
            )

            val untransformedMaleClickOffset = if(currentClickOffset == Offset.Zero) {
                malePathBounds.center
            } else {
                (currentClickOffset - maleTranslationOffset) / pathScaleFactor
            }
            val untransformedFemaleClickOffset = if(currentClickOffset == Offset.Zero) {
                femalePathBounds.center
            } else {
                (currentClickOffset - femaleTranslationOffset) / pathScaleFactor
            }

            translate(
                left = maleTranslationOffset.x,
                top = maleTranslationOffset.y
            ) {
                scale(
                    scale = pathScaleFactor,
                    pivot = malePathBounds.topLeft
                ) {
                    drawPath(
                        path = malePath,
                        color = Color.LightGray
                    )
                    clipPath(
                        path = malePath
                    ) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = maleGradient,
                                center = untransformedMaleClickOffset,
                                radius = maleSelectionRadius.value + 1f
                            ),
                            radius = maleSelectionRadius.value,
                            center = untransformedMaleClickOffset
                        )
                    }
                }

            }
            translate(
                left = femaleTranslationOffset.x,
                top = femaleTranslationOffset.y
            ) {
                scale(
                    scale = pathScaleFactor,
                    pivot = femalePathBounds.topLeft
                ) {
                    drawPath(
                        path = femalePath,
                        color = Color.LightGray
                    )
                    clipPath(
                        path = femalePath
                    ) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = femaleGradient,
                                center = untransformedFemaleClickOffset,
                                radius = femaleSelectionRadius.value + 1
                            ),
                            radius = femaleSelectionRadius.value,
                            center = untransformedFemaleClickOffset
                        )
                    }
                }
            }

        }

        Column(
            modifier = Modifier.fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(onClick = {
                if (selectedGender == Gender.Male) {
                    navController.navigate(Screen.MacrosScreen.passId(id = 0))
                }else{
                    navController.navigate(Screen.MacrosScreen.passId(id = 1))
                }
            },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.LightGray

                ),
                modifier = Modifier
                    .border(
                        width = 5.dp,
                        brush = Brush.horizontalGradient(listOf(Color.Cyan, Color.Blue)),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .width(100.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent
                            ),
                            startX = 150f
                        )
                    )
            ) {
                Text(text = "Apply")
            }

        }

    }

}