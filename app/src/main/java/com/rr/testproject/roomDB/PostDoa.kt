package com.pradeep.roomdb.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.rr.testproject.data.UserData


@Dao
interface PostDoa {

    // while inseting data to db,if there is data with same id it will ignore
    @Insert(onConflict = IGNORE)
    suspend fun insert(quotes: List<UserData>)

    // for reading the db
    @Query("Select * from quotesTable order by user ASC")
    //order by id ASC- means we want to arrange the table with by its id ascending order
    fun getAllNotes(): LiveData<List<UserData>>

    @Delete
    suspend fun delete(quotes: List<UserData>)

    @Delete
    suspend fun deleteOne(quotes: UserData)

}