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
            )
            //to add more add photo to res/drawable and then create a photoData like above
        )
    }
}