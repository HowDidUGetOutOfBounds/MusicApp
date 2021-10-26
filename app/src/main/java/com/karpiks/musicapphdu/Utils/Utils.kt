package com.karpiks.musicapphdu.Utils

import android.content.Context
import android.util.Log
import java.io.IOException
import java.io.InputStream


object Utils {
    /**
     * My logger to do faster logs
     * @author MrHDU
     * @param message - text to display
     * @param tag (optional) tag for logs
     */
    fun q(message: String, tag: String = "HDU") {
        Log.d(tag, "@ $tag: $message")
    }

    /**
     * Loads json-string stored locally
     */

    fun loadJSONFromAsset(context: Context, fileName: String): String? {
        var json: String? = null

        try {
            val ins: InputStream = context.assets.open(fileName)
            val size = ins.available()
            val buffer = ByteArray(size)
            ins.read(buffer)
            ins.close()
            json = buffer.toString(Charsets.UTF_8)
        } catch (e: IOException) {
            q("I dunno, you got IO in ${this.javaClass.simpleName}")
        }

        return json
    }


}