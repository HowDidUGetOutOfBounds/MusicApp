package com.karpiks.musicapphdu.model

import android.content.Context
import com.karpiks.musicapphdu.Utils.Utils.loadJSONFromAsset

class LocalRepo(val context: Context) {
    fun getData(fileName: String): String?{
        return loadJSONFromAsset(context, fileName)
    }
}