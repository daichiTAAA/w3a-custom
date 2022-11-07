package com.folios.plugin.w3acustom

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat.startActivity
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin


@CapacitorPlugin(name = "W3aCustom")
class W3aCustomPlugin : Plugin() {
    private val implementation = W3aCustom()

    @PluginMethod
    fun echo(call: PluginCall) {
        val pluginValue = implementation.echo()
        val value = call.getString("value")
        val concatValue = value + pluginValue
        val ret = JSObject()
        ret.put("value", concatValue)
        call.resolve(ret)
    }

    @PluginMethod
    fun login(call: PluginCall) {
        Handler(Looper.getMainLooper())
            .post {
                val w3aCustomIntent = Intent(context, W3aCustom::class.java).apply {
                    action = Intent.ACTION_MAIN
                }
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    context as Activity?,
                )

                startActivity(context, w3aCustomIntent, options.toBundle())
            }
        call.resolve()
    }

}