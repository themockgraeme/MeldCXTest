package com.kizio.meldcxtest.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class BitmapConverter {
	@TypeConverter
	fun fromByteArray(array: ByteArray?): Bitmap? {
		return if (array != null) {
			BitmapFactory.decodeByteArray(array, 0, array.size)
		} else {
			null
		}
	}

	@TypeConverter
	fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? {
		return if (bitmap != null) {
			val bos = ByteArrayOutputStream()

			bitmap.compress(Bitmap.CompressFormat.PNG, 90, bos)

			bos.toByteArray()
		} else {
			null
		}
	}
}
