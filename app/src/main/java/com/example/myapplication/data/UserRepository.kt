package com.example.myapplication.data

class UserRepository {

    fun getWelcomeMessage(): String {
        return "Jetpack Compose MVVM 예제입니다."
    }

    fun getUserDescription(name: String): String {
        return "$name 님의 상세 페이지입니다."
    }
}