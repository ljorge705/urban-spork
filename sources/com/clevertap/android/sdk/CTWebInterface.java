package com.clevertap.android.sdk;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.clevertap.android.sdk.inapp.CTInAppAction;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTWebInterface {
    private CTInAppBaseFragment inAppBaseFragment;
    private final WeakReference<CleverTapAPI> weakReference;

    @JavascriptInterface
    public int getSdkVersion() {
        return BuildConfig.VERSION_CODE;
    }

    public CTWebInterface(CleverTapAPI cleverTapAPI) {
        CoreState coreState;
        WeakReference<CleverTapAPI> weakReference = new WeakReference<>(cleverTapAPI);
        this.weakReference = weakReference;
        CleverTapAPI cleverTapAPI2 = weakReference.get();
        if (cleverTapAPI2 == null || (coreState = cleverTapAPI2.getCoreState()) == null) {
            return;
        }
        coreState.getCoreMetaData().setWebInterfaceInitializedExternally(true);
    }

    public CTWebInterface(CleverTapAPI cleverTapAPI, CTInAppBaseFragment cTInAppBaseFragment) {
        this.weakReference = new WeakReference<>(cleverTapAPI);
        this.inAppBaseFragment = cTInAppBaseFragment;
    }

    @JavascriptInterface
    public void promptPushPermission(boolean z) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            dismissInAppNotification();
            cleverTapAPI.promptForPushPermission(z);
        }
    }

    @JavascriptInterface
    public void dismissInAppNotification() {
        if (this.weakReference.get() == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        CTInAppBaseFragment cTInAppBaseFragment = this.inAppBaseFragment;
        if (cTInAppBaseFragment != null) {
            cTInAppBaseFragment.didDismiss(null);
        }
    }

    @JavascriptInterface
    public void addMultiValueForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.addMultiValueForKey(str, str2);
        }
    }

    @JavascriptInterface
    public void incrementValue(String str, double d) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.incrementValue(str, Double.valueOf(d));
        }
    }

    @JavascriptInterface
    public void decrementValue(String str, double d) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.decrementValue(str, Double.valueOf(d));
        }
    }

    @JavascriptInterface
    public void addMultiValuesForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
            return;
        }
        if (str2 != null) {
            try {
                cleverTapAPI.addMultiValuesForKey(str, Utils.convertJSONArrayToArrayList(new JSONArray(str2)));
                return;
            } catch (JSONException e) {
                Logger.v("Unable to parse values from WebView " + e.getLocalizedMessage());
                return;
            }
        }
        Logger.v("values passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void pushChargedEvent(String str, String str2) {
        ArrayList<HashMap<String, Object>> arrayListConvertJSONArrayOfJSONObjectsToArrayListOfHashMaps;
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        if (str != null) {
            try {
                map = Utils.convertJSONObjectToHashMap(new JSONObject(str));
            } catch (JSONException e) {
                Logger.v("Unable to parse chargeDetails for Charged Event from WebView " + e.getLocalizedMessage());
            }
            if (str2 != null) {
                try {
                    arrayListConvertJSONArrayOfJSONObjectsToArrayListOfHashMaps = Utils.convertJSONArrayOfJSONObjectsToArrayListOfHashMaps(new JSONArray(str2));
                } catch (JSONException e2) {
                    Logger.v("Unable to parse items for Charged Event from WebView " + e2.getLocalizedMessage());
                    arrayListConvertJSONArrayOfJSONObjectsToArrayListOfHashMaps = null;
                }
                cleverTapAPI.pushChargedEvent(map, arrayListConvertJSONArrayOfJSONObjectsToArrayListOfHashMaps);
                return;
            }
            return;
        }
        Logger.v("chargeDetails passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void pushEvent(String str) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else {
            cleverTapAPI.pushEvent(str);
        }
    }

    @JavascriptInterface
    public void pushEvent(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        if (str2 != null) {
            try {
                cleverTapAPI.pushEvent(str, Utils.convertJSONObjectToHashMap(new JSONObject(str2)));
                return;
            } catch (JSONException e) {
                Logger.v("Unable to parse eventActions from WebView " + e.getLocalizedMessage());
                return;
            }
        }
        Logger.v("eventActions passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void pushProfile(String str) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        if (str != null) {
            try {
                cleverTapAPI.pushProfile(Utils.convertJSONObjectToHashMap(new JSONObject(str)));
                return;
            } catch (JSONException e) {
                Logger.v("Unable to parse profile from WebView " + e.getLocalizedMessage());
                return;
            }
        }
        Logger.v("profile passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void removeMultiValueForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
        } else if (str2 == null) {
            Logger.v("Value passed to CTWebInterface is null");
        } else {
            cleverTapAPI.removeMultiValueForKey(str, str2);
        }
    }

    @JavascriptInterface
    public void removeMultiValuesForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
            return;
        }
        if (str2 != null) {
            try {
                cleverTapAPI.removeMultiValuesForKey(str, Utils.convertJSONArrayToArrayList(new JSONArray(str2)));
                return;
            } catch (JSONException e) {
                Logger.v("Unable to parse values from WebView " + e.getLocalizedMessage());
                return;
            }
        }
        Logger.v("values passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void removeValueForKey(String str) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
        } else if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
        } else {
            cleverTapAPI.removeValueForKey(str);
        }
    }

    @JavascriptInterface
    public void setMultiValueForKey(String str, String str2) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        if (str == null) {
            Logger.v("Key passed to CTWebInterface is null");
            return;
        }
        if (str2 != null) {
            try {
                cleverTapAPI.setMultiValuesForKey(str, Utils.convertJSONArrayToArrayList(new JSONArray(str2)));
                return;
            } catch (JSONException e) {
                Logger.v("Unable to parse values from WebView " + e.getLocalizedMessage());
                return;
            }
        }
        Logger.v("values passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void onUserLogin(String str) {
        CleverTapAPI cleverTapAPI = this.weakReference.get();
        if (cleverTapAPI == null) {
            Logger.d("CleverTap Instance is null.");
            return;
        }
        if (str != null) {
            try {
                cleverTapAPI.onUserLogin(Utils.convertJSONObjectToHashMap(new JSONObject(str)));
                return;
            } catch (JSONException e) {
                Logger.v("Unable to parse profile from WebView " + e.getLocalizedMessage());
                return;
            }
        }
        Logger.v("profile passed to CTWebInterface is null");
    }

    @JavascriptInterface
    public void triggerInAppAction(String str, String str2, String str3) {
        if (this.weakReference.get() == null) {
            Logger.d("CTWebInterface CleverTap Instance is null.");
            return;
        }
        if (this.inAppBaseFragment == null) {
            Logger.d("CTWebInterface Fragment is null");
            return;
        }
        if (str == null) {
            Logger.d("CTWebInterface action JSON is null");
            return;
        }
        try {
            CTInAppAction cTInAppActionCreateFromJson = CTInAppAction.createFromJson(new JSONObject(str));
            if (cTInAppActionCreateFromJson == null) {
                Logger.d("CTWebInterface invalid action JSON: " + str);
                return;
            }
            Bundle bundle = new Bundle();
            if (str3 != null) {
                bundle.putString("button_id", str3);
            }
            this.inAppBaseFragment.triggerAction(cTInAppActionCreateFromJson, str2, bundle);
        } catch (JSONException unused) {
            Logger.d("CTWebInterface invalid action JSON: " + str);
        }
    }
}
