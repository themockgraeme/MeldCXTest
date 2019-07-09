package com.kizio.meldcxtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.kizio.meldcxtest.database.WebItemDatabase

class MainActivity : AppCompatActivity() {

	private var database: WebItemDatabase? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_main)

		database = Room.databaseBuilder(
			applicationContext,
			WebItemDatabase::class.java, "web_item"
		).build()
	}
}
