package com.petrulak.cleankotlin.platform.navigation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.petrulak.cleankotlin.ui.base.BaseActivity

class Navigator {

    fun navigate(source: BaseActivity, target: Class<out AppCompatActivity>, bundle: Bundle? = null) {
        val intent = Intent(source, target)
        bundle?.let { intent.putExtras(it) }
        source.startActivity(intent)
    }
}