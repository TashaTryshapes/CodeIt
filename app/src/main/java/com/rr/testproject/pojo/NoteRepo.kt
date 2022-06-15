package com.rr.testproject.pojo

import androidx.lifecycle.LiveData
import com.pradeep.roomdb.roomDb.PostDoa
import com.rr.testproject.data.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteRepo(private val postDao: PostDoa) {

    //for getting the notes data
    val allNotes:LiveData<List<UserData>> = postDao.getAllNotes()

    //for inserting the data
    suspend fun insert(note: List<UserData>){
        postDao.insert(note)
    }

    //for deleting All the data
    suspend fun deleteAll(note: List<UserData>){
        postDao.delete(note)
    }
    //for deleting Single data
    suspend fun deleteSingle(note: UserData){
        postDao.deleteOne(note)
    }

}