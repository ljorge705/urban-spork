package com.clevertap.android.sdk;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CTXtensions.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0002\u001a\"\u0010\u0007\u001a\u0004\u0018\u00010\b*\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\n\u001a\u00020\b\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\t\u001a\u00020\r\u001a$\u0010\u000e\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0002H\u0007\u001a \u0010\u0013\u001a\u0004\u0018\u00010\b*\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u0002H\u0007\u001a\n\u0010\u0016\u001a\u00020\u0006*\u00020\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0006*\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0001\u001a\u001d\u0010\u001b\u001a\u00020\u0006*\u0004\u0018\u00010\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\b\u0003\u0010\u0000\u001a\f\u0010\u001c\u001a\u00020\u0006*\u0004\u0018\u00010\r\u001a\u0012\u0010\u001d\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\b\u001a\u0012\u0010\u001f\u001a\u00020\u0006*\u00020\u00022\u0006\u0010 \u001a\u00020\u0001\u001a\n\u0010!\u001a\u00020\u0006*\u00020\"\u001a;\u0010#\u001a\u00020\f\"\u0006\b\u0000\u0010$\u0018\u0001*\u00020\u00192!\u0010%\u001a\u001d\u0012\u0013\u0012\u0011H$¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\f0&H\u0086\bø\u0001\u0000\u001a\f\u0010*\u001a\u00020\u0019*\u0004\u0018\u00010\u0019\u001a \u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00190,*\u00020\r2\u0006\u0010-\u001a\u00020\b\u001a \u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00190,*\u00020\r2\u0006\u0010-\u001a\u00020\b\u001a\u000e\u0010/\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\b\u001a\u001b\u00100\u001a\b\u0012\u0004\u0012\u0002H$01\"\u0006\b\u0000\u0010$\u0018\u0001*\u00020\u0019H\u0086\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00062"}, d2 = {"targetSdkVersion", "", "Landroid/content/Context;", "getTargetSdkVersion", "(Landroid/content/Context;)I", "areAppNotificationsEnabled", "", "concatIfNotNull", "", "other", "separator", "copyFrom", "", "Lorg/json/JSONObject;", "flushPushImpressionsOnPostAsyncSafely", "Lcom/clevertap/android/sdk/CleverTapAPI;", "logTag", "caller", "context", "getOrCreateChannel", "Landroid/app/NotificationManager;", "msgChannel", "hasData", "Landroid/content/SharedPreferences;", "isInvalidIndex", "Lorg/json/JSONArray;", "index", "isNotNullAndBlank", "isNotNullAndEmpty", "isNotificationChannelEnabled", "channelId", "isPackageAndOsTargetsAbove", "apiLevel", "isValid", "Landroid/location/Location;", "iterator", ExifInterface.GPS_DIRECTION_TRUE, "foreach", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "element", "orEmptyArray", "safeGetJSONArray", "Lkotlin/Pair;", Constants.KEY_KEY, "safeGetJSONArrayOrNullIfEmpty", "toJsonOrNull", "toList", "", "clevertap-core_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTXtensions {
    public static final boolean isPackageAndOsTargetsAbove(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return Build.VERSION.SDK_INT > i && getTargetSdkVersion(context) > i;
    }

    public static final int getTargetSdkVersion(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getApplicationContext().getApplicationInfo().targetSdkVersion;
    }

    public static final boolean isNotificationChannelEnabled(Context context, String channelId) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        if (Build.VERSION.SDK_INT >= 26) {
            if (areAppNotificationsEnabled(context)) {
                try {
                    Object systemService = context.getSystemService("notification");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                    if (((NotificationManager) systemService).getNotificationChannel(channelId).getImportance() != 0) {
                        return true;
                    }
                } catch (Exception unused) {
                    Logger.d("Unable to find notification channel with id = " + channelId);
                }
            }
            return false;
        }
        return areAppNotificationsEnabled(context);
    }

    public static final boolean areAppNotificationsEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            return NotificationManagerCompat.from(context).areNotificationsEnabled();
        } catch (Exception e) {
            Logger.d("Unable to query notifications enabled flag, returning true!");
            e.printStackTrace();
            return true;
        }
    }

    public static final String getOrCreateChannel(NotificationManager notificationManager, String str, Context context) {
        String string;
        Intrinsics.checkNotNullParameter(notificationManager, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            String str2 = str;
            if (str2 != null && str2.length() != 0 && notificationManager.getNotificationChannel(str) != null) {
                return str;
            }
            String devDefaultPushChannelId = ManifestInfo.getInstance(context).getDevDefaultPushChannelId();
            String str3 = devDefaultPushChannelId;
            if (str3 != null && str3.length() != 0 && notificationManager.getNotificationChannel(devDefaultPushChannelId) != null) {
                return devDefaultPushChannelId;
            }
            String str4 = devDefaultPushChannelId;
            if (str4 == null || str4.length() == 0) {
                Logger.d(Constants.CLEVERTAP_LOG_TAG, "Missing Default CleverTap Notification Channel metadata in AndroidManifest.");
            } else {
                Logger.d(Constants.CLEVERTAP_LOG_TAG, "Notification Channel set in AndroidManifest.xml has not been created by the app.");
            }
            if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                try {
                    string = context.getString(R.string.ct_fcm_fallback_notification_channel_label);
                } catch (Exception unused) {
                    string = Constants.FCM_FALLBACK_NOTIFICATION_CHANNEL_NAME;
                }
                Intrinsics.checkNotNullExpressionValue(string, "try {\n                  …HANNEL_NAME\n            }");
                NotificationChannel notificationChannel = new NotificationChannel("fcm_fallback_notification_channel", string, 3);
                Logger.d(Constants.CLEVERTAP_LOG_TAG, "created default channel: " + notificationChannel);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            return "fcm_fallback_notification_channel";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void flushPushImpressionsOnPostAsyncSafely(final CleverTapAPI cleverTapAPI, final String logTag, final String caller, final Context context) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(cleverTapAPI, "<this>");
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            CTExecutorFactory.executors(cleverTapAPI.getCoreState().getConfig()).postAsyncSafelyTask().submit(logTag, new Callable() { // from class: com.clevertap.android.sdk.CTXtensions$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return CTXtensions.flushPushImpressionsOnPostAsyncSafely$lambda$1(cleverTapAPI, context, caller, logTag);
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void flushPushImpressionsOnPostAsyncSafely$lambda$1(CleverTapAPI this_flushPushImpressionsOnPostAsyncSafely, Context context, String caller, String logTag) {
        Intrinsics.checkNotNullParameter(this_flushPushImpressionsOnPostAsyncSafely, "$this_flushPushImpressionsOnPostAsyncSafely");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(caller, "$caller");
        Intrinsics.checkNotNullParameter(logTag, "$logTag");
        try {
            this_flushPushImpressionsOnPostAsyncSafely.getCoreState().getBaseEventQueueManager().flushQueueSync(context, EventGroup.PUSH_NOTIFICATION_VIEWED, caller);
            return null;
        } catch (Exception unused) {
            Logger.d(logTag, "failed to flush push impressions on ct instance = " + this_flushPushImpressionsOnPostAsyncSafely.getCoreState().getConfig().getAccountId());
            return null;
        }
    }

    public static final boolean isInvalidIndex(JSONArray jSONArray, int i) {
        return jSONArray == null || i < 0 || i >= jSONArray.length();
    }

    public static final boolean hasData(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Map<String, ?> all = sharedPreferences.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "all");
        return !all.isEmpty();
    }

    public static final JSONArray orEmptyArray(JSONArray jSONArray) {
        return jSONArray == null ? new JSONArray() : jSONArray;
    }

    public static final /* synthetic */ <T> List<T> toList(JSONArray jSONArray) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (obj instanceof Object) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final /* synthetic */ <T> void iterator(JSONArray jSONArray, Function1<? super T, Unit> foreach) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        Intrinsics.checkNotNullParameter(foreach, "foreach");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (obj instanceof Object) {
                foreach.invoke(obj);
            }
        }
    }

    public static final Pair<Boolean, JSONArray> safeGetJSONArrayOrNullIfEmpty(JSONObject jSONObject, String key) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(key);
        if (jSONArrayOptJSONArray == null) {
            return new Pair<>(false, null);
        }
        Boolean boolValueOf = Boolean.valueOf(jSONArrayOptJSONArray.length() > 0);
        if (jSONArrayOptJSONArray.length() <= 0) {
            jSONArrayOptJSONArray = null;
        }
        return new Pair<>(boolValueOf, jSONArrayOptJSONArray);
    }

    public static final Pair<Boolean, JSONArray> safeGetJSONArray(JSONObject jSONObject, String key) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(key);
        if (jSONArrayOptJSONArray == null) {
            return new Pair<>(false, null);
        }
        Boolean boolValueOf = Boolean.valueOf(jSONArrayOptJSONArray.length() >= 0);
        if (jSONArrayOptJSONArray.length() < 0) {
            jSONArrayOptJSONArray = null;
        }
        return new Pair<>(boolValueOf, jSONArrayOptJSONArray);
    }

    public static final void copyFrom(JSONObject jSONObject, JSONObject other) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Iterator<String> itKeys = other.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "other.keys()");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            jSONObject.put(next, other.opt(next));
        }
    }

    public static final boolean isNotNullAndEmpty(JSONObject jSONObject) {
        return jSONObject != null && jSONObject.length() > 0;
    }

    public static /* synthetic */ String concatIfNotNull$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = "";
        }
        return concatIfNotNull(str, str2, str3);
    }

    public static final String concatIfNotNull(String str, String str2, String separator) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        if (str == null || str2 == null) {
            return str == null ? str2 : str;
        }
        return str + separator + str2;
    }

    public static final boolean isValid(Location location) {
        Intrinsics.checkNotNullParameter(location, "<this>");
        double latitude = location.getLatitude();
        if (-90.0d <= latitude && latitude <= 90.0d) {
            double longitude = location.getLongitude();
            if (-180.0d <= longitude && longitude <= 180.0d) {
                return true;
            }
        }
        return false;
    }

    public static final JSONObject toJsonOrNull(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final boolean isNotNullAndBlank(String str) {
        String str2 = str;
        return !(str2 == null || StringsKt.isBlank(str2));
    }
}
