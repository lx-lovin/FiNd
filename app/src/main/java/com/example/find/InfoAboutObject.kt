package com.example.find

import android.content.Intent
import android.media.midi.MidiInputPort
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_info_about_object.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.sql.Connection
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.net.ssl.HttpsURLConnection

class InfoAboutObject : AppCompatActivity() {


 /*   inner class RetrieveData:AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {

            var result = ""
            var url:URL = URL(params[0])
            var httpConnection:HttpURLConnection = url.openConnection() as HttpURLConnection
            var inputStream:InputStream = httpConnection.inputStream

            var inputStreamReader:InputStreamReader = InputStreamReader(inputStream)
            var data = inputStreamReader.read()
            while(data!=-1){
                var current = data.toChar()
                result+=current
                data = inputStreamReader.read()
            }

            return result
        }

    }

*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_about_object)

        var intent:Intent = intent
        var result = intent.getStringExtra("result")

        var webview:WebView = findViewById(R.id.webView)


        webview.settings.javaScriptEnabled = true

        webview.webViewClient = WebViewClient()

        var url:String = "https://en.wikipedia.org/wiki/"+result
        webView.loadUrl(url)
    }
}
