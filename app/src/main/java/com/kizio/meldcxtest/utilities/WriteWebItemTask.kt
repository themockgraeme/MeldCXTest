package com.kizio.meldcxtest.utilities

import android.content.Context
import android.os.AsyncTask
import com.kizio.meldcxtest.database.WebItem
import com.kizio.meldcxtest.database.WebItemDatabase

class WriteWebItemTask(context: Context) : AsyncTask<WebItem, Void, Void> () {
	private val database = WebItemDatabase.getInstance(context)

	override fun doInBackground(vararg webItems: WebItem?): Void? {
		for (webItem in webItems) {
			if (webItem != null) {
				database.webItemDao().insertAll(webItem)
			}
		}

		return null
	}
}
