package com.clevertap.react;

import android.util.Log;
import com.clevertap.android.sdk.CTFeatureFlagsListener;
import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.InAppNotificationButtonListener;
import com.clevertap.android.sdk.InAppNotificationListener;
import com.clevertap.android.sdk.InboxMessageButtonListener;
import com.clevertap.android.sdk.InboxMessageListener;
import com.clevertap.android.sdk.PushPermissionResponseListener;
import com.clevertap.android.sdk.SyncListener;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.product_config.CTProductConfigListener;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CleverTapListenerProxy.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\fJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u0013\u001a\u00020\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\b\u0010\u001b\u001a\u00020\u0010H\u0016J4\u0010\u001c\u001a\u00020\u00102\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0016J$\u0010\u001e\u001a\u00020\u00102\u001a\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020!\u0018\u00010 j\n\u0012\u0004\u0012\u00020!\u0018\u0001`\"H\u0016J\b\u0010#\u001a\u00020\u0010H\u0016J0\u0010$\u001a\u00020\u00102&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010&j\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u0001`'H\u0016J0\u0010(\u001a\u00020\u00102&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010&j\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u0001`'H\u0016J\"\u0010)\u001a\u00020\u00102\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0016J\b\u0010/\u001a\u00020\u0010H\u0016J0\u00100\u001a\u00020\u00102&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0017\u0018\u00010&j\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0017\u0018\u0001`'H\u0016J\u0010\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u0014H\u0016J\u0010\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u000205H\u0016J\u0012\u00106\u001a\u00020\u00102\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0012\u00109\u001a\u00020\u00102\b\u0010:\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/clevertap/react/CleverTapListenerProxy;", "Lcom/clevertap/android/sdk/SyncListener;", "Lcom/clevertap/android/sdk/InAppNotificationListener;", "Lcom/clevertap/android/sdk/CTInboxListener;", "Lcom/clevertap/android/sdk/InboxMessageButtonListener;", "Lcom/clevertap/android/sdk/InboxMessageListener;", "Lcom/clevertap/android/sdk/InAppNotificationButtonListener;", "Lcom/clevertap/android/sdk/displayunits/DisplayUnitListener;", "Lcom/clevertap/android/sdk/product_config/CTProductConfigListener;", "Lcom/clevertap/android/sdk/CTFeatureFlagsListener;", "Lcom/clevertap/android/sdk/pushnotification/CTPushNotificationListener;", "Lcom/clevertap/android/sdk/PushPermissionResponseListener;", "()V", "LOG_TAG", "", "attachToInstance", "", "instance", "Lcom/clevertap/android/sdk/CleverTapAPI;", "beforeShow", "", "extras", "", "", "featureFlagsUpdated", "inboxDidInitialize", "inboxMessagesDidUpdate", "onActivated", "onDismissed", "actionExtras", "onDisplayUnitsLoaded", "units", "Ljava/util/ArrayList;", "Lcom/clevertap/android/sdk/displayunits/model/CleverTapDisplayUnit;", "Lkotlin/collections/ArrayList;", "onFetched", "onInAppButtonClick", "payload", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "onInboxButtonClick", "onInboxItemClicked", "message", "Lcom/clevertap/android/sdk/inbox/CTInboxMessage;", "contentPageIndex", "", "buttonIndex", "onInit", "onNotificationClickedPayloadReceived", "onPushPermissionResponse", "accepted", "onShow", "ctInAppNotification", "Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", "profileDataUpdated", "updates", "Lorg/json/JSONObject;", "profileDidInitialize", "cleverTapID", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CleverTapListenerProxy implements SyncListener, InAppNotificationListener, CTInboxListener, InboxMessageButtonListener, InboxMessageListener, InAppNotificationButtonListener, DisplayUnitListener, CTProductConfigListener, CTFeatureFlagsListener, CTPushNotificationListener, PushPermissionResponseListener {
    public static final CleverTapListenerProxy INSTANCE = new CleverTapListenerProxy();
    private static final String LOG_TAG = "CleverTapReact";

    @Override // com.clevertap.android.sdk.InAppNotificationListener
    public boolean beforeShow(Map<String, Object> extras) {
        return true;
    }

    private CleverTapListenerProxy() {
    }

    public final void attachToInstance(CleverTapAPI instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        CleverTapListenerProxy cleverTapListenerProxy = this;
        instance.unregisterPushPermissionNotificationResponseListener(cleverTapListenerProxy);
        instance.registerPushPermissionNotificationResponseListener(cleverTapListenerProxy);
        instance.setCTPushNotificationListener(this);
        instance.setInAppNotificationListener(this);
        instance.setSyncListener(this);
        instance.setCTNotificationInboxListener(this);
        instance.setInboxMessageButtonListener(this);
        instance.setCTInboxMessageListener(this);
        instance.setInAppNotificationButtonListener(this);
        instance.setDisplayUnitListener(this);
        instance.setCTProductConfigListener(this);
        instance.setCTFeatureFlagsListener(this);
    }

    @Override // com.clevertap.android.sdk.SyncListener
    public void profileDataUpdated(JSONObject updates) throws JSONException {
        if (updates == null) {
            return;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        Iterator<String> itKeys = updates.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                try {
                    JSONArray jSONArray = updates.getJSONArray(next);
                    WritableArray writableArrayCreateArray = Arguments.createArray();
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        writableArrayCreateArray.pushString(jSONArray.getJSONObject(i).toString());
                    }
                    writableMapCreateMap.putArray(next, writableArrayCreateArray);
                } catch (Exception e) {
                    Log.e("CleverTapReact", "Failed sending profile update event", e);
                }
            } catch (JSONException unused) {
                writableMapCreateMap.putString(next, updates.get(next).toString());
            }
        }
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putMap("updates", writableMapCreateMap);
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_PROFILE_SYNC, writableMapCreateMap2);
    }

    @Override // com.clevertap.android.sdk.SyncListener
    public void profileDidInitialize(String cleverTapID) {
        if (cleverTapID == null) {
            Log.d("CleverTapReact", "profileDidInitialize called with cleverTapID=null");
            return;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("CleverTapID", cleverTapID);
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_PROFILE_DID_INITIALIZE, writableMapCreateMap);
    }

    @Override // com.clevertap.android.sdk.InAppNotificationListener
    public void onShow(CTInAppNotification ctInAppNotification) {
        Intrinsics.checkNotNullParameter(ctInAppNotification, "ctInAppNotification");
        WritableMap writableMapCreateMap = Arguments.createMap();
        JSONObject jsonDescription = ctInAppNotification.getJsonDescription();
        if (jsonDescription != null) {
            writableMapCreateMap.putMap("data", CleverTapUtils.convertObjectToWritableMap(jsonDescription));
        }
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_IN_APP_NOTIFICATION_SHOWED, writableMapCreateMap);
    }

    @Override // com.clevertap.android.sdk.InAppNotificationListener
    public void onDismissed(Map<String, Object> extras, Map<String, Object> actionExtras) {
        WritableMap writableMapFromMap = CleverTapUtils.getWritableMapFromMap(extras);
        WritableMap writableMapFromMap2 = CleverTapUtils.getWritableMapFromMap(actionExtras);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putMap("extras", writableMapFromMap);
        writableMapCreateMap.putMap("actionExtras", writableMapFromMap2);
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_IN_APP_NOTIFICATION_DISMISSED, writableMapCreateMap);
    }

    @Override // com.clevertap.android.sdk.CTInboxListener
    public void inboxDidInitialize() {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_INBOX_DID_INITIALIZE, Arguments.createMap());
    }

    @Override // com.clevertap.android.sdk.CTInboxListener
    public void inboxMessagesDidUpdate() {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_INBOX_MESSAGES_DID_UPDATE, Arguments.createMap());
    }

    @Override // com.clevertap.android.sdk.InboxMessageButtonListener
    public void onInboxButtonClick(HashMap<String, String> payload) {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_ON_INBOX_BUTTON_CLICK, CleverTapUtils.getWritableMapFromMap(payload));
    }

    @Override // com.clevertap.android.sdk.InboxMessageListener
    public void onInboxItemClicked(CTInboxMessage message, int contentPageIndex, int buttonIndex) {
        WritableMap writableMapCreateMap;
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        JSONObject data = message != null ? message.getData() : null;
        if (data == null || (writableMapCreateMap = CleverTapUtils.convertObjectToWritableMap(data)) == null) {
            writableMapCreateMap = Arguments.createMap();
        }
        writableMapCreateMap2.putMap("data", writableMapCreateMap);
        writableMapCreateMap2.putInt("contentPageIndex", contentPageIndex);
        writableMapCreateMap2.putInt("buttonIndex", buttonIndex);
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_ON_INBOX_MESSAGE_CLICK, writableMapCreateMap2);
    }

    @Override // com.clevertap.android.sdk.InAppNotificationButtonListener
    public void onInAppButtonClick(HashMap<String, String> payload) {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_ON_INAPP_BUTTON_CLICK, CleverTapUtils.getWritableMapFromMap(payload));
    }

    @Override // com.clevertap.android.sdk.displayunits.DisplayUnitListener
    public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putArray("displayUnits", CleverTapUtils.getWritableArrayFromDisplayUnitList(units));
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_ON_DISPLAY_UNITS_LOADED, writableMapCreateMap);
    }

    @Override // com.clevertap.android.sdk.product_config.CTProductConfigListener
    public void onActivated() {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_PRODUCT_CONFIG_DID_ACTIVATE, Arguments.createMap());
    }

    @Override // com.clevertap.android.sdk.product_config.CTProductConfigListener
    public void onFetched() {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_PRODUCT_CONFIG_DID_FETCH, Arguments.createMap());
    }

    @Override // com.clevertap.android.sdk.product_config.CTProductConfigListener
    public void onInit() {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_PRODUCT_CONFIG_DID_INITIALIZE, Arguments.createMap());
    }

    @Override // com.clevertap.android.sdk.CTFeatureFlagsListener
    public void featureFlagsUpdated() {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_FEATURE_FLAGS_DID_UPDATE, Arguments.createMap());
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushNotificationListener
    public void onNotificationClickedPayloadReceived(HashMap<String, Object> payload) {
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_PUSH_NOTIFICATION_CLICKED, CleverTapUtils.getWritableMapFromMap(payload));
    }

    @Override // com.clevertap.android.sdk.PushPermissionResponseListener
    public void onPushPermissionResponse(boolean accepted) {
        Log.i("CleverTapReact", "onPushPermissionResponse result: " + accepted);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("accepted", accepted);
        CleverTapEventEmitter.INSTANCE.emit(CleverTapEvent.CLEVERTAP_ON_PUSH_PERMISSION_RESPONSE, writableMapCreateMap);
    }
}
