package com.example.googlmapschacker.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.googlmapschacker.Map
import com.example.googlmapschacker.R
import com.google.gson.Gson
import com.google.zxing.Result
import kotlinx.android.synthetic.main.fragment_maps.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.lang.Exception


class ScannerFragment:
    Fragment(),
    ZXingScannerView.ResultHandler {

    private lateinit var mScannerView: ZXingScannerView

    override fun onCreateView(infl: LayoutInflater, cont: ViewGroup?, inst: Bundle?): View? {
        mScannerView = ZXingScannerView(activity)
        return mScannerView
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun handleResult(rawResult: Result) {
        (activity as Map).setPosition(rawResult.text)

        Handler().postDelayed({
            mScannerView.setResultHandler(this)
            mScannerView.startCamera()
        }, 1000)
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }


}