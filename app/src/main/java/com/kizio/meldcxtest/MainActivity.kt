package com.kizio.meldcxtest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.kizio.meldcxtest.database.WebItemDatabase
import kotlinx.android.synthetic.main.activity_main.*

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

	public fun onClickGo(view: View) {
		val url = web_address.text.toString()

		web_view.loadUrl(url)
	}

}
