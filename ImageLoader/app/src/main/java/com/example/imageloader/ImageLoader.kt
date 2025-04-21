@file:Suppress("DEPRECATION")
package com.example.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.loader.content.AsyncTaskLoader
import java.net.HttpURLConnection
import java.net.URL

class ImageLoader(context: Context, private val imageUrl: String):
AsyncTaskLoader<Bitmap?>(context){

    @Deprecated("Deprecated in Java")
    override fun loadInBackground(): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream = connection.inputStream
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null;
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("forceLoad()"))
    override fun onStartLoading() {
        forceLoad()
    }

}