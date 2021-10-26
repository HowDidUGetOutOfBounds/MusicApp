package com.karpiks.musicapphdu.Utils

import android.util.Log

object Utils {
    /**
     * My logger to do faster logs
     * @author MrHDU
     * @param message - text to display
     * @param tag (optional) tag for logs
     */
    fun q(message: String, tag: String = "HDU")
    {
        Log.d(tag, "@ $tag: $message")
    }

    /**
     * Loads json-array stored locally
     */

    fun loadJSONFromAsset(){

    }


}