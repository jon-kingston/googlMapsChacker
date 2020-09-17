package com.example.googlmapschacker

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.googlmapschacker.fragments.MapsFragment
import com.example.googlmapschacker.fragments.ScannerFragment
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_map.*


class Map : AppCompatActivity() {

    var mapPosition = LatLng(-34.0, 151.0)

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_map)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            // Display the app icon in action bar/toolbar
            setDisplayShowHomeEnabled(true)
            setLogo(R.mipmap.ic_king_foreground)
            setDisplayUseLogoEnabled(true)
        }

        askPermission(Manifest.permission.CAMERA){
        }.onDeclined { e ->
            if (e.hasDenied())
                e.askAgain()
        }
     //   lastFragment = MapsFragment()
    }

    fun setPosition(latLngText: String){
        val (latitude, longitude) = latLngText.split(",")

        Toast.makeText(this, latitude + longitude, Toast.LENGTH_SHORT ).show()

        mapPosition = LatLng(latitude.toDouble(), longitude.toDouble())

        lastFragment = MapsFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    var lastFragment
        get() = supportFragmentManager.fragments.last()
        set(value) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, value)
                .commit()
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.skan -> {
//                val donetsk = LatLng(48.021753, 37.757999)
//                (lastFragment as MapsFragment).setPosition(donetsk)
                lastFragment = ScannerFragment()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}