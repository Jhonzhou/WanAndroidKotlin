package com.bee.wanandroidkotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bee.wanandroidkotlin.MainActivity

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/19
 * @Description:
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
    }

}