package com.hva.madlevel5task1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "noteTable")
data class Note(
    val title: String? = null,
    val lastUpdated: Date? = null,
    val text: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
    )
