@file:Suppress("DEPRECATION")

package com.example.imageloader

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

    // Task 3
    private lateinit var networkReceiver: NetworkReceiver

    // Task 4
    private val REQUEST_POST_NOTIF = 1001

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

            val args = Bundle().apply {
                putString("url", url)
            }
            LoaderManager.getInstance(this).restartLoader(IMAGE_LOADER_ID, args, loaderCallbacks)

            // Start the service using PendingIntent
            val serviceIntent = Intent(this, ImageLoaderService::class.java)
            val pendingIntent = PendingIntent.getForegroundService(
                this,
                0,
                serviceIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            pendingIntent.send()
        }



        // Task 3
        val filter = android.content.IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION)
        networkReceiver = NetworkReceiver { isConnected, isWifi ->
            if (isConnected) {
                loadImageButton.isEnabled = true
                loadImageButton.text = "Load Image"
                statusTextView.text = if (isWifi) "Connected via Wi-Fi" else "Connected (non-Wi-Fi)"
            } else {
                loadImageButton.isEnabled = false
                loadImageButton.text = "No internet connection"
                statusTextView.text = "No internet connection"
            }
        }

        registerReceiver(networkReceiver, filter)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_POST_NOTIF
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_POST_NOTIF) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Allow to send noti
            } else {
                // Not allowed to send noti, turn off service
                Toast.makeText(this, "Notifications are disabled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
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