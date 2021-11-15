package com.hfad.jokes.ui

import android.os.Bundle
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.hfad.jokes.R
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.hfad.jokes.databinding.FragmentWebBinding

class WebFragment :Fragment(R.layout.fragment_web) {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val webView: WebView = view.findViewById(R.id.web_view)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        val webSetting: WebSettings = webView.settings
        webSetting.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.canGoBack()
        webView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK

                && event.action == MotionEvent.ACTION_UP
                && webView.canGoBack()){
                webView.goBack()
                return@OnKeyListener true
            }
            false
        })


        webView.loadUrl("https://www.icndb.com/api/")
        webView.settings.javaScriptEnabled = true
    }
}