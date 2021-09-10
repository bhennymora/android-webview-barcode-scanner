package com.example.myapplication

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.View
import android.os.Build

import android.annotation.TargetApi
import android.util.Log

import android.webkit.PermissionRequest

import android.webkit.WebChromeClient
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), 100)
        webViewSetup()
    }

    private fun webViewSetup(){
        val wView = findViewById<View>(R.id.webView) as WebView

        wView.settings.javaScriptEnabled = true
        wView.settings.allowFileAccessFromFileURLs = true
        wView.settings.allowUniversalAccessFromFileURLs = true
        wView.settings.allowContentAccess = true
        wView.settings.mediaPlaybackRequiresUserGesture = false
        wView.settings.domStorageEnabled = true

        wView.webViewClient = WebViewClient()
        wView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                runOnUiThread {

                        request.grant(request.resources)

                }
            }
        }

        wView.loadUrl("https://bhennymora.github.io/barcode-scanner/")
    }

    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        val webView = findViewById<View>(R.id.webView) as WebView
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }


}