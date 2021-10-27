package com.karpiks.musicapphdu.viewmodel

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karpiks.musicapphdu.Utils.Utils.q
import com.karpiks.musicapphdu.model.LocalRepo
import com.karpiks.musicapphdu.model.MainActivityState
import com.karpiks.musicapphdu.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.lang.Exception

internal class MainActivityViewModel(val localDataRepository: LocalRepo) : ViewModel() {
    private var _mainActivityState: MutableLiveData<MainActivityState> =
        MutableLiveData(MainActivityState.SongStateSaved("", "", 0, 0))
    var mainActivityState: MutableLiveData<MainActivityState>? = null
        get() = _mainActivityState

    var loadedSongs: ArrayList<Song> = ArrayList()
    var mediaPlayer: MediaPlayer? = null
    var playbackPosition = 0
    var isPlaying = false
    var currentSongId = 0

    init {
        val data = getJsonStringFromAsset("songs.json")
        loadedSongs = parseJsonSongs(data)
    }

    private fun getJsonStringFromAsset(fileName: String): String {
        return localDataRepository.getData(fileName) ?: "Reading json error"
    }

    private fun parseJsonSongs(jsonSongs: String): ArrayList<Song> {
        val songsList: ArrayList<Song> = ArrayList()
        val inputData = JSONArray(jsonSongs)

        for (i in 0 until inputData.length()) {
            val jsonObject = inputData.getJSONObject(i)

            val title = jsonObject.getString("title")
            val artist = jsonObject.getString("artist")
            val bitmapUri = jsonObject.getString("bitmapUri")
            val trackUri = jsonObject.getString("trackUri")
            val duration = jsonObject.getInt("duration")

            songsList.add(Song(title, artist, bitmapUri, trackUri, duration))
        }

        return songsList
    }

    fun playNext() {
        if (loadedSongs.size > (currentSongId + 1)) {
            currentSongId++
            pauseAudio(nextSong = true)
            playAudio()
        }
    }

    fun playPrev() {
        if (0 <= (currentSongId - 1)) {
            currentSongId--
            pauseAudio(prevSong = true)
            playAudio()
        }
    }

    fun playPause() {
        if (isPlaying) {
            pauseAudio()
        } else {
            playAudio()
        }
    }

    private fun pauseAudio(nextSong: Boolean = false, prevSong: Boolean = false) {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            playbackPosition = if (nextSong || prevSong) {
                0
            } else {
                mediaPlayer!!.currentPosition
            }
            isPlaying = false
        }
    }

    private fun playAudio() {

        killMediaPlayer()

        q(loadedSongs[currentSongId].trackUri)

        mediaPlayer = MediaPlayer()
        mediaPlayer!!.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(loadedSongs[currentSongId].trackUri)
            prepare() // might take long! (for buffering, etc)
            seekTo(playbackPosition)
            start()
        }
        isPlaying = true


    }

    private fun killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer!!.stop()
                mediaPlayer!!.reset()
                mediaPlayer!!.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}