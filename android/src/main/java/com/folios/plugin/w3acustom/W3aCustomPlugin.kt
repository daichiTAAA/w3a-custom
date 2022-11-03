package com.folios.plugin.w3acustom

import android.os.Bundle
import androidx.core.app.BundleCompat
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin


@CapacitorPlugin(name = "W3aCustom")
class W3aCustomPlugin :  Plugin() {
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
        implementation.login()
        call.resolve()
    }
}