package me.dio.cartaovisitas.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDAO {
    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCard: BusinessCard)

    @Delete(entity = BusinessCard::class)
    suspend fun delete(businessCard: BusinessCard)

    @Update(entity = BusinessCard::class)
    suspend fun update(businessCard: BusinessCard)
}