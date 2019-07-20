package com.kizio.meldcxtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WebItem::class), version = 1)
abstract class WebItemDatabase : RoomDatabase() {
	companion object {
		private var database: WebItemDatabase? = null

		/**
		 * I'm sure that there's a neater way of doing this, but I couldn't figure out the correct Voodoo for having a
		 * non-null value for a potentially null variable!
		 */
		fun getInstance(context: Context) : WebItemDatabase {
			return database?.let {
				it
			} ?: run {
				val result = Room.databaseBuilder(
					context.applicationContext,
					WebItemDatabase::class.java, "web_item"
				).build()

				database = result

				result
			}
		}
	}

	abstract fun webItemDao(): WebItemDao
}
