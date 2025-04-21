@file:Suppress("DEPRECATION")

package com.example.imageloader

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity: AppCompatActivity() {
    private lateinit var urlEditText: EditText
    private lateinit var loadImageButton: Button
    private lateinit var statusTextView: TextView
    private lateinit var imageView: ImageView

    // Task 2
    private val IMAGE_LOADER_ID = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        urlEditText = findViewById(R.id.urlEditText)
        loadImageButton = findViewById(R.id.loadButton)
        statusTextView = findViewById(R.id.statusTextView)
        imageView = findViewById(R.id.imageView)

        /** // Task 1
         * loadImageButton.setOnClickListener {
         *      val url = urlEditText.text.toString()
         *      ImageLoaderTask().execute(url)
         * }
         */
        
        // Task 2
        LoaderManager.getInstance(this).initLoader(IMAGE_LOADER_ID, null, loaderCallbacks)

        loadImageButton.setOnClickListener {
            val url = urlEditText.text.toString()
            if (url.isBlank()) {
                statusTextView.text = "Please enter a URL"
                return@setOnClickListener
            }

            statusTextView.text = "Loading..."
            imageView.setImageDrawable(null)

            // If loader exists, restart it
            val args = Bundle().apply {
                putString("url", urlEditText.text.toString())
            }

            LoaderManager.getInstance(this).restartLoader(IMAGE_LOADER_ID, args, loaderCallbacks)
        }
    }

    private val loaderCallbacks = object : LoaderManager.LoaderCallbacks<Bitmap?> {
        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Bitmap?> {
            val url = args?.getString("url") ?: "";
            return ImageLoader(this@MainActivity, url)
        }

        override fun onLoadFinished(loader: Loader<Bitmap?>, data: Bitmap?) {
            if (data != null){
                imageView.setImageBitmap(data)
                statusTextView.text = "Image loaded successfully"
            } else {
                statusTextView.text = "Failed to load image"
            }
        }

        override fun onLoaderReset(loader: Loader<Bitmap?>) {
            TODO("Not yet implemented")
        }
        
    }

    /**
     * // Task 1: Initial Implementation with AsyncTask
     *     inner class ImageLoaderTask: AsyncTask<String, String, Bitmap?>(){
     *         override fun onPreExecute() {
     *             super.onPreExecute()
     *             statusTextView.text = "Loading..."
     *         }
     *
     *         override fun doInBackground(vararg params: String?): Bitmap? {
     *             return try {
     *                 val url = URL(params[0])
     *                 val connection = url.openConnection() as HttpURLConnection
     *                 connection.doInput = true
     *                 connection.connect()
     *                 val input: InputStream = connection.inputStream
     *                 BitmapFactory.decodeStream(input)
     *             } catch (e: Exception){
     *                 e.printStackTrace()
     *                 null;
     *             }
     *         }
     *
     *         override fun onPostExecute(result: Bitmap?) {
     *             super.onPostExecute(result)
     *             if(result != null){
     *                 imageView.setImageBitmap(result)
     *                 statusTextView.text = "Image loaded successfully"
     *             } else {
     *                 statusTextView.text = "Failed to load image"
     *             }
     *         }
     *     }
     */
}