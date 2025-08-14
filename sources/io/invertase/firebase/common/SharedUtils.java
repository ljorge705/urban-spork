package io.invertase.firebase.common;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class SharedUtils {
    private static final String EXPO_CORE_PACKAGE = "expo.core";
    private static final String EXPO_REGISTRY_CLASS = "ModuleRegistry";
    private static final String FLUTTER_CORE_PACKAGE = "io.flutter.plugin.common";
    private static final String FLUTTER_REGISTRY_CLASS = "PluginRegistry";
    private static final String REACT_NATIVE_CORE_PACKAGE = "com.facebook.react.bridge";
    private static final String REACT_NATIVE_REGISTRY_CLASS = "NativeModuleRegistry";
    private static final String RN_DEVSUPPORT_CLASS = "DevSupportManagerImpl";
    private static final String RN_DEVSUPPORT_PACKAGE = "com.facebook.react.devsupport";
    private static final String TAG = "Utils";

    public static int[] rectToIntArray(@Nullable Rect rect) {
        return (rect == null || rect.isEmpty()) ? new int[0] : new int[]{rect.left, rect.top, rect.right, rect.bottom};
    }

    public static int[] pointToIntArray(@Nullable Point point) {
        return point == null ? new int[0] : new int[]{point.x, point.y};
    }

    public static List<int[]> pointsToIntsList(@Nullable Point[] pointArr) {
        if (pointArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(pointArr.length);
        for (Point point : pointArr) {
            arrayList.add(pointToIntArray(point));
        }
        return arrayList;
    }

    public static Uri getUri(String str) {
        Uri uri = Uri.parse(str);
        return (uri.getScheme() == null || uri.getScheme().isEmpty()) ? Uri.fromFile(new File(str)) : uri;
    }

    public static WritableMap getExceptionMap(Exception exc) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        String message = exc.getMessage();
        writableMapCreateMap.putString("code", "unknown");
        writableMapCreateMap.putString("nativeErrorCode", "unknown");
        writableMapCreateMap.putString("message", message);
        writableMapCreateMap.putString("nativeErrorMessage", message);
        return writableMapCreateMap;
    }

    public static String timestampToUTC(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(Long.valueOf(j * 1000));
    }

    public static void sendEvent(ReactContext reactContext, String str, Object obj) {
        if (reactContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, obj);
        } else {
            Log.d(TAG, "Missing context - cannot send event!");
        }
    }

    public static boolean isAppInForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return false;
        }
        ReactNativeFirebaseJSON sharedInstance = ReactNativeFirebaseJSON.getSharedInstance();
        if (sharedInstance.contains("android_background_activity_names")) {
            ArrayList<String> arrayValue = sharedInstance.getArrayValue("android_background_activity_names");
            if (arrayValue.size() != 0) {
                List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
                String shortClassName = appTasks.size() > 0 ? appTasks.get(0).getTaskInfo().baseActivity.getShortClassName() : "";
                if (!"".equals(shortClassName) && arrayValue.contains(shortClassName)) {
                    return false;
                }
            }
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                try {
                    return ((ReactContext) context).getLifecycleState() == LifecycleState.RESUMED;
                } catch (ClassCastException unused) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getResId(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, CTVariableUtils.STRING, context.getPackageName());
        if (identifier == 0) {
            Log.e(TAG, "resource " + str + " could not be found");
        }
        return identifier;
    }

    public static Boolean reactNativeHasDevSupport() {
        return hasPackageClass(RN_DEVSUPPORT_PACKAGE, RN_DEVSUPPORT_CLASS);
    }

    public static Boolean isExpo() {
        return hasPackageClass(EXPO_CORE_PACKAGE, EXPO_REGISTRY_CLASS);
    }

    public static Boolean isFlutter() {
        return hasPackageClass(FLUTTER_CORE_PACKAGE, FLUTTER_REGISTRY_CLASS);
    }

    public static Boolean isReactNative() {
        return Boolean.valueOf(!isExpo().booleanValue() && hasPackageClass(REACT_NATIVE_CORE_PACKAGE, REACT_NATIVE_REGISTRY_CLASS).booleanValue());
    }

    public static Boolean hasPackageClass(String str, String str2) throws ClassNotFoundException {
        try {
            Class.forName(str + "." + str2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static WritableMap jsonObjectToWritableMap(JSONObject jSONObject) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        WritableMap writableMapCreateMap = Arguments.createMap();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object obj = jSONObject.get(next);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                writableMapCreateMap.putDouble(next, jSONObject.getDouble(next));
            } else if (obj instanceof Number) {
                writableMapCreateMap.putInt(next, jSONObject.getInt(next));
            } else if (obj instanceof String) {
                writableMapCreateMap.putString(next, jSONObject.getString(next));
            } else if (obj instanceof JSONObject) {
                writableMapCreateMap.putMap(next, jsonObjectToWritableMap(jSONObject.getJSONObject(next)));
            } else if (obj instanceof JSONArray) {
                writableMapCreateMap.putArray(next, jsonArrayToWritableArray(jSONObject.getJSONArray(next)));
            } else if (obj == JSONObject.NULL) {
                writableMapCreateMap.putNull(next);
            }
        }
        return writableMapCreateMap;
    }

    public static WritableArray jsonArrayToWritableArray(JSONArray jSONArray) throws JSONException {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                writableArrayCreateArray.pushDouble(jSONArray.getDouble(i));
            } else if (obj instanceof Number) {
                writableArrayCreateArray.pushInt(jSONArray.getInt(i));
            } else if (obj instanceof String) {
                writableArrayCreateArray.pushString(jSONArray.getString(i));
            } else if (obj instanceof JSONObject) {
                writableArrayCreateArray.pushMap(jsonObjectToWritableMap(jSONArray.getJSONObject(i)));
            } else if (obj instanceof JSONArray) {
                writableArrayCreateArray.pushArray(jsonArrayToWritableArray(jSONArray.getJSONArray(i)));
            } else if (obj == JSONObject.NULL) {
                writableArrayCreateArray.pushNull();
            }
        }
        return writableArrayCreateArray;
    }

    public static WritableMap mapToWritableMap(Map<String, Object> map) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            mapPutValue(entry.getKey(), entry.getValue(), writableMapCreateMap);
        }
        return writableMapCreateMap;
    }

    private static WritableArray listToWritableArray(List<Object> list) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            arrayPushValue(it.next(), writableArrayCreateArray);
        }
        return writableArrayCreateArray;
    }

    public static void arrayPushValue(@Nullable Object obj, WritableArray writableArray) {
        String name;
        if (obj == null || obj == JSONObject.NULL) {
            writableArray.pushNull();
            return;
        }
        name = obj.getClass().getName();
        name.hashCode();
        switch (name) {
            case "java.lang.Integer":
                writableArray.pushInt(((Integer) obj).intValue());
                break;
            case "java.lang.Float":
                writableArray.pushDouble(((Float) obj).floatValue());
                break;
            case "org.json.JSONArray$1":
                try {
                    writableArray.pushArray(jsonArrayToWritableArray((JSONArray) obj));
                    break;
                } catch (JSONException unused) {
                    writableArray.pushNull();
                    return;
                }
            case "java.lang.Boolean":
                writableArray.pushBoolean(((Boolean) obj).booleanValue());
                break;
            case "java.lang.Long":
                writableArray.pushDouble(((Long) obj).longValue());
                break;
            case "java.lang.Double":
                writableArray.pushDouble(((Double) obj).doubleValue());
                break;
            case "java.lang.String":
                writableArray.pushString((String) obj);
                break;
            case "org.json.JSONObject$1":
                try {
                    writableArray.pushMap(jsonObjectToWritableMap((JSONObject) obj));
                    break;
                } catch (JSONException unused2) {
                    writableArray.pushNull();
                    return;
                }
            default:
                if (List.class.isAssignableFrom(obj.getClass())) {
                    writableArray.pushArray(listToWritableArray((List) obj));
                    break;
                } else if (Map.class.isAssignableFrom(obj.getClass())) {
                    writableArray.pushMap(mapToWritableMap((Map) obj));
                    break;
                } else {
                    Log.d(TAG, "utils:arrayPushValue:unknownType:" + name);
                    writableArray.pushNull();
                    break;
                }
        }
    }

    public static void mapPutValue(String str, @Nullable Object obj, WritableMap writableMap) {
        String name;
        if (obj == null || obj == JSONObject.NULL) {
            writableMap.putNull(str);
            return;
        }
        name = obj.getClass().getName();
        name.hashCode();
        switch (name) {
            case "java.lang.Integer":
                writableMap.putInt(str, ((Integer) obj).intValue());
                break;
            case "java.lang.Float":
                writableMap.putDouble(str, ((Float) obj).floatValue());
                break;
            case "org.json.JSONArray$1":
                try {
                    writableMap.putArray(str, jsonArrayToWritableArray((JSONArray) obj));
                    break;
                } catch (JSONException unused) {
                    writableMap.putNull(str);
                    return;
                }
            case "java.lang.Boolean":
                writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
                break;
            case "java.lang.Long":
                writableMap.putDouble(str, ((Long) obj).longValue());
                break;
            case "java.lang.Double":
                writableMap.putDouble(str, ((Double) obj).doubleValue());
                break;
            case "java.lang.String":
                writableMap.putString(str, (String) obj);
                break;
            case "org.json.JSONObject$1":
                try {
                    writableMap.putMap(str, jsonObjectToWritableMap((JSONObject) obj));
                    break;
                } catch (JSONException unused2) {
                    writableMap.putNull(str);
                    return;
                }
            default:
                if (List.class.isAssignableFrom(obj.getClass())) {
                    writableMap.putArray(str, listToWritableArray((List) obj));
                    break;
                } else if (Map.class.isAssignableFrom(obj.getClass())) {
                    writableMap.putMap(str, mapToWritableMap((Map) obj));
                    break;
                } else {
                    Log.d(TAG, "utils:mapPutValue:unknownType:" + name);
                    writableMap.putNull(str);
                    break;
                }
        }
    }

    public static WritableMap readableMapToWritableMap(ReadableMap readableMap) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.merge(readableMap);
        return writableMapCreateMap;
    }
}
