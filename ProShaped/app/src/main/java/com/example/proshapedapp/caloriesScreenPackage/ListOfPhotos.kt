package com.example.proshapedapp.caloriesScreenPackage

import android.provider.ContactsContract
import com.example.proshapedapp.R

object PhotosData{
    fun getData(): ArrayList<PhotoData>{
        return arrayListOf(
            PhotoData(
                calories = "Chicken Breast 100g -> 165kcal",
                imageResourceId = R.drawable.chickenbreast
            ),
            PhotoData(
                calories = "1 medium Banana -> 105kcal",
                imageResourceId = R.drawable.banana
            ),
            PhotoData(
                calories = "1 medium Avocado -> 234kcal",
                imageResourceId = R.drawable.avocado
            ),
            PhotoData(
                calories = "1 tbsp(16g) Peanut Butter -> 94kcal",
                imageResourceId = R.drawable.peanutbutter
            ),
            PhotoData(
                calories = "1 cup(81g) Oats -> 307kcal",
                imageResourceId = R.drawable.oats
            ),
            PhotoData(
                calories = "beef meat",
                imageResourceId = R.drawable.beef
            )
            //to add more add photo to res/drawable and then create a photoData like above
        )
    }
}