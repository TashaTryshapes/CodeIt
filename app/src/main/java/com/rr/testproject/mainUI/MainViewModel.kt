package com.rr.testproject.mainUI

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradeep.roomdb.roomDb.PostDatabase
import com.rr.testproject.data.UserData
import com.rr.testproject.data.UserIdData.UserIdData
import com.rr.testproject.pojo.NoteRepo
import com.rr.testproject.services.APIInterface
import com.rr.testproject.services.RetofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("StaticFieldLeak") class MainViewModel constructor(private val activity: Activity) : ViewModel() {

    val allpost: LiveData<List<UserData>>
    val userIdData = MutableLiveData<UserIdData>()
    val noteRepo: NoteRepo

    val userid = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val body = MutableLiveData<String>()
    var screen = ""
    var refresh = ""

    val adapterMain: AdapterMain

    init {
        val dao = PostDatabase.getDatabase(activity).notesDao()
        noteRepo = NoteRepo(dao)
        allpost = noteRepo.allNotes
        adapterMain = AdapterMain()
    }

    fun getUserList() = viewModelScope.launch {

        Dispatchers.IO
        val userList = RetofitAPI.getInstance().create(APIInterface::class.java)
        val result = userList.getUsersList()
        if (result.body() != null) {
            addNotes(result.body()!!)
        }
    }

    fun idData() = viewModelScope.launch {
        val userid = id.value.toString()
        Dispatchers.IO
        val userList = RetofitAPI.getInstance().create(APIInterface::class.java)
        val result = userList.userData(userid)
        if (result.body() != null) {
            userIdData.postValue(result.body())
        }
    }

    fun addNotes(note: List<UserData>) = viewModelScope.launch {
        Dispatchers.IO
        noteRepo.insert(note)
        adapterMain.setData(note)
        refresh="NotRefresh"
    }

    fun deleteNoteAll(note: List<UserData>) = viewModelScope.launch(Dispatchers.IO) {
        noteRepo.deleteAll(note)
    }

    fun deleteNoteSingle(note: UserData) = viewModelScope.launch(Dispatchers.IO) {
        noteRepo.deleteSingle(note)
    }

}