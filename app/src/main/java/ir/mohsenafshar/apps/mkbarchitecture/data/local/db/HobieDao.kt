package ir.mohsenafshar.apps.mkbarchitecture.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie

@Dao
interface HobieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg hobie:Hobie)
}