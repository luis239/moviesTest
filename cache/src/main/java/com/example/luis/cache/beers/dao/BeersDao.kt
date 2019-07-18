package com.example.luis.cache.beers.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.luis.cache.beers.BeersConstants
import com.example.luis.cache.beers.BeersEntity
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
abstract class BeersDao {

    @Query(BeersConstants.GET_DATE)
    abstract fun getDate(): Maybe<BeersEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertInfo(beersEntity: BeersEntity):Completable


}
