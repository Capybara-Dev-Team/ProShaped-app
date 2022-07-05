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
                calories = "100 grams of Beef -> 332kcal",
                imageResourceId = R.drawable.beef
            ),
            PhotoData(
                calories = "100 grams of Blueberry -> 57kcal",
                imageResourceId = R.drawable.blueberry
            ),
            PhotoData(
                calories = "3 boiled Eggs -> 155kcal",
                imageResourceId = R.drawable.boiledeggs
            ),
            PhotoData(
                calories = "100 grams of Brocolli -> 34kcal",
                imageResourceId = R.drawable.brocolli
            ),
            PhotoData(
                calories = "100 grams of Mexican Mix -> 92kcal",
                imageResourceId = R.drawable.mexicanmix
            ),
            PhotoData(
                calories = "100 grams of Pancakes -> 227kcal",
                imageResourceId = R.drawable.pancakes
            ),
            PhotoData(
                calories = "100 grams of Pasta -> 131kcal",
                imageResourceId = R.drawable.pasta
            ),
            PhotoData(
                calories = "100 grams of White Rice -> 130kcal",
                imageResourceId = R.drawable.rice
            ),
            PhotoData(
                calories = "100 grams of Salmon -> 208kcal",
                imageResourceId = R.drawable.somon
            ),
            PhotoData(
                calories = "100 grams of Strawberry -> 32kcal",
                imageResourceId = R.drawable.strawberry
            ),
            PhotoData(
                calories = "100 grams of Dark Chocolate -> 500kcal",
                imageResourceId = R.drawable.darkchocolate
            ),
            PhotoData(
                calories = "14 almonds -> 100kcal",
                imageResourceId = R.drawable.almonds
            )
            //to add more add photo to res/drawable and then create a photoData like above
        )
    }
}