package com.kizio.meldcxtest

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.kizio.meldcxtest.database.WebItem
import com.kizio.meldcxtest.utilities.ReadWebItemsTask
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

	private var task: ReadWebItemsTask? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_history)

		task = ReadWebItemsTask(this)

		task?.execute()
	}

	fun setWebItems(items: List<WebItem>) {
		val adapter = ArrayAdapter<WebItem> (this, R.layout.list_item, items)

		list_view.adapter = adapter
	}
}
