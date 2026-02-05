package com.example.mobile_computing_hw.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Profile(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "profileImage") val profileImage: String?
)

const val PROFILE_ID = 0

@Dao
interface ProfileDao {

    @Query("SELECT * FROM Profile WHERE uid = :id")
    fun getProfile(id: Int = PROFILE_ID): Profile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: Profile)
}