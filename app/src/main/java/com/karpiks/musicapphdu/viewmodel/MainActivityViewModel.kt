package com.karpiks.musicapphdu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karpiks.musicapphdu.Utils.Utils.q
import com.karpiks.musicapphdu.model.MainActivityState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class MainActivityViewModel : ViewModel() {
    private var _mainActivityState: MutableLiveData<MainActivityState> = MutableLiveData(MainActivityState.SongState("", "", 0))
    var mainActivityState: LiveData<MainActivityState>?= null
        get() = _mainActivityState

    init {
        //prove working of this part
        viewModelScope.launch {
            for(data in 1 .. 10) {
                delay(1000)
                _mainActivityState.value = MainActivityState.SongState("", "", data)
            }
        }
    }

    fun doSomething() {
        q( "doSomething: I'm useful!")
    }

}