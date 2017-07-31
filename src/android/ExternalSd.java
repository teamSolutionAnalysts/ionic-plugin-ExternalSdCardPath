package com.example.plugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.*;
import org.json.JSONObject;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


public class ExternalSd extends CordovaPlugin {

  public static final String EXTERNAL = "external";
  public static final String INTERNAL = "internal";

  public static final String FIXED_PATH = "Android/data/";
  public static final String SD_CARD = "SDCard";

  @Override
  public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

    if (action.equals("getPath")) {
      Context context = this.cordova.getActivity().getApplicationContext();
      String[] storageDirectories = getStorageDirectories(context);
      String strPackageName = this.cordova.getActivity().getPackageName();
      String strDynamicPath = FIXED_PATH + strPackageName;
//      String name = data.getString(0);
      JSONObject jsonObject = new JSONObject();
      JSONObject jsonSubobj = new JSONObject();
      for (int index = 0; index < storageDirectories.length; index++) {
        if (storageDirectories[index].contains("emulated")) {
          jsonSubobj.put(INTERNAL, "//" + storageDirectories[index] + File.separator + strDynamicPath + "/");
        } else {
          jsonSubobj.put(EXTERNAL, "//" + storageDirectories[index] + File.separator + strDynamicPath + "/");
        }
        jsonObject.putOpt(SD_CARD, jsonSubobj);
      }
      callbackContext.success(jsonObject.toString());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns all available SD-Cards in the system (include emulated)
   * <p>
   * Warning: Hack! Based on Android source code of version 4.3 (API 18)
   * Because there is no standard way to get it.
   *
   * @return paths to all available SD-Cards in the system (include emulated)
   */
  public static String[] getStorageDirectories(Context pContext) {
    // Final set of paths
    final Set<String> rv = new HashSet<String>();

    //Get primary & secondary external device storage (internal storage & micro SDCARD slot...)
    File[] listExternalDirs = ContextCompat.getExternalFilesDirs(pContext, null);
    for (int i = 0; i < listExternalDirs.length; i++) {
      if (listExternalDirs[i] != null) {
        String path = listExternalDirs[i].getAbsolutePath();
        int indexMountRoot = path.indexOf("/Android/data/");
        if (indexMountRoot >= 0 && indexMountRoot <= path.length()) {
          //Get the root path for the external directory
          rv.add(path.substring(0, indexMountRoot));
        }
      }
    }
    return rv.toArray(new String[rv.size()]);
  }
}
