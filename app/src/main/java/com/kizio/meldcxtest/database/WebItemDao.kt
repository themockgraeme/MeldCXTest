package com.kizio.meldcxtest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WebItemDao {
	@Query("SELECT * FROM web_items")
	fun getAll(): List<WebItem>

	@Query("SELECT * FROM web_items WHERE uid IN (:itemIds)")
	fun loadAllByIds(itemIds: IntArray): List<WebItem>
	
	@Insert
	fun insertAll(vararg webItems: WebItem)

	@Delete
	fun delete(webItem: WebItem)
}