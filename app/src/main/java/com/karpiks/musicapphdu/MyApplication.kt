package com.karpiks.musicapphdu

import android.app.Application
import com.karpiks.musicapphdu.model.LocalRepo

class MyApplication : Application() {
    open val localDataRepository by lazy {
        LocalRepo(applicationContext)
    }
}