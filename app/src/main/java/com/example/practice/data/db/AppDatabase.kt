package com.example.practice.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practice.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}