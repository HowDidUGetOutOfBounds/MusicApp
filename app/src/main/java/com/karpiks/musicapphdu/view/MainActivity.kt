package com.karpiks.musicapphdu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.karpiks.musicapphdu.MyApplication
import com.karpiks.musicapphdu.R
import com.karpiks.musicapphdu.Utils.Utils.q
import com.karpiks.musicapphdu.model.MainActivityState
import com.karpiks.musicapphdu.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val localRepo = (application as MyApplication).localDataRepository
        setContentView(R.layout.activity_main)
        

        viewModel.mainActivityState!!.observe(this) { state ->
            when (state) {
                is MainActivityState.SongState -> {
                    //q(state.currentTime.toString())
                }
            }
        }
    }
}