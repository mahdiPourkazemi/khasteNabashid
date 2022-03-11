package ir.mohsenafshar.apps.mkbarchitecture.data.local.db

import androidx.room.*
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)
}