package com.danlibs.businesscard

import android.app.Application
import com.danlibs.businesscard.data.AppDatabase
import com.danlibs.businesscard.data.BusinessCardRepository

class App : Application() {
    private val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy {BusinessCardRepository(database.businessDao())}
}