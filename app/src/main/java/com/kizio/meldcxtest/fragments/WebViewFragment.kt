package com.kizio.meldcxtest.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kizio.meldcxtest.R
import com.kizio.meldcxtest.database.WebItem
import com.kizio.meldcxtest.utilities.WriteWebItemTask
import com.kizio.meldcxtest.web.MeldWebClient
import kotlinx.android.synthetic.main.fragment_web_view.*
import java.util.*

class WebViewFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_view, container)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appActivity = activity as? AppCompatActivity
        appActivity?.delegate?.setSupportActionBar(web_toolbar)

        web_view.settings.javaScriptEnabled = true
        web_view.webViewClient = MeldWebClient()

        web_address.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadUrl(textView.text.toString())
            }

            true
        }

        go_button.setOnClickListener {
            loadUrl(web_address.text.toString())
        }

        capture_button.setOnClickListener {
            captureView()
        }

        history_button.setOnClickListener {
            showHistory()
        }
    }

    private fun loadUrl(url: String?) {
        val urlToLoad = getUrlWithHttp(url)

        web_view.loadUrl(urlToLoad)
    }

    private fun captureView() {
        val webItem = WebItem(web_view.url, Date(), getWebViewAsBitmap())
        val task = WriteWebItemTask(context!!)

        task.execute(webItem)
    }

    private fun showHistory() {
        //activity?.supportFragmentManager?.putFragment(Bundle(), "History", HistoryFragment())
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
}