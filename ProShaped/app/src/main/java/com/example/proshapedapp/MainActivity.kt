package com.example.proshapedapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.proshapedapp.ui.theme.ProShapedAppTheme
import kotlinx.coroutines.delay
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.proshapedapp.caloriesScreenPackage.ActivityLevel
import com.example.proshapedapp.caloriesScreenPackage.GetRandomPhoto
import com.example.proshapedapp.caloriesScreenPackage.ImageCard
import com.example.proshapedapp.caloriesScreenPackage.PhotosData
import com.example.proshapedapp.workoutScreenPackage.AddWorkout
import com.example.proshapedapp.workoutScreenPackage.DisplayWorkout
import com.example.proshapedapp.workoutScreenPackage.backPackage.BackScreen
import com.example.proshapedapp.workoutScreenPackage.bicepsPackage.BicepsScreen
import com.example.proshapedapp.workoutScreenPackage.chestPackage.ChestScreen
import com.example.proshapedapp.workoutScreenPackage.deltoidsPackage.DeltoidsScreen
import com.example.proshapedapp.workoutScreenPackage.legsPackage.LegsScreen
import com.example.proshapedapp.workoutScreenPackage.tricepsPackage.TricepsScreen
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProShapedAppTheme {
                /*MobileAds.initialize(this@MainActivity)*/
                val navController = rememberNavController()
                //ca-app-pub-5116754338374159/1168590671


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
                                    name = "Workout",
                                    route = "workout",
                                    icon = Icons.Filled.FitnessCenter
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        //change background default Color
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
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.MacrosScreen.route)
         {
            MacrosScreen(navController = navController)
        }
        composable(Screen.CaloriesScreen.route) {
            CaloriesScreen()
        }
        composable(Screen.WorkoutScreen.route) {
            WorkoutScreen(navController = navController)
        }

        composable(Screen.ChestScreen.route) {
            ChestScreen(navController = navController)
        }
        composable(
            route = Screen.DisplayScreen.route,
                arguments = listOf(
                    navArgument("name1"){
                        type = NavType.StringType
                        nullable = true
                    }
                )
        ){ entry ->
            DisplayWorkout(navController = navController, name = entry.arguments?.getString("name1"))

        }
        composable(
            route = Screen.AddScreen.route,
            arguments = listOf(
                navArgument("name2"){
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){ entry->
            AddWorkout(navController = navController, name = entry.arguments?.getString("name2"))
        }
        
        composable(Screen.DeltoidsScreen.route) {
            DeltoidsScreen(navController = navController)
        }
        composable(Screen.TricepsScreen.route) {
            TricepsScreen(navController = navController)
        }
        composable(Screen.BackScreen.route) {
            BackScreen(navController = navController)
        }
        composable(Screen.BicepsScreen.route) {
            BicepsScreen(navController = navController)
        }
        composable(Screen.LegsScreen.route) {
            LegsScreen(navController = navController)
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
    var gender= "male"

    var weight: Int
    var height: Int
    var selectedLevel by remember{
        mutableStateOf("")
    }
    var calories by remember{
        mutableStateOf("")
    }
    var calNr: Double = 0.0
    var fat = 0
    var protein = 0
    var carbs = 0

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
            horizontalAlignment = CenterHorizontally,
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
            age = try{
                textFieldState1.toInt()
            }catch (e: NumberFormatException){
                Log.d("exception","input string")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row() {
                Button(onClick = {
                    gender = "male"
                },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = Color.LightGray

                    ),
                    modifier = Modifier
                        .border(
                            width = 5.dp,
                            brush = Brush.horizontalGradient(listOf(Color.Cyan, Color.Blue)),
                            shape = RoundedCornerShape(0.dp)
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
                    Text(text = "Male", fontSize = 18.sp)
                }
                Button(onClick = {
                    gender = "female"
                },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = Color.LightGray

                    ),
                    modifier = Modifier
                        .border(
                            width = 5.dp,
                            brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Cyan)),
                            shape = RoundedCornerShape(0.dp)
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
                    Text(text = "Female", fontSize = 18.sp)
                }
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
                textFieldState2.toInt()
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
                textFieldState3.toInt()
            }catch (e: NumberFormatException){
                Log.d("exception","input string")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Activity Level", fontSize = 18.sp)

            Spacer(modifier = Modifier.size(16.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = CenterHorizontally,
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
                    if (textFieldState1 != "" && textFieldState2 != "" && textFieldState3 != "") {
                        calNr = 0.0

                        if (gender == "male"){
                            calNr = 66.5
                        }else if (gender == "female"){
                            calNr = 655.1
                        }

                        if (gender == "male") {
                            calNr += 13.75 * weight
                        } else if (gender == "female") {
                            calNr += 9.563 * weight
                        }

                        if (gender == "male") {
                            calNr += 5.003 * height
                        } else if (gender == "female") {
                            calNr += 1.85 * height
                        }

                        if (gender == "male") {
                            calNr -= 6.75 * age
                        } else if (gender == "female") {
                            calNr -= 4.676 * age
                        }


                        if (selectedLevel == ActivityLevel.sedentary) {
                            calNr *= 1.2
                            calNr -= (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.light) {
                            calNr *= 1.375
                            calNr -= (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.moderate) {
                            calNr *= 1.55
                            calNr -= (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.high) {
                            calNr *= 1.725
                            calNr -= (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        }

                        fat = ((calNr*0.3)/9).roundToInt()
                        protein = ((calNr*0.3)/4).roundToInt()
                        carbs = ((calNr*0.4)/4).roundToInt()
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
                    Text(text = "LOSE", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(14.dp))

                Button(onClick = {
                    if (textFieldState1 != "" && textFieldState2 != "" && textFieldState3 != ""){
                        calNr = 0.0

                        if (gender == "male"){
                            calNr = 66.5
                        }else if (gender == "female"){
                            calNr = 655.1
                        }

                        if (gender == "male") {
                            calNr += 13.75 * weight
                        } else if (gender == "female") {
                            calNr += 9.563 * weight
                        }

                        if (gender == "male") {
                            calNr += 5.003 * height
                        } else if (gender == "female") {
                            calNr += 1.85 * height
                        }

                        if (gender == "male") {
                            calNr -= 6.75 * age
                        } else if (gender == "female") {
                            calNr -= 4.676 * age
                        }


                        if (selectedLevel == ActivityLevel.sedentary) {
                            calNr *= 1.2
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.light) {
                            calNr *= 1.375
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.moderate) {
                            calNr *= 1.55
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.high) {
                            calNr *= 1.725
                            calories = calNr.roundToInt().toString()
                        }

                        fat = ((calNr*0.3)/9).roundToInt()
                        protein = ((calNr*0.3)/4).roundToInt()
                        carbs = ((calNr*0.4)/4).roundToInt()

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
                    if (textFieldState1 != "" && textFieldState2 != "" && textFieldState3 != "") {
                        calNr = 0.0

                        if (gender == "male"){
                            calNr = 66.5
                        }else if (gender == "female"){
                            calNr = 655.1
                        }

                        if (gender == "male") {
                            calNr += 13.75 * weight
                        } else if (gender == "female") {
                            calNr += 9.563 * weight
                        }

                        if (gender == "male") {
                            calNr += 5.003 * height
                        } else if (gender == "female") {
                            calNr += 1.85 * height
                        }

                        if (gender == "male") {
                            calNr -= 6.75 * age
                        } else if (gender == "female") {
                            calNr -= 4.676 * age
                        }


                        if (selectedLevel == ActivityLevel.sedentary) {
                            calNr *= 1.2
                            calNr += (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.light) {
                            calNr *= 1.375
                            calNr += (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.moderate) {
                            calNr *= 1.55
                            calNr += (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        } else if (selectedLevel == ActivityLevel.high) {
                            calNr *= 1.725
                            calNr += (calNr * 0.2)
                            calories = calNr.roundToInt().toString()
                        }

                        fat = ((calNr*0.3)/9).roundToInt()
                        protein = ((calNr*0.3)/4).roundToInt()
                        carbs = ((calNr*0.4)/4).roundToInt()
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
                    Text(text = "GAIN", fontSize = 14.sp)
                }
            }

            //change the names from cut/maintain/bulk
            //For women, it's: BMR = 655.1 + (9.563 * weight in kg) + (1.850 * height in cm) - (4.676 * age)
            //For men, the formula is: BMR = 66.5 + (13.75 * weight in kg) + (5.003 * height in cm) - (6.75 * age).
            /*
            Sedentary (little or no exercise): calories = BMR × 1.2;
            Lightly active (light exercise/sports 1-3 days/week): calories = BMR × 1.375;
            Moderately active (moderate exercise/sports 3-5 days/week): calories = BMR × 1.55;
            Very active (hard exercise/sports 6-7 days a week): calories = BMR × 1.725;
             */
            //20% cut 20% bulk
            //implement goal cut/maintain/bulk buttons which will also reset the textStates to ""
            //30% fat 30% protein 40% carbs

            Spacer(modifier = Modifier.size(100.dp))


            ExpandableCard(
                title = "$calories calories",
                description = "You need to eat " + fat + "g of fat, " +
                        protein + "g of protein and " + carbs + "g of carbs"
            )


            Text(text = "")
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
        horizontalAlignment = CenterHorizontally
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
fun WorkoutScreen(navController: NavHostController) {
    // kgs + reps per use a different section for every exercise where you can add
    //every time you want how many reps and kgs you've done, add a section that shows your all time prs
    //make a screen for each exercise

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate("chest_screen")
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
            Text(text = "Chest", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.size(32.dp))

        Button(onClick = {
            navController.navigate("deltoids_screen")
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
            Text(text = "Deltoids", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.size(32.dp))

        Button(onClick = {
            navController.navigate("triceps_screen")
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
            Text(text = "Triceps", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.size(32.dp))

        Button(onClick = {
            navController.navigate("back_screen")
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
            Text(text = "Back", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.size(32.dp))

        Button(onClick = {
            navController.navigate("biceps_screen")
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
            Text(text = "Biceps", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.size(32.dp))

        Button(onClick = {
            navController.navigate("legs_screen")
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
            Text(text = "Legs", fontSize = 18.sp)
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
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_round),
            contentDescription = "Logo",
            modifier = Modifier.scale(10*scale.value)
        )
    }
}

