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
//            val ret = JSObject()
//            ret.put("value", )
//            call.resolve(ret)
        Log.i("NodaPoint2", "NodaPoint2")
        call.resolve()
    }


    @ActivityCallback
    private fun w3aCustomResult(call: PluginCall?, result: ActivityResult): String {
//        if (call == null) {
//            return
//        }


        // Do something with the result data
        Log.i("Nodaresult", result.data.toString())
//        Log.i("Noda", "Nodalog")
        val ret = JSObject()
        ret.put("value", result.data.toString())
        call?.resolve(ret)
        return result.data.toString()
    }

}