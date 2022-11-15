package com.folios.plugin.w3acustom

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.ActivityCallback
import com.getcapacitor.annotation.CapacitorPlugin

var privateKey: String? = null

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
        val w3aCustomIntent = Intent(context, W3aCustom::class.java).apply {
            action = Intent.ACTION_MAIN
        }
        Log.i("NodaPoint1", "NodaPoint1")
        startActivityForResult(call, w3aCustomIntent, "w3aCustomResult")
        Log.i("NodaPoint2", "NodaPoint2")
        call.resolve()
    }

    @PluginMethod
    fun logout(call: PluginCall) {
        privateKey = null
        call.resolve()
    }

    @PluginMethod
    fun getPrivateKey(call: PluginCall) {
        Log.i("NodaPoint3", privateKey!!)
        val ret = JSObject()
        ret.put("value", privateKey)
        call.resolve(ret)
    }

    @ActivityCallback
    private fun w3aCustomResult(call: PluginCall?, result: ActivityResult): String {
        Log.i("Nodaresult", result.data.toString())
        privateKey = result.data.toString()
        return result.data.toString()
    }
}