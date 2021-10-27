package com.karpiks.musicapphdu.view

import android.content.res.Configuration
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karpiks.musicapphdu.MyApplication
import com.karpiks.musicapphdu.databinding.ActivityMainBinding
import com.karpiks.musicapphdu.model.MainActivityState
import com.karpiks.musicapphdu.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = MainActivityViewModel ((application as MyApplication).localDataRepository)

        viewModel.mainActivityState!!.observe(this) { state ->
            when (state) {
                is MainActivityState.SongStateSaved -> {
                    //save data in VM to restore when config changes
                }
                MainActivityState.Next -> {
                    viewModel.playNext()
                }
                MainActivityState.PlayPause -> {
                    viewModel.playPause()
                }
                MainActivityState.Prev -> {
                    viewModel.playPrev()
                }
            }
        }

        binding.next.setOnClickListener {
            viewModel.mainActivityState?.value = MainActivityState.Next
        }
        binding.prev.setOnClickListener {
            viewModel.mainActivityState?.value = MainActivityState.Prev
        }
        binding.playPause.setOnClickListener {
            viewModel.mainActivityState?.value = MainActivityState.PlayPause
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }
}