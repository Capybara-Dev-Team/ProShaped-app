package com.example.proshapedapp.caloriesScreenPackage

class GetRandomPhoto(val photosList: ArrayList<PhotoData>){
    fun randomPhoto(): PhotoData{
        return photosList.random()
    }
}