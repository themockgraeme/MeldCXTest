package com.kizio.meldcxtest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WebItem::class), version = 1)
abstract class WebItemDatabase : RoomDatabase() {
	abstract fun webItemDao(): WebItemDao
}
