package com.example.luis.cache.beers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BeersConstants.TABLE_NAME)
data class BeersEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date:String
)