package com.kizio.meldcxtest

import com.kizio.meldcxtest.fragments.WebViewFragment
import org.junit.Assert.assertEquals
import org.junit.Test

class WebFragmentUnitTest {
    @Test
    fun getUrlWithHttpWithNoHttp() {
        val url = "www.google.com"
        val processed = "http://www.google.com"

        assertEquals(processed, WebViewFragment.getUrlWithHttp(url))
    }

    @Test
    fun getUrlWithHttpWithHttp() {
        val url = "http://www.google.com"

        assertEquals(url, WebViewFragment.getUrlWithHttp(url))
    }

    @Test
    fun getUrlWithHttpWithHttps() {
        val url = "https://www.google.com"

        assertEquals(url, WebViewFragment.getUrlWithHttp(url))
    }

    @Test
    fun getUrlWithHttpWithNull() {
        assertEquals(null, WebViewFragment.getUrlWithHttp(null))
    }
}
