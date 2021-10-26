package com.karpiks.musicapphdu.model

internal sealed class MainActivityState {
    data class SongState(val songUrl: String, val imgUrl: String, val currentTime: Int) :
        MainActivityState()
}