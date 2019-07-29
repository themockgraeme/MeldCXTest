package com.kizio.meldcxtest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.kizio.meldcxtest.database.WebItem
import com.kizio.meldcxtest.utilities.WriteWebItemTask
import com.kizio.meldcxtest.web.MeldWebClient
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * The original design used a single app with a pair of fragments, but I simplified it to a pair of activities.
 */
class MainActivity : AppCompatActivity() {

	companion object {
		private const val HTTP = "http://"
		private const val HTTPS = "https://"

		fun getUrlWithHttp(url: String?) : String? {
			return if (url != null && !url.startsWith(HTTP, true) && !url.startsWith(HTTPS, true)) {
				HTTP + url
			} else {
				url
			}
		}
	}

	@SuppressLint("SetJavaScriptEnabled")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_main)

		delegate.setSupportActionBar(web_toolbar)

		web_view.settings.javaScriptEnabled = true
		web_view.webViewClient = MeldWebClient()

		web_address.setOnEditorActionListener { textView, actionId, _ ->
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				loadUrl(textView.text.toString())
			}

			true
		}
	}

	fun onGo(@Suppress("UNUSED_PARAMETER") view: View) {
		loadUrl(web_address.text.toString())
	}

	fun onCapture(@Suppress("UNUSED_PARAMETER") view: View) {
		val webItem = WebItem(web_view.url, Date(), getWebViewAsBitmap())
		val task = WriteWebItemTask(this)

		task.execute(webItem)
	}

	fun onHistory(@Suppress("UNUSED_PARAMETER") view: View) {
		val intent = Intent(this, HistoryActivity::class.java)

		startActivity(intent)
	}

	/**
	 * Renders the web view into a [Bitmap]. This code was copied from:
	 *
	 * https://dev.to/pranavpandey/android-create-bitmap-from-a-view-3lck
	 */
	private fun getWebViewAsBitmap() : Bitmap {
		val bitmap = Bitmap.createBitmap(web_view.measuredWidth, web_view.measuredHeight, Bitmap.Config.ARGB_8888)
		val canvas = Canvas(bitmap)

		web_view.background?.draw(canvas)
		web_view.draw(canvas)

		return bitmap
	}

	private fun loadUrl(url: String?) {
		val urlToLoad = getUrlWithHttp(url)

		web_view.loadUrl(urlToLoad)
	}
}
