package me.dio.cartaovisitas

import android.app.Application
import me.dio.cartaovisitas.data.AppDatabase
import me.dio.cartaovisitas.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDAO()) }
}