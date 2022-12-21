package com.example.retrofitwithpost

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class CreateNewUserViewModel: ViewModel() {

    private var createNewUserLiveData: MutableLiveData<ResponseItem?> = MutableLiveData()
//    var loadUserData: MutableLiveData<UserItem?> = MutableLiveData()
//    var deleteUserLiveData: MutableLiveData<UserItem?> = MutableLiveData()

    private val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)

    fun getCreateNewUserObservable():MutableLiveData<ResponseItem?> {
        return createNewUserLiveData
    }

//    fun getDeleteUserObservable():MutableLiveData<UserItem?> {
//        return deleteUserLiveData
//    }
//
//    fun getLoadUserObservable():MutableLiveData<UserItem?> {
//        return loadUserData
//    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun createUser(user: ResponseBody) {
        Log.d("@@@", "start: ")
        viewModelScope.launch(Dispatchers.Default  + coroutineExceptionHandler) {

            val call = retroInstance.createUser(user)

            if (call.isSuccessful) {
                createNewUserLiveData.postValue(call.body())
            } else {
                createNewUserLiveData.postValue(null)
            }
        }
    }

//    fun updateUser(event_id: RequestBody, user: UserItem) {
//        viewModelScope.launch(Dispatchers.Default) {
//            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
////            val call = retroInstance.updateUser(event_id, user)
////            if (call.isSuccessful) {
////
////                createNewUserLiveData.postValue(call.body())
////            } else {
////                createNewUserLiveData.postValue(null)
////            }
//        }
//    }
//
//    fun deleteUser(user_id: String?) {
//        viewModelScope.launch(Dispatchers.Default) {
//            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
//            val call = retroInstance.deleteUser(user_id!!)
//
//            if (call.isSuccessful) {
//                deleteUserLiveData.postValue(call.body())
//            } else {
//                deleteUserLiveData.postValue(null)
//            }
//        }
//    }
//
//    fun getUserData(user_id: String?) {
//        viewModelScope.launch(Dispatchers.Default) {
//            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
//            val call = retroInstance.getUser(user_id!!)
//            if (call.isSuccessful) {
//                loadUserData.postValue(call.body())
//            } else {
//                loadUserData.postValue(null)
//            }
//        }
//    }
}