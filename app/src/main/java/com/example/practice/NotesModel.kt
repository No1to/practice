package com.example.practice

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity/*(tableName = "note-table")*/
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int? = null,
    var title: String? = null,
    var description: String? = null
) : Serializable
