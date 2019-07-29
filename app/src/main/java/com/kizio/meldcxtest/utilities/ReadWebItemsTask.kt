package com.kizio.meldcxtest.utilities

import android.os.AsyncTask
import com.kizio.meldcxtest.HistoryActivity
import com.kizio.meldcxtest.database.WebItem
import com.kizio.meldcxtest.database.WebItemDatabase
import java.lang.ref.WeakReference

class ReadWebItemsTask(activity: HistoryActivity) : AsyncTask<Void, Void, List<WebItem>>() {
	private val database = WebItemDatabase.getInstance(activity)

	private val activityReference = WeakReference<HistoryActivity> (activity)

	override fun doInBackground(vararg p0: Void?): List<WebItem> {
		return database.webItemDao().getAll()
	}

	override fun onPostExecute(result: List<WebItem>?) {
		val activity = activityReference.get()

		if (activity != null && result != null) {
			activity.setWebItems(result)
		}
	}
}
