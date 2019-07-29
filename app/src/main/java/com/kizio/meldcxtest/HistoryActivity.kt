package com.kizio.meldcxtest

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
		super.onCreate(savedInstanceState, persistentState)

		setContentView(R.layout.activity_history)

		delegate.setSupportActionBar(history_toolbar)
	}
}
