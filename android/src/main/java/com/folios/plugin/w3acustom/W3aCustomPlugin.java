package com.folios.plugin.w3acustom;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "W3aCustom")
public class W3aCustomPlugin extends Plugin {

    private W3aCustom implementation = new W3aCustom();

    @PluginMethod
    public void onCreate(PluginCall call) {
        implementation.onCreate(saveInstanceState());
        call.resolve();
    }
}
