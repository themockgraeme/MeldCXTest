package com.kizio.meldcxtest.database

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Data class for persisting a web item.
 *
 * @param uid Unique [Int] identifying the row in the table
 * @param url The [Uri] the web page is downloaded from
 * @param date A [Date] object used to timestamp when the web page was accessed
 * @param image A [Bitmap] containing a snapshot of the web page
 */
@Entity(tableName = "web_items")
data class WebItem (@PrimaryKey val uid: Int/*,
                    @ColumnInfo(name = "url") val url: Uri,
                    @ColumnInfo(name = "date_time") val date: Date,
                    @ColumnInfo(name = "image") val image: Bitmap*/)