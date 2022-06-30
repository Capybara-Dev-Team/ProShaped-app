package com.example.proshapedapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proshapedapp.ui.theme.ProShapedAppTheme
import kotlinx.coroutines.delay
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.example.proshapedapp.caloriesScreenPackage.GetRandomPhoto
import com.example.proshapedapp.caloriesScreenPackage.ImageCard
import com.example.proshapedapp.caloriesScreenPackage.PhotosData
import com.example.proshapedapp.gender.GenderPicker
import com.example.proshapedapp.settingsScreenPackage.ActivityLevel
import com.example.proshapedapp.settingsScreenPackage.Gender
import com.example.proshapedapp.settingsScreenPackage.Height
import com.example.proshapedapp.settingsScreenPackage.Weight
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProShapedAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Macros",
                                    route = "macros",
                                    icon = Icons.Filled.FoodBank
                                ),
                                BottomNavItem(
                                    name = "Calories",
                                    route = "calories",
                                    icon = Icons.Filled.NoFood
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("macros") {
            MacrosScreen(navController = navController)
        }
        composable("calories") {
            CaloriesScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
        composable("genderPicker") {
            GenderPicker(
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Cyan,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if(item.badgeCount > 0) {
                            BadgeBox(
                                badgeContent = {
                                    Text(text = item.badgeCount.toString())
                                }
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun MacrosScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    var textFieldState1 by remember{
        mutableStateOf("")
    }
    var textFieldState2 by remember{
        mutableStateOf("")
    }
    var textFieldState3 by remember{
        mutableStateOf("")
    }
    
    var age: Int
    var gender: String
    var weight: Int
    var height: Int
    var selectedLevel by remember{
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 30.dp,
                    vertical = 30.dp
                )
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Age", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = textFieldState1,
                label = {
                    Text(text = "Enter your age")
                },
                onValueChange = {
                    textFieldState1 = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.75f),
                textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )

            )
            //use textfieldstate in a try catch block to use the age later
            age = try{
                textFieldState1.toString().toInt()
            }catch (e: NumberFormatException){
                Log.d("exception","input string")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate("genderPicker")
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
                    .width(150.dp)
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
                Text(text = "Pick gender", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Weight", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = textFieldState2,
                label = {
                    Text(text = "Enter your weight(kg)")
                },
                onValueChange = {
                    textFieldState2 = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
//                    .border(
//                        width = 2.dp,
//                        brush = Brush.horizontalGradient(listOf(Color.Cyan,Color.Blue)),
//                        shape = RoundedCornerShape(10.dp)),
                ,
                textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
//                backgroundColor = Color.Transparent,
//                activeColor = Color.Transparent,
//                inactiveColor = Color.Transparent

            )

            weight = try{
                textFieldState2.toString().toInt()
            }catch (e: NumberFormatException){
                Log.d("exception","input string")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Height", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = textFieldState3,
                label = {
                    Text(text = "Enter your height(cm)")
                },
                onValueChange = {
                    textFieldState3 = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
//                    .border(
//                        width = 2.dp,
//                        brush = Brush.horizontalGradient(listOf(Color.Cyan,Color.Blue)),
//                        shape = RoundedCornerShape(10.dp)),
                ,
                textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
//                backgroundColor = Color.Transparent,
//                activeColor = Color.Transparent,
//                inactiveColor = Color.Transparent

            )

            height = try{
                textFieldState3.toString().toInt()
            }catch (e: NumberFormatException){
                Log.d("exception","input string")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Activity Level", fontSize = 18.sp)

            Spacer(modifier = Modifier.size(16.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    RadioButton(
                        selected = selectedLevel == ActivityLevel.sedentary,
                        onClick = {
                            selectedLevel = ActivityLevel.sedentary
                        },
                        colors = RadioButtonDefaults.colors(Color.Cyan)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.sedentary, fontSize = 12.sp)

                    Spacer(modifier = Modifier.size(16.dp))

                    RadioButton(
                        selected = selectedLevel == ActivityLevel.light,
                        onClick = {
                            selectedLevel = ActivityLevel.light
                        },
                        colors = RadioButtonDefaults.colors(Color.Green)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.light, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.size(16.dp))

                Row {
                    RadioButton(
                        selected = selectedLevel == ActivityLevel.moderate,
                        onClick = {
                            selectedLevel = ActivityLevel.moderate
                        },
                        colors = RadioButtonDefaults.colors(Color.Yellow)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.moderate, fontSize = 12.sp)

                    Spacer(modifier = Modifier.size(16.dp))

                    RadioButton(
                        selected = selectedLevel == ActivityLevel.high,
                        onClick = {
                            selectedLevel = ActivityLevel.high
                        },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.high, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row {
                Button(onClick = {
                    //implement calculation for cut
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
                        .width(80.dp)
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
                    Text(text = "CUT", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(14.dp))

                Button(onClick = {
                    //implement calculation for maintain
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
                        .width(110.dp)
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
                    Text(text = "MAINTAIN", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    //implement calculation for bulking
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
                        .width(80.dp)
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
                    Text(text = "BULK", fontSize = 14.sp)
                }
            }

            //change the padding and the size so that it fits nicely on the screen
            //implement goal cut/maintain/bulk buttons which will also reset the textStates to ""

        }


    }
}

//Composable for the calories screen
//  !!!  Feature done   !!!
@SuppressLint("UnrememberedMutableState")
@Composable
fun CaloriesScreen() {
    val scope = rememberCoroutineScope()
    val photosList = PhotosData.getData()
    var photoData = mutableStateOf(GetRandomPhoto(photosList).randomPhoto())

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //here we will create an image card

        var painter = painterResource(id = photoData.value.imageResourceId)
        var description = photoData.value.calories
        var title = photoData.value.calories

        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            ImageCard(
                painter = painter,
                contentDescription = description,
                title = title
            )
        }
        Button(onClick = {
            scope.launch {
                photoData.value = GetRandomPhoto(photosList).randomPhoto()
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
            Text(text = "Next")
        }

    }

}

@Composable
fun SettingsScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //!!! might need to use shared preferences to save the data
            //pass data between activities

            //!!! pass this to the macros screen later !!!
            val selectedGender = remember {
                mutableStateOf("")
            }

            Text(text = "Select Gender", fontSize = 18.sp)
            Spacer(modifier = Modifier.size(16.dp))

            Row {
                RadioButton(
                    selected = selectedGender.value == Gender.male,
                    onClick = {
                        selectedGender.value = Gender.male
                    },
                    colors = RadioButtonDefaults.colors(Color.Cyan)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(Gender.male)

                Spacer(modifier = Modifier.size(16.dp))

                RadioButton(
                    selected = selectedGender.value == Gender.female,
                    onClick = {
                        selectedGender.value = Gender.female
                    },
                    colors = RadioButtonDefaults.colors(Color.Magenta)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(Gender.female)

            }

            Spacer(modifier = Modifier.size(32.dp))

            //!!! pass this to the macros screen later !!!
            val selectedWeight = remember {
                mutableStateOf("")
            }

            Text(text = "Weight", fontSize = 18.sp)
            Spacer(modifier = Modifier.size(16.dp))

            Row {
                RadioButton(
                    selected = selectedWeight.value == Weight.pounds,
                    onClick = {
                        selectedWeight.value = Weight.pounds
                    },
                    colors = RadioButtonDefaults.colors(Color.Cyan)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(Weight.pounds)

                Spacer(modifier = Modifier.size(16.dp))

                RadioButton(
                    selected = selectedWeight.value == Weight.kilos,
                    onClick = {
                        selectedWeight.value = Weight.kilos
                    },
                    colors = RadioButtonDefaults.colors(Color.Cyan)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(Weight.kilos)

            }

            Spacer(modifier = Modifier.size(32.dp))

            //!!! pass this to the macros screen later !!!
            val selectedHeight = remember {
                mutableStateOf("")
            }

            Text(text = "Height", fontSize = 18.sp)
            Spacer(modifier = Modifier.size(16.dp))

            Row {
                RadioButton(
                    selected = selectedHeight.value == Height.feet,
                    onClick = {
                        selectedHeight.value = Height.feet
                    },
                    colors = RadioButtonDefaults.colors(Color.Cyan)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(Height.feet)

                Spacer(modifier = Modifier.size(16.dp))

                RadioButton(
                    selected = selectedHeight.value == Height.cm,
                    onClick = {
                        selectedHeight.value = Height.cm
                    },
                    colors = RadioButtonDefaults.colors(Color.Cyan)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(Height.cm)

            }

            Spacer(modifier = Modifier.size(32.dp))

            //!!! pass this to the macros screen later !!!
            val selectedLevel = remember {
                mutableStateOf("")
            }

            Text(text = "Activity Level", fontSize = 18.sp)
            Spacer(modifier = Modifier.size(16.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    RadioButton(
                        selected = selectedLevel.value == ActivityLevel.sedentary,
                        onClick = {
                            selectedLevel.value = ActivityLevel.sedentary
                        },
                        colors = RadioButtonDefaults.colors(Color.Cyan)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.sedentary)

                    Spacer(modifier = Modifier.size(16.dp))

                    RadioButton(
                        selected = selectedLevel.value == ActivityLevel.light,
                        onClick = {
                            selectedLevel.value = ActivityLevel.light
                        },
                        colors = RadioButtonDefaults.colors(Color.Green)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.light)
                }

                Spacer(modifier = Modifier.size(16.dp))

                Row {
                    RadioButton(
                        selected = selectedLevel.value == ActivityLevel.moderate,
                        onClick = {
                            selectedLevel.value = ActivityLevel.moderate
                        },
                        colors = RadioButtonDefaults.colors(Color.Yellow)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.moderate)

                    Spacer(modifier = Modifier.size(16.dp))

                    RadioButton(
                        selected = selectedLevel.value == ActivityLevel.high,
                        onClick = {
                            selectedLevel.value = ActivityLevel.high
                        },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(ActivityLevel.high)
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = {
                    //implement
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

            //might add a language choice
        }

    }

}

@Composable
fun SplashScreen(navController: NavHostController){
    val scale = remember{
        Animatable(0f)

    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("macros")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.capybara),
//            contentDescription = "Logo",
//            modifier = Modifier.scale(scale.value)
//        )
    }
}