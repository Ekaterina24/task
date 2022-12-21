package com.example.retrofitwithpost

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private var recyclerListData: MutableLiveData<List<UserItem>?> = MutableLiveData()

    private val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)

    fun getUserListObservable(): MutableLiveData<List<UserItem>?> {
        return recyclerListData
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun getUsersList() {
        viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {

            val call = retroInstance.getUsersList()

            if (call.isSuccessful) {
                recyclerListData.postValue(call.body())
            } else {
                recyclerListData.postValue(null)
            }

        }
    }

}