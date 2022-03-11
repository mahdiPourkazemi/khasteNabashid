package ir.mohsenafshar.apps.mkbarchitecture.data.local.db

import androidx.room.*
import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user  JOIN table_hobie AS h ON user.id == h.user_id")
    fun getAllUserWithHobbies():Map<User,List<Hobie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)
}