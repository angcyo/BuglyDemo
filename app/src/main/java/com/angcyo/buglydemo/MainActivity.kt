package com.angcyo.buglydemo

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.TextView
import com.angcyo.sodemo.So

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text_view).apply {
            text = So.stringFromJNI()//TestClass.getString()//getString(R.string.text) //"测试文本-2018-9-17"
            setOnClickListener {
                val intent = Intent(this@MainActivity, NewActivity::class.java)
                startActivity(intent)
            }
        }

        /*
        * 程序无补丁:
        * D/CrashReport: app version is: [2.0.2.3], [deviceId:99001215161008|460110725738959|d064356c78ca8749],
        * channel: [null], base tinkerId:[bugly-1.4-patch], patch tinkerId:[], patch version:[1]
        *
        * 程序打了补丁:
        * D/CrashReport: app version is: [2.0.2.3], [deviceId:99001215161008|460110725738959|d064356c78ca8749],
        * channel: [null], base tinkerId:[bugly-1.4-patch], patch tinkerId:[bugly-1.5-patch], patch version:[1]
        * */
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE), 101)
    }
}
