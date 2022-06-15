package com.pradeep.roomdb.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rr.testproject.data.UserData

@Database(entities = arrayOf(UserData::class), version = 1, exportSchema = false)

abstract class PostDatabase : RoomDatabase(){

    abstract fun notesDao(): PostDoa

    companion object{

        @Volatile
        private var INSTANCE: PostDatabase?=null

        fun getDatabase(contect: Context): PostDatabase{

            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(contect.applicationContext,
                PostDatabase::class.java,
                "post_database").build()
                INSTANCE=instance
                instance
            }
        }
    }


}