package io.invertase.notifee;

import android.os.Build;
import android.os.Bundle;
import app.notifee.core.Logger;
import app.notifee.core.Notifee;
import app.notifee.core.interfaces.MethodCallResult;
import com.clevertap.android.sdk.PushPermissionManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class NotifeeApiModule extends ReactContextBaseJavaModule implements PermissionListener {
    private static final int NOTIFICATION_TYPE_ALL = 0;
    private static final int NOTIFICATION_TYPE_DISPLAYED = 1;
    private static final int NOTIFICATION_TYPE_TRIGGER = 2;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "NotifeeApiModule";
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public NotifeeApiModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public static String getMainComponent(String str) {
        return Notifee.getInstance().getMainComponent(str);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        NotifeeReactUtils.clearRunningHeadlessTasks();
    }

    @ReactMethod
    public void cancelAllNotifications(final Promise promise) {
        Notifee.getInstance().cancelAllNotifications(0, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda13
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void cancelDisplayedNotifications(final Promise promise) {
        Notifee.getInstance().cancelAllNotifications(1, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda23
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void cancelTriggerNotifications(final Promise promise) {
        Notifee.getInstance().cancelAllNotifications(2, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda22
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void cancelAllNotificationsWithIds(ReadableArray readableArray, int i, String str, final Promise promise) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            arrayList.add(readableArray.getString(i2));
        }
        Notifee.getInstance().cancelAllNotificationsWithIds(i, arrayList, str, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda18
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void getDisplayedNotifications(final Promise promise) {
        Notifee.getInstance().getDisplayedNotifications(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda1
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (List<Bundle>) obj);
            }
        });
    }

    @ReactMethod
    public void getTriggerNotifications(final Promise promise) {
        Notifee.getInstance().getTriggerNotifications(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda2
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (List<Bundle>) obj);
            }
        });
    }

    @ReactMethod
    public void getTriggerNotificationIds(final Promise promise) {
        Notifee.getInstance().getTriggerNotificationIds(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda31
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseStringListResolver(promise, exc, (List) obj);
            }
        });
    }

    @ReactMethod
    public void createChannel(ReadableMap readableMap, final Promise promise) {
        Notifee.getInstance().createChannel(Arguments.toBundle(readableMap), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda17
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void createChannels(ReadableArray readableArray, final Promise promise) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Arguments.toBundle(readableArray.getMap(i)));
        }
        Notifee.getInstance().createChannels(arrayList, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda27
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void createChannelGroup(ReadableMap readableMap, final Promise promise) {
        Notifee.getInstance().createChannelGroup(Arguments.toBundle(readableMap), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda8
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void createChannelGroups(ReadableArray readableArray, final Promise promise) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Arguments.toBundle(readableArray.getMap(i)));
        }
        Notifee.getInstance().createChannelGroups(arrayList, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda10
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void deleteChannel(String str, final Promise promise) {
        Notifee.getInstance().deleteChannel(str, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda25
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void deleteChannelGroup(String str, final Promise promise) {
        Notifee.getInstance().deleteChannelGroup(str, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda32
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void displayNotification(ReadableMap readableMap, final Promise promise) {
        Notifee.getInstance().displayNotification(Arguments.toBundle(readableMap), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda11
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void openAlarmPermissionSettings(final Promise promise) {
        Notifee.getInstance().openAlarmPermissionSettings(getCurrentActivity(), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda3
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void createTriggerNotification(ReadableMap readableMap, ReadableMap readableMap2, final Promise promise) {
        Notifee.getInstance().createTriggerNotification(Arguments.toBundle(readableMap), Arguments.toBundle(readableMap2), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda28
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void getChannels(final Promise promise) {
        Notifee.getInstance().getChannels(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda14
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (List<Bundle>) obj);
            }
        });
    }

    @ReactMethod
    public void getChannel(String str, final Promise promise) {
        Notifee.getInstance().getChannel(str, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda19
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
            }
        });
    }

    @ReactMethod
    public void getChannelGroups(final Promise promise) {
        Notifee.getInstance().getChannelGroups(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda20
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (List<Bundle>) obj);
            }
        });
    }

    @ReactMethod
    public void getChannelGroup(String str, final Promise promise) {
        Notifee.getInstance().getChannel(str, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda29
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
            }
        });
    }

    @ReactMethod
    public void isChannelCreated(String str, final Promise promise) {
        Notifee.getInstance().isChannelCreated(str, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda4
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseBooleanResolver(promise, exc, (Boolean) obj);
            }
        });
    }

    @ReactMethod
    public void isChannelBlocked(String str, final Promise promise) {
        Notifee.getInstance().isChannelBlocked(str, new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda12
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseBooleanResolver(promise, exc, (Boolean) obj);
            }
        });
    }

    @ReactMethod
    public void getInitialNotification(final Promise promise) {
        Notifee.getInstance().getInitialNotification(getCurrentActivity(), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda0
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
            }
        });
    }

    @ReactMethod
    public void getNotificationSettings(final Promise promise) {
        Notifee.getInstance().getNotificationSettings(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda21
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
            }
        });
    }

    @ReactMethod
    public void requestPermission(final Promise promise) {
        if (Build.VERSION.SDK_INT < 33) {
            Notifee.getInstance().getNotificationSettings(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda5
                @Override // app.notifee.core.interfaces.MethodCallResult
                public final void onComplete(Exception exc, Object obj) {
                    NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
                }
            });
            return;
        }
        PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity) getCurrentActivity();
        if (permissionAwareActivity == null) {
            Logger.d("requestPermission", "Unable to get permissionAwareActivity for " + Build.VERSION.SDK_INT);
            Notifee.getInstance().getNotificationSettings(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda6
                @Override // app.notifee.core.interfaces.MethodCallResult
                public final void onComplete(Exception exc, Object obj) {
                    NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
                }
            });
        } else {
            Notifee.getInstance().setRequestPermissionCallback(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda7
                @Override // app.notifee.core.interfaces.MethodCallResult
                public final void onComplete(Exception exc, Object obj) {
                    NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
                }
            });
            permissionAwareActivity.requestPermissions(new String[]{PushPermissionManager.ANDROID_PERMISSION_STRING}, Notifee.REQUEST_CODE_NOTIFICATION_PERMISSION, this);
        }
    }

    @ReactMethod
    public void openNotificationSettings(String str, final Promise promise) {
        Notifee.getInstance().openNotificationSettings(str, getCurrentActivity(), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda30
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void openBatteryOptimizationSettings(final Promise promise) {
        Notifee.getInstance().openBatteryOptimizationSettings(getCurrentActivity(), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda15
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void isBatteryOptimizationEnabled(final Promise promise) {
        Notifee.getInstance().isBatteryOptimizationEnabled(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda16
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseBooleanResolver(promise, exc, (Boolean) obj);
            }
        });
    }

    @ReactMethod
    public void getPowerManagerInfo(final Promise promise) {
        Notifee.getInstance().getPowerManagerInfo(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda26
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc, (Bundle) obj);
            }
        });
    }

    @ReactMethod
    public void openPowerManagerSettings(final Promise promise) {
        Notifee.getInstance().openPowerManagerSettings(getCurrentActivity(), new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda24
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void stopForegroundService(final Promise promise) {
        Notifee.getInstance().stopForegroundService(new MethodCallResult() { // from class: io.invertase.notifee.NotifeeApiModule$$ExternalSyntheticLambda9
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                NotifeeReactUtils.promiseResolver(promise, exc);
            }
        });
    }

    @ReactMethod
    public void hideNotificationDrawer() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        NotifeeReactUtils.hideNotificationDrawer();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put("ANDROID_API_LEVEL", Integer.valueOf(Build.VERSION.SDK_INT));
        return map;
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        return Notifee.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }
}
