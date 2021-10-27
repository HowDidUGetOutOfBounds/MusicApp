package com.karpiks.musicapphdu.model

internal sealed class MainActivityState {
    data class SongStateSaved(
        val songUrl: String, val imgUrl: String,
        val currentTime: Int, val Id: Int
    ) :
        MainActivityState()

    object PlayPause : MainActivityState()
    object Next : MainActivityState()
    object Prev : MainActivityState()
}