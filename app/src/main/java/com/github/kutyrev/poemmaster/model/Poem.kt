package com.github.kutyrev.poemmaster.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poems")
data class Poem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var text: String
)