package com.example.practice.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practice.NotesModel

@Dao
interface NoteDao {

    @Insert
    fun insert(note: NotesModel)

    @Delete
    fun delete(note: NotesModel)

    @Update
    fun update(note: NotesModel)

    @Query("SELECT * FROM NotesModel ORDER BY noteId DESC")
    fun getAll(): List<NotesModel>
}